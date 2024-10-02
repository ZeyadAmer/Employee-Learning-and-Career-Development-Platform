package org.example.Controllers;

import org.example.Mappers.UserScoresDTO;
import org.example.Services.UserScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  org.example.Configurations.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userScores")
public class UserScoresController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserScoresService userScoresService;

    //todo
    //list of users sorted by score
    //
    @PostMapping
    public ResponseEntity<String> createUserScore(@RequestBody int score, HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        int id = jwtUtil.extractUserId(token);
        UserScoresDTO userScoresDTO = new UserScoresDTO(id, score);
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
