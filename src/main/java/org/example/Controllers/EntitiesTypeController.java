package org.example.Controllers;


import org.example.Mappers.EntitiesTypeDTO;
import org.example.Services.EntitiesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entitiesType")
public class EntitiesTypeController {
    @Autowired
    private EntitiesTypeService entitiesTypeService;

    @PostMapping
    public ResponseEntity<String> createEntitiesType(@RequestBody EntitiesTypeDTO entitiesTypeDTO){
        entitiesTypeService.createEntitiesType(entitiesTypeDTO);
        return ResponseEntity.ok("Entities Type created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEntitiesType(@RequestBody String name){
        entitiesTypeService.deleteEntitiesType(name);
        return ResponseEntity.ok("Entities Type deleted");
    }

    @PutMapping("/{oldName}")
    public ResponseEntity<String> updateAction(@PathVariable String oldName, @RequestBody String newName){
        entitiesTypeService.updateEntitiesType(oldName,newName);
        return ResponseEntity.ok("Entites Type updated");
    }

    @GetMapping
    public ResponseEntity<List<EntitiesTypeDTO>> getAllEntitiesTypes(){
        return ResponseEntity.ok(entitiesTypeService.getAllEntitiesTypes());
    }
}
