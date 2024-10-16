package org.example.Controllers;


import org.example.Mappers.UserLearningDTO;
import org.example.Services.UserLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.Configurations.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userLearning")
public class UserLearningController {

    @Autowired
    private UserLearningService userLearningService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> createUserLearning(@RequestBody UserLearningDTO userLearningDTO, HttpServletRequest request) {
        // Logging to check the incoming DTO
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        int id = jwtUtil.extractUserId(token);
        userLearningDTO.setUserId(id);
        userLearningService.createUserLearning(userLearningDTO);
        return ResponseEntity.ok("{\"response\":\"" + "User Learning created" + "\"}");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserLearning(@RequestBody int id){
        userLearningService.deleteUserLearning(id);
        return ResponseEntity.ok("{\"response\":\"" + "User Learning deleted" + "\"}");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserLearning(@PathVariable int id,@RequestBody UserLearningDTO userLearningDTO){
        userLearningService.updateUserLearning(id, userLearningDTO);
        return ResponseEntity.ok("{\"response\":\"" + "User Learning updated" + "\"}");
    }

    @GetMapping
    public ResponseEntity<List<UserLearningDTO>> getUserLearning(
            @RequestParam int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "300") int size) {
        List<UserLearningDTO> learnings = userLearningService.getAllUserLearnings(id, page, size);
        return ResponseEntity.ok(learnings);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserLearningDTO>> getAllUserLearnings(
            HttpServletRequest request ,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "300") int size) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        int id = jwtUtil.extractUserId(token);
        List<UserLearningDTO> learnings = userLearningService.getAllUserLearnings(id, page, size);

        // Log the result before returning
        System.out.println("User Learnings: " + learnings); // Debugging

        return ResponseEntity.ok(learnings);
    }
    @PutMapping("/approve")
    public ResponseEntity<String> approveUserLearning(@RequestBody int id){
        userLearningService.approveUserLearning(id);
        return ResponseEntity.ok("{\"response\":\"" + "User Learning approved" + "\"}");
    }
    @PutMapping("/reject")
    public ResponseEntity<String> rejectUserLearning(@RequestBody int id){
        userLearningService.rejectUserLearning(id);
        return ResponseEntity.ok("{\"response\":\"" + "User Learning rejected" + "\"}");
    }
    @GetMapping("/pending")
    public ResponseEntity<List<UserLearningDTO>> getPendingUserLearnings(@RequestParam int id){
        return ResponseEntity.ok(userLearningService.getAllPending(id));
    }
}
