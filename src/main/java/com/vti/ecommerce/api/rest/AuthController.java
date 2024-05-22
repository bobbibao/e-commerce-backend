package com.vti.ecommerce.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vti.ecommerce.config.jwt.TokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Authentication: " + passwordEncoder.encode("1233214321"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        System.out.println("Authentication2");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication.getName());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getShoppingCart")
    public ResponseEntity<?> getShoppingCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Username: " + username);
        // Logic để lấy giỏ hàng của người dùng hiện tại dựa trên username
        Map<String, Object> cart = new HashMap<>();
        cart.put("item1", "Apple");
        cart.put("item2", "Banana");

        return ResponseEntity.ok(cart);
    }
}

class LoginRequest {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("Username: " + username);
        this.username = username;
    }

    public String getPassword() {
        System.out.println("Password: " + password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
