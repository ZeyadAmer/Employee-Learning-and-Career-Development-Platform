package org.example.Controllers;

import org.example.Entities.LoginRequest;
import org.example.Entities.User;
import org.example.Mappers.UserDTO;
import org.example.Services.UserService;
import org.example.Configurations.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserID(loginRequest.getEmail(), loginRequest.getPassword());

        String token = jwtUtil.generateToken(user.getEmail(),user.getTitle().getDepartment().getName(),user.getId());
        return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
    }
    @GetMapping
    public ResponseEntity<String> isAdmin(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        String role = jwtUtil.extractRole(token);
        return ResponseEntity.ok("{\"role\":\"" + role + "\"}");
    }


}
