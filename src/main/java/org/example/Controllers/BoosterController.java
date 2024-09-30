package org.example.Controllers;

import org.example.Mappers.BoosterDTO;
import org.example.Services.BoosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boosters")
public class BoosterController {
    @Autowired
    private BoosterService boosterService;

    @PostMapping
    public ResponseEntity<String> createBooster(@RequestBody BoosterDTO boosterDTO){
        boosterService.createBooster(boosterDTO);
        return ResponseEntity.ok("Booster created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBooster(@RequestBody String name){
        boosterService.deleteBooster(name);
        return ResponseEntity.ok("Booster deleted");
    }

    @PutMapping
    public ResponseEntity<String> updateBooster(@RequestBody String oldName, String newName,
                                                boolean isActive, String boosterTypeName){
        boosterService.updateBooster(oldName, newName, isActive, boosterTypeName);
        return ResponseEntity.ok("Booster updated.");
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
