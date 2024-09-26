package org.example.Controllers;

import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Mappers.DepartmentDTO;
import org.example.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok("Department created");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteDepartment(@RequestBody String departmentName) {
        departmentService.deleteDepartment(departmentName);
        return ResponseEntity.ok("Department deleted");
    }
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping("/titles")
    public ResponseEntity<List<Title>> getAllDepartmentsTitles(@RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.getAllTitles(departmentDTO));
    }
    @PutMapping("/{name}")
    public ResponseEntity<String> updateDepartment(@RequestBody DepartmentDTO departmentDTO,@PathVariable String name) {
        departmentService.updateDepartment(departmentDTO,name);
        return ResponseEntity.ok("Department updated");
    }
}
