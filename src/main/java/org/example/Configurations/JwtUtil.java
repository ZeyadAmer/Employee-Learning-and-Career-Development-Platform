package org.example.Configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_secret_key"; // Change this to a secure key
    private final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(String email, String departmentName,boolean isManager, int userId) {
        // Determine role based on department name
        String role = departmentName.equalsIgnoreCase("HR") ? "ROLE_ADMIN" : "ROLE_USER";
        if(role.equals("ROLE_USER") && isManager){
            role = "ROLE_MANAGER";
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);  // Add userId as an int to the token

        // Create the JWT token using the claims and other parameters
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) extractClaims(token).get("role");
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    public int extractUserId(String token) {
        return (int) extractClaims(token).get("userId");  // Extract userId as an int
    }



    public boolean validateToken(String token, String email) {
        String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }
}
