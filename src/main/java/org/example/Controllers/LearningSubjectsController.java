package org.example.Controllers;

import org.example.Entities.LearningSubjects;
import org.example.Mappers.LearningSubjectsDTO;
import org.example.Services.LearningSubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/learningSubjects")
public class LearningSubjectsController {
    @Autowired
    private LearningSubjectsService learningSubjectsService;

    @PostMapping
    public ResponseEntity<String> createLearningSubject(@RequestBody LearningSubjectsDTO learningSubjectsDTO){
        learningSubjectsService.createLearningSubject(learningSubjectsDTO);
        return ResponseEntity.ok("Learning Subject created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLearningSubject(@RequestBody String subject){
        learningSubjectsService.deleteLearningSubject(subject);
        return ResponseEntity.ok("Learning Subject deleted.");
    }

    @PutMapping("/updateName/{oldName}")
    public ResponseEntity<String> updateLearningSubjectName(@PathVariable String oldName, @RequestBody String newName){
        learningSubjectsService.updateLearningSubjectName(oldName, newName);
        return ResponseEntity.ok("Learning Subject Name updated.");
    }

    @PutMapping("/updateLearningType/{subject}")
    public ResponseEntity<String> updateLearningSubjectType(@PathVariable String subject, @RequestBody LearningSubjects.LearningSubjectsType learningSubjectsType){
        learningSubjectsService.updateLearningSubjectType(subject, learningSubjectsType);
        return ResponseEntity.ok("Learning Subject Type updated.");
    }

    @GetMapping("/{subject}")
    public ResponseEntity<LearningSubjectsDTO> getLearningSubject(@PathVariable String subject){
        return ResponseEntity.ok(learningSubjectsService.getLearningSubject(subject));
    }

    @GetMapping
    public ResponseEntity<List<LearningSubjectsDTO>> getAllLearningSubjects(){
        return ResponseEntity.ok(learningSubjectsService.getAllLearningSubjects());
    }
}
