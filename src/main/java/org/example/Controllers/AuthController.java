package org.example.Controllers;

import org.example.Entities.LoginRequest;
import org.example.Mappers.UserDTO;
import org.example.Services.UserService;
import org.example.Configurations.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserDTO user = userService.getUser(loginRequest.getEmail(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(user.getEmail(),user.getTitle().getDepartment().getName());
        return ResponseEntity.ok(token);
    }
}
