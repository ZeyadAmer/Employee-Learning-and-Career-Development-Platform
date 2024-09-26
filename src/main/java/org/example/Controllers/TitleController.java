package org.example.Controllers;

import org.example.Mappers.TitleDTO;
import org.example.Services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/titles")
public class TitleController {
    @Autowired
    private TitleService titleService;
    @PostMapping
    public ResponseEntity<String> createTitle(@RequestBody TitleDTO titleDTO) {
        titleService.createTitle(titleDTO);
        return ResponseEntity.ok("Title created");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteTitle(@RequestBody String titleName) {
        titleService.deleteTitle(titleName);
        return ResponseEntity.ok("Title deleted");
    }
    @GetMapping("/all")
    public ResponseEntity<List<TitleDTO>> getTitles() {
        return ResponseEntity.ok(titleService.getAllTitles());
    }
    @PutMapping("/{name}")
    public ResponseEntity<String> updateTitleName(@PathVariable String name, @RequestBody String oldName) {
        titleService.updateTitleName(oldName, name);
        return ResponseEntity.ok("Title updated");
    }
    @PutMapping("/{department}")
    public ResponseEntity<String> updateTitleDepartment(@PathVariable String department, @RequestBody String titleName) {
        titleService.updateTitleDepartment(titleName,department);
        return ResponseEntity.ok("Title department updated");
    }
}
