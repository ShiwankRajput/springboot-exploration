package com.RecipeBook.RecipeBookApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.RecipeBook.RecipeBookApplication.dto.AuthResponse;
import com.RecipeBook.RecipeBookApplication.dto.LoginRequest;
import com.RecipeBook.RecipeBookApplication.dto.RegisterRequest;
import com.RecipeBook.RecipeBookApplication.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recipeBook/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            AuthResponse response = new AuthResponse(null, null, errorMessage);
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            String token = userService.register(registerRequest);
            AuthResponse response = new AuthResponse(token, registerRequest.getUsername(), "User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(null, null, e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            AuthResponse response = new AuthResponse(null, null, errorMessage);
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            String token = userService.login(loginRequest);
            AuthResponse response = new AuthResponse(token, loginRequest.getUsername(), "Login successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(null, null, e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Auth service is healthy!");
    }
}