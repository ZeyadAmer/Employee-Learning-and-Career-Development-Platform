package org.example.Controllers;

import org.example.Mappers.LearningTypesDTO;
import org.example.Services.LearningTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/learningTypes")
public class LearningTypesController {

    @Autowired
    private LearningTypesService learningTypesService;

    @PostMapping
    public ResponseEntity<String> createLearningType(@RequestBody LearningTypesDTO learningTypesDTO){
        learningTypesService.createLearningType(learningTypesDTO);
        return ResponseEntity.ok("Learning Type created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLearningType(@RequestBody String learningTypeName){
        learningTypesService.deleteLearningType(learningTypeName);
        return ResponseEntity.ok("Learning Type deleted.");
    }

    @PutMapping("/updateName/{oldName}")
    public ResponseEntity<String> updateLearningTypeName(@PathVariable String oldName, @RequestBody String newName){
        learningTypesService.updateLearningTypeName(oldName,newName);
        return ResponseEntity.ok("Learning TYpe Name updated");
    }

    @PutMapping("/updateScore/{learningTypeName}")
    public ResponseEntity<String> updateLearningTypeScore(@PathVariable String learningTypeName, @RequestBody int score ){
        learningTypesService.updateLearningTypeScore(learningTypeName,score);
        return ResponseEntity.ok("Learning Type Score updated.");
    }

    @GetMapping("/{learningTypeName}")
    public ResponseEntity<LearningTypesDTO> getLearningType(@PathVariable String learningTypeName){
        return ResponseEntity.ok(learningTypesService.getLearningType(learningTypeName));
    }

    @GetMapping
    public ResponseEntity<List<LearningTypesDTO>> getAllLearningTypes(){
        return ResponseEntity.ok(learningTypesService.getAllLearningTypes());
    }
}
