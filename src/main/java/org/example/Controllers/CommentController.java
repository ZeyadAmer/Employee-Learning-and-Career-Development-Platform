package org.example.Controllers;

import org.example.Classes.SubmittedCareerPackage;
import org.example.Mappers.CommentDTO;
import org.example.Mappers.SubmittedCareerPackageDTO;
import org.example.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO){
        commentService.createComment(commentDTO);
        return ResponseEntity.ok("comment created");
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable int id){
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@RequestBody SubmittedCareerPackage submittedCareerPackage){
        return ResponseEntity.ok(commentService.getAllComments(submittedCareerPackage));
    }
}
