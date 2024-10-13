package org.example.Controllers;

import org.example.Mappers.SubmittedCareerPackageDTO;
import org.example.Services.SubmittedCareerPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submittedCareerPackage")
public class SubmittedCareerPackageController {

    @Autowired
    private SubmittedCareerPackageService submittedCareerPackageService;

    @PostMapping
    public ResponseEntity<String> createSubmittedCareerPackage(@RequestBody SubmittedCareerPackageDTO submittedCareerPackageDTO){
        submittedCareerPackageService.createSubmittedCareerPackage(submittedCareerPackageDTO);
        return ResponseEntity.ok("Submitted Career Package created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubmittedCareerPackage(@RequestBody int id){
        submittedCareerPackageService.deleteSubmittedCareerPackage(id);
        return ResponseEntity.ok("Submitted Career Package deleted.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedCareerPackageDTO> getSubmittedCareerPackage(@PathVariable int id){
        return ResponseEntity.ok(submittedCareerPackageService.getSubmittedCareerPackage(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllSubmittedCareerPackages(){
        return ResponseEntity.ok(submittedCareerPackageService.getAllSubmittedCareerPackages());
    }
}
