package com.example.teamweave.presentation.controller;

import com.example.teamweave.application.port.in.AuthUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase auth;

    public AuthController(AuthUseCase auth) {
        this.auth = auth;
    }

    // ✅ ユーザー登録
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        auth.signup(req.email(), req.password());
        return ResponseEntity.ok("User created");
    }

    // ✅ ログイン（JWTを発行）
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {
        String token = auth.login(req.email(), req.password());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    // --- DTO定義（recordでOK） ---
    public record SignupRequest(String email, String password) {}
    public record LoginRequest(String email, String password) {}
    public record TokenResponse(String token) {}
}
