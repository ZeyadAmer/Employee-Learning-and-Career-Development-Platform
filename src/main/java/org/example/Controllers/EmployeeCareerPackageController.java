package org.example.Controllers;

import org.example.Mappers.EmployeeCareerPackageDTO;
import org.example.Services.EmployeeCareerPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // for CORS
@RequestMapping("/employeeCareerPackages")
public class EmployeeCareerPackageController {

    @Autowired
    private EmployeeCareerPackageService employeeCareerPackageService;

    @PostMapping
    public ResponseEntity<String> createEmployeeCareerPackage(@RequestParam("employeeId") int employeeId, @RequestParam("careerPackage") MultipartFile file, @RequestParam("careerPackageName") String careerPackageName, @RequestParam("date")Date date) throws IOException {
        byte[] careerPackage = file.getBytes();
        EmployeeCareerPackageDTO employeeCareerPackageDTO = new EmployeeCareerPackageDTO(employeeId, careerPackage, careerPackageName, date);
        employeeCareerPackageService.createEmployeeCareerPackage(employeeCareerPackageDTO);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Employee Career Package created.");
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployeeCareerPackage(@RequestBody int id){
        employeeCareerPackageService.deleteEmployeeCareerPackage(id);
        return ResponseEntity.ok("Employee Career Package deleted.");
    }

    @PutMapping
    public ResponseEntity<String> updateEmployeeCareerPackage(@RequestParam("id") int id, @RequestParam("careerPackage") MultipartFile file) throws IOException {
        byte[] careerPackage = file.getBytes();
        employeeCareerPackageService.updateEmployeeCareerPackage(id, careerPackage);
        return ResponseEntity.ok("Employee Career Package updated.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeCareerPackageDTO> getEmployeeCareerPackage(@PathVariable int id){
        return ResponseEntity.ok(employeeCareerPackageService.getEmployeeCareerPackage(id));
    }

    @GetMapping("/all/{employeeId}")
    public ResponseEntity<List<EmployeeCareerPackageDTO>> getEmployeeCareerPackages(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeCareerPackageService.getEmployeeCareerPackages(employeeId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeCareerPackageDTO>> getAllEmployeeCareerPackages(){
        return ResponseEntity.ok(employeeCareerPackageService.getAllEmployeeCareerPackages());
    }
}
