package org.example.Controllers;

import org.example.Mappers.BoosterDTO;
import org.example.Services.BoosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/boosters")
public class BoosterController {
    @Autowired
    private BoosterService boosterService;

    @PostMapping
    public ResponseEntity<String> createBooster(@RequestBody BoosterDTO boosterDTO){
        boosterService.createBooster(boosterDTO);
        return ResponseEntity.ok("{\"Response\":\"" + "Booster created" + "\"}");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBooster(@RequestBody String name){
        boosterService.deleteBooster(name);
        return ResponseEntity.ok("Booster deleted");
    }

    @PutMapping("/updateName/{oldName}")
    public ResponseEntity<String> updateBoosterName(@PathVariable String oldName, @RequestBody String newName){
        boosterService.updateBoosterName(oldName, newName);
        return ResponseEntity.ok("Booster name updated.");
    }

    @PutMapping("/updateBoosterActive/{name}")
    public ResponseEntity<String> updateBoosterActivity(@PathVariable String name, @RequestBody boolean isActive){
        boosterService.updateBoosterActivity(name, isActive);
        return ResponseEntity.ok("Booster name updated.");
    }

    @PutMapping("/updateBoosterType/{boosterTypeName}")
    public ResponseEntity<String> updateBoosterType(@PathVariable String boosterTypeName, @RequestBody String boosterName){
        boosterService.updateBoosterType(boosterName, boosterTypeName);
        return ResponseEntity.ok("Booster Type updated");
    }

    @GetMapping("/{name}")
    public ResponseEntity<BoosterDTO> getBooster(@PathVariable String name){
        return ResponseEntity.ok(boosterService.getBooster(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BoosterDTO>> getAllBoosters(){
        return ResponseEntity.ok(boosterService.getAllBoosters());
    }
}
