package com.vti.ecommerce.api.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private TokenProvider tokenProvider;

	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String username){
		String token = tokenProvider.generateToken(username);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping
	public String a() {
		String token = tokenProvider.generateToken("bobbibao");
		System.out.println("asd" + token);
		return token;
	}
}
