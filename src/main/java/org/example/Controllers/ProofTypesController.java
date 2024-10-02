package org.example.Controllers;

import org.example.Mappers.ProofTypesDTO;
import org.example.Services.ProofTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/proofTypes")
public class ProofTypesController {
    @Autowired
    private ProofTypesService proofTypesService;

    @PostMapping
    public ResponseEntity<String> createProofType(@RequestBody ProofTypesDTO proofTypesDTO){
        proofTypesService.createProofType(proofTypesDTO);
        return ResponseEntity.ok("Proof Type created.");
    }

    @DeleteMapping("/{proofTypeName}")
    public ResponseEntity<String> deleteProofType(@PathVariable String proofTypeName){
        proofTypesService.deleteProofType(proofTypeName);
        return ResponseEntity.ok("Proof Type deleted.");
    }

    @PostMapping("/{oldName}")
    public ResponseEntity<String> updateProofType(@PathVariable String oldName ,@RequestBody String newName){
        proofTypesService.updateProofType(oldName,newName);
        return ResponseEntity.ok("Proof Type updated.");
    }

    @GetMapping("/{proofTypeName}")
    public ResponseEntity<ProofTypesDTO> getProofType(@PathVariable String proofTypeName){
        return ResponseEntity.ok(proofTypesService.getProofType(proofTypeName));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProofTypesDTO>> getAllProofTypes(){
        return ResponseEntity.ok(proofTypesService.getAllProofTypes());
    }
}
