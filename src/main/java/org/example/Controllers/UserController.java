package org.example.Controllers;

import org.example.Entities.LoginRequest;
import org.example.Configurations.JwtUtil;
import org.example.Mappers.UserDTO;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @PostMapping("/{managerEmail}")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO, @PathVariable String managerEmail) {
        userService.createUser(userDTO,managerEmail);
        return ResponseEntity.ok("{\"token\":\"" + userDTO + "\"}");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User Deleted");
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping
    public ResponseEntity<UserDTO> getUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        int id = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(userService.getUser(id));
    }
    @PutMapping("/reset-pass/{email}")
    public ResponseEntity<String> updateUserPassword(@PathVariable String email,@RequestBody String password) {
        userService.updateUserPassword(email,password);
        return ResponseEntity.ok("password updated successfully");
    }
    @PutMapping("/title/{title}")
    public ResponseEntity<String> updateUserTitle(@PathVariable String title,@RequestBody String email) {
        userService.updateUserTitle(email,title);
        return ResponseEntity.ok("title updated successfully");
    }
    @PutMapping("/{managerEmail}/{email}")
    public ResponseEntity<String> updateUserManager(@PathVariable String managerEmail,@PathVariable String email) {
        userService.updateUserManager(email,managerEmail);
        return ResponseEntity.ok("manager updated successfully");
    }

}
