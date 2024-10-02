package org.example.Controllers;

import org.example.Mappers.ScoreboardLevelsDTO;
import org.example.Services.ScoreboardLevelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/scoreboardLevels")
public class ScoreboardLevelsController {

    @Autowired
    private ScoreboardLevelsService scoreboardLevelsService;

    @PostMapping
    public ResponseEntity<String> createScoreBoardLevel(@RequestBody ScoreboardLevelsDTO scoreboardLevelsDTO){
        scoreboardLevelsService.createScoreBoardLevel(scoreboardLevelsDTO);
        return ResponseEntity.ok("Scoreboard Level created.");
    }

    @DeleteMapping("/{levelName}")
    public ResponseEntity<String> deleteScoreboardLevel(@PathVariable String levelName){
        scoreboardLevelsService.deleteScoreboardLevel(levelName);
        return ResponseEntity.ok("Scoreboard Level deleted.");
    }

    @PutMapping("/updateName/{oldName}")
    public ResponseEntity<String> updateScoreboardLevelName(@PathVariable String oldName, @RequestBody String newName){
        scoreboardLevelsService.updateScoreboardLevelName(oldName,newName);
        return ResponseEntity.ok("Scoreboard Level Name updated.");
    }

    @PutMapping("/updateScore/{levelName}")
    public ResponseEntity<String> updateScoreboardLevelName(@PathVariable String levelName, @RequestBody int minScore){
        scoreboardLevelsService.updateScoreboardLevelScore(levelName,minScore);
        return ResponseEntity.ok("Scoreboard Level Score updated.");
    }

    @GetMapping("/{levelName}")
    public ResponseEntity<ScoreboardLevelsDTO> getScoreboardLevel(@PathVariable String levelName){
        return ResponseEntity.ok(scoreboardLevelsService.getScoreboardLevel(levelName));
    }
    @GetMapping("/levelName/{score}")
    public ResponseEntity<String> getScoreboardLevelByScore(@PathVariable String score){
        int scoreInt = Integer.parseInt(score);
        String levelName = scoreboardLevelsService.getScoreboardLevelByScore(scoreInt);
        return ResponseEntity.ok("{\"levelName\":\"" + levelName + "\"}");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScoreboardLevelsDTO>> getAllScoreboardLevels(){
        return ResponseEntity.ok(scoreboardLevelsService.getAllScoreboardLevels());
    }
}
