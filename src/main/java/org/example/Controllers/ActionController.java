package org.example.Controllers;

import org.example.Mappers.ActionDTO;
import org.example.Services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping
    public ResponseEntity<String> createAction(@RequestBody ActionDTO actionDTO){
        actionService.createAction(actionDTO);
        return ResponseEntity.ok("Action created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAction(@RequestBody int id){
        actionService.deleteAction(id);
        return ResponseEntity.ok("Action deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAction(@PathVariable int id, @RequestBody String newName){
        actionService.updateAction(id,newName);
        return ResponseEntity.ok("Action updated");
    }

    @GetMapping
    public ResponseEntity<List<ActionDTO>> getAllActions(){
        return ResponseEntity.ok(actionService.getAllActions());
    }

}
