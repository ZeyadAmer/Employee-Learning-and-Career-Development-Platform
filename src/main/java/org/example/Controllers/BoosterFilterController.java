package org.example.Controllers;

import org.example.Entities.BoosterFilter;
import org.example.Entities.BoosterFilterId;
import org.example.Mappers.BoosterDTO;
import org.example.Mappers.BoosterFilterDTO;
import org.example.Mappers.BoosterFilterRequest;
import org.example.Services.BoosterFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/boosterFilters")
public class BoosterFilterController {

    @Autowired
    private BoosterFilterService boosterFilterService;

    @PostMapping // create boosterFilterRequest class
    public ResponseEntity<String> createBoosterFilter(@RequestBody BoosterFilterRequest boosterFilterRequest){
        boosterFilterService.createBoosterFilter(boosterFilterRequest.getBoosterDTO(),boosterFilterRequest.getLearningTypesDTO());
        return ResponseEntity.ok("Booster Filter created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBoosterFilter(@RequestBody BoosterFilterId boosterFilterId){
        boosterFilterService.deleteBoosterFilter(boosterFilterId);
        return ResponseEntity.ok("Booster Filter deleted.");
    }

    @GetMapping
    public ResponseEntity<BoosterFilterDTO> getBoosterFilter(@RequestBody BoosterFilterId boosterFilterId){
        return ResponseEntity.ok(boosterFilterService.getBoosterFilter(boosterFilterId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BoosterFilterDTO>> getAllBoosterFilters(){
        return ResponseEntity.ok(boosterFilterService.getAllBoosterFilters());
    }
}
