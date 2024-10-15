package org.example.Controllers;

import org.example.Enums.CareerPackageStatus;
import org.example.Mappers.SubmittedCareerPackageDTO;
import org.example.Services.SubmittedCareerPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // for CORS
@RequestMapping("/submittedCareerPackage")
public class SubmittedCareerPackageController {

    @Autowired
    private SubmittedCareerPackageService submittedCareerPackageService;

    @PostMapping
    public ResponseEntity<String> createSubmittedCareerPackage(@RequestBody SubmittedCareerPackageDTO submittedCareerPackageDTO){
        submittedCareerPackageService.createSubmittedCareerPackage(submittedCareerPackageDTO);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Submitted Career Package created.");
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubmittedCareerPackage(@RequestBody int id){
        submittedCareerPackageService.deleteSubmittedCareerPackage(id);
        return ResponseEntity.ok("Submitted Career Package deleted.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubmittedCareerPackage(@PathVariable int id, @RequestParam("careerPackageStatus") CareerPackageStatus careerPackageStatus){
        System.out.println("STATUS RECEIVED: "+ careerPackageStatus);
        submittedCareerPackageService.updateSubmittedCareerPackage(id,careerPackageStatus);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Submiited Career Package Status updated.");
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedCareerPackageDTO> getSubmittedCareerPackage(@PathVariable int id){
        return ResponseEntity.ok(submittedCareerPackageService.getSubmittedCareerPackage(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllEmployeeSubmittedCareerPackage(@PathVariable int employeeId){
        return ResponseEntity.ok(submittedCareerPackageService.getAllEmployeeSubmittedCareerPackage(employeeId));
    }


    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllManagerReceivedCareerPackage(@PathVariable int managerId){
        return ResponseEntity.ok(submittedCareerPackageService.getAllManagerReceivedCareerPackage(managerId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllSubmittedCareerPackages(){
        return ResponseEntity.ok(submittedCareerPackageService.getAllSubmittedCareerPackages());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadSubmittedCareerPackage(
            @PathVariable int id,
            @RequestParam("careerPackageName") String careerPackageName) {

        byte[] careerPackageFile = submittedCareerPackageService.downloadSubmittedCareerPackage(id);

        if (careerPackageFile == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + careerPackageName + "\"");

        return new ResponseEntity<>(careerPackageFile, headers, HttpStatus.OK);
    }

}
