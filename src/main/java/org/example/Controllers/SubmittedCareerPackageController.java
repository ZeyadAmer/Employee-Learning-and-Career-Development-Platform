package org.example.Controllers;

import org.example.Enums.CareerPackageStatus;
import org.example.Mappers.SubmittedCareerPackageDTO;
import org.example.Services.SubmittedCareerPackageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> updateSubmittedCareerPackage(@PathVariable int id, @RequestBody CareerPackageStatus careerPackageStatus){
        submittedCareerPackageService.updateSubmittedCareerPackage(id,careerPackageStatus);
        return ResponseEntity.ok("Submiited Career Package Status updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmittedCareerPackageDTO> getSubmittedCareerPackage(@PathVariable int id){
        return ResponseEntity.ok(submittedCareerPackageService.getSubmittedCareerPackage(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllEmployeeSubmittedCareerPackage(@PathVariable int employeeId){
        return ResponseEntity.ok(submittedCareerPackageService.getAllEmployeeSubmittedCareerPackage(employeeId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubmittedCareerPackageDTO>> getAllSubmittedCareerPackages(){
        return ResponseEntity.ok(submittedCareerPackageService.getAllSubmittedCareerPackages());
    }
}
