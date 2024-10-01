package org.example.Controllers;

import org.example.Mappers.LearningsDTO;
import org.example.Services.LearningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learnings")
public class LearningsController {

    @Autowired
    private LearningsService learningsService;

    @PostMapping
    public ResponseEntity<String> createLearning(@RequestBody LearningsDTO learningsDTO){
        learningsService.createLearning(learningsDTO);
        return ResponseEntity.ok("Learning created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLearning(@RequestBody int id){
        learningsService.deleteLearning(id);
        return ResponseEntity.ok("Learning deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLearning(@PathVariable int id,@RequestBody LearningsDTO learningsDTO){
        learningsService.updateLearning(id, learningsDTO);
        return ResponseEntity.ok("Learning updated.");
    }

    @GetMapping
    public ResponseEntity<LearningsDTO> getLearning(@RequestBody int id){
        return ResponseEntity.ok(learningsService.getLearning(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LearningsDTO>> getAllLearnings(){
        return ResponseEntity.ok(learningsService.getAllLearnings());
    }
}
