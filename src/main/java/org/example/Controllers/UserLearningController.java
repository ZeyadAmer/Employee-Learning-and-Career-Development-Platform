package org.example.Controllers;

import org.example.Mappers.LearningsDTO;
import org.example.Mappers.UserLearningDTO;
import org.example.Services.UserLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userLearning")
public class UserLearningController {

    @Autowired
    private UserLearningService userLearningService;

    @PostMapping
    public ResponseEntity<String> createUserLearning(UserLearningDTO userLearningDTO){
        userLearningService.createUserLearning(userLearningDTO);
        return ResponseEntity.ok("User Learning created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserLearning(@RequestBody int id){
        userLearningService.deleteUserLearning(id);
        return ResponseEntity.ok("User Learning deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserLearnoinf(@PathVariable int id,@RequestBody UserLearningDTO userLearningDTO){
        userLearningService.updateUserLearning(id, userLearningDTO);
        return ResponseEntity.ok("User Learning updated.");
    }

    @GetMapping
    public ResponseEntity<UserLearningDTO> getUserLearning(@RequestBody int id){
        return ResponseEntity.ok(userLearningService.getUserLearning(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserLearningDTO>> getAllUserLearnings(){
        return ResponseEntity.ok(userLearningService.getAllUserLearnings());
    }
}
