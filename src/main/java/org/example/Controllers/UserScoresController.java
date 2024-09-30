package org.example.Controllers;

import org.example.Mappers.UserScoresDTO;
import org.example.Services.UserScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userScores")
public class UserScoresController {

    @Autowired
    private UserScoresService userScoresService;

    @PostMapping
    public ResponseEntity<String> createUserScore(@RequestBody UserScoresDTO userScoresDTO){
        userScoresService.createUserScore(userScoresDTO);
        return ResponseEntity.ok("User Score created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserScore(@RequestBody int id){
        userScoresService.deleteUserScore(id);
        return ResponseEntity.ok("User Score deleted.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserScore(@PathVariable int id, @RequestBody int newScore){
        userScoresService.updateUserScore(id, newScore);
        return ResponseEntity.ok("User Score updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserScoresDTO> getUserScore(@PathVariable int id){
        return ResponseEntity.ok(userScoresService.getUserScore(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserScoresDTO>> getAllUserScores(){
        return ResponseEntity.ok(userScoresService.getAllUserScores());
    }
}
