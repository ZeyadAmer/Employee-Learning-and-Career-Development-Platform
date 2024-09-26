package org.example.Controllers;

import org.example.Entities.User;
import org.example.Mappers.UserDTO;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("User Created");
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
    public ResponseEntity<UserDTO> getUser(@RequestBody String email,@RequestBody String password) {
        return ResponseEntity.ok(userService.getUser(email,password));
    }
    @PutMapping("/{email}")
    public ResponseEntity<String> updateUserPassword(@PathVariable String email,@RequestBody String password) {
        userService.updateUserPassword(email,password);
        return ResponseEntity.ok("password updated successfully");
    }
    @PutMapping("/{title}")
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
