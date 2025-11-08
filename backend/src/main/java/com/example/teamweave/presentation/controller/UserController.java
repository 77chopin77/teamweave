package com.example.teamweave.presentation.controller;

import com.example.teamweave.infrastructure.security.JwtProvider;
import com.example.teamweave.infrastructure.persistence.UserJpaRepository;
import com.example.teamweave.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JwtProvider jwtProvider;
    private final UserJpaRepository userRepository;

    public UserController(JwtProvider jwtProvider, UserJpaRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Missing or invalid token"));
        }

        try {
            String token = authHeader.substring(7);
            String userId = jwtProvider.validateAndGetUserId(token);

            UserJpaEntity user = userRepository.findById(UUID.fromString(userId))
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // ✅ パスワードハッシュを除外したレスポンス
            return ResponseEntity.ok(Map.of(
                    "id", user.getId(),
                    "email", user.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}
