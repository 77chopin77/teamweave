package com.example.teamweave.presentation.controller;

import com.example.teamweave.application.port.in.AuthUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authUseCase.signup(request.getEmail(), request.getPassword());
        return "User created successfully";
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        String token = authUseCase.login(request.getEmail(), request.getPassword());
        return new TokenResponse(token);
    }

    // --- 内部DTO ---
    public static class SignupRequest {
        private String email;
        private String password;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginRequest {
        private String email;
        private String password;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class TokenResponse {
        private String token;
        public TokenResponse(String token) { this.token = token; }
        public String getToken() { return token; }
    }
}
