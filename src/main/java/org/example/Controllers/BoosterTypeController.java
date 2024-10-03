package org.example.Controllers;

import org.example.Mappers.BoosterTypeDTO;
import org.example.Services.BoosterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/boosterTypes")
public class BoosterTypeController {
    @Autowired
    private BoosterTypeService boosterTypeService;

    @PostMapping
    public ResponseEntity<String> createBoosterType(@RequestBody BoosterTypeDTO boosterTypeDTO){
        boosterTypeService.createBoosterType(boosterTypeDTO);
        return ResponseEntity.ok("{\"Response\":\"" + "Booster Type created" + "\"}");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBoosterType(@RequestBody String name){
        boosterTypeService.deleteBoosterType(name);
        return ResponseEntity.ok("Booster Type deleted");
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateBoosterType(@PathVariable String name,@RequestBody int newValue){
        boosterTypeService.updateBoosterType(name, newValue);
        return ResponseEntity.ok("Booster Type updated.");
    }

    @GetMapping("/{name}")
    public ResponseEntity<BoosterTypeDTO> getBoosterType(@PathVariable String name){
        return ResponseEntity.ok(boosterTypeService.getBoosterType(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BoosterTypeDTO>> getAllBoosterTypes(){
        return ResponseEntity.ok(boosterTypeService.getAllBoosterTypes());
    }
}
