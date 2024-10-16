package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.mapper.CommentDTO;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // for CORS
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO){
        commentService.createComment(commentDTO);
        String jsonResponse = String.format("{\"message\": \"%s\"}", "Comment created.");
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable int id) throws JsonProcessingException {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/all/{submittedCareerPackageId}")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable int submittedCareerPackageId){
        return ResponseEntity.ok(commentService.getAllComments(submittedCareerPackageId));
    }
}
