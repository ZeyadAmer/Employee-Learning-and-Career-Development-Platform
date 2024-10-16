package org.example.controller;

import org.example.mapper.CareerPackageTemplateDTO;
import org.example.service.CareerPackageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/careerPackageTemplates")
public class CareerPackageTemplateController {

    @Autowired
    private CareerPackageTemplateService careerPackageTemplateService;

    @PostMapping
    public ResponseEntity<String> createCareerPackageTemplate(@RequestParam("careerPackageTemplate")MultipartFile file, @RequestParam("title") String title) throws IOException {
        byte[] careerPackage = file.getBytes();
        CareerPackageTemplateDTO careerPackageTemplateDTO = new CareerPackageTemplateDTO(title,careerPackage);
        careerPackageTemplateService.createCareerPackageTemplate(careerPackageTemplateDTO);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Career Package Template created.");
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCareerPackageTemplate(@RequestBody String title){
        careerPackageTemplateService.deleteCareerPackageTemplate(title);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Career Package Template deleted.");
        return ResponseEntity.ok(jsonResponse);
    }

    @PutMapping
    public ResponseEntity<String> updateCareerPackageTemplate(@RequestBody CareerPackageTemplateDTO careerPackageTemplateDTO){
        careerPackageTemplateService.updateCareerPackageTemplate(careerPackageTemplateDTO);
        return ResponseEntity.ok("Career Package Template updated.");
    }

    @GetMapping("/{title}")
    public ResponseEntity<CareerPackageTemplateDTO> getCareerPackageTemplate(@PathVariable String title){
        return ResponseEntity.ok(careerPackageTemplateService.getCareerPackageTemplate(title));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CareerPackageTemplateDTO>> getAllCareerPackageTemplates(){
        return ResponseEntity.ok(careerPackageTemplateService.getAllCareerPackageTemplates());
    }

    @GetMapping("/download/{title}")
    public ResponseEntity<byte[]> downloadCareerPackageTemplate(@PathVariable String title) {

        byte[] careerPackageFile = careerPackageTemplateService.downloadCareerPackageTemplate(title);

        if (careerPackageFile == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + title + "\"");

        return new ResponseEntity<>(careerPackageFile, headers, HttpStatus.OK);
    }
}
