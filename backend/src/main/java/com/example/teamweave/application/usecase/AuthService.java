package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.in.AuthUseCase;
import com.example.teamweave.infrastructure.persistence.UserJpaRepository;
import com.example.teamweave.infrastructure.persistence.entity.UserJpaEntity;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService implements AuthUseCase {
    private final UserJpaRepository users;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserJpaRepository users, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.users = users;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void signup(String email, String password) {
        if (users.findByEmail(email).isPresent())
            throw new IllegalArgumentException("Email already exists");

        var user = new UserJpaEntity();
        user.setId(UUID.randomUUID()); // ✅ UUID型で保存
        user.setEmail(email);
        user.setPasswordHash(encoder.encode(password));
        users.save(user);
    }

    @Override
    public String login(String email, String password) {
        var entity = users.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!encoder.matches(password, entity.getPasswordHash()))
            throw new IllegalArgumentException("Invalid credentials");

        return jwtProvider.generateToken(entity.getId()); // ✅ 1引数に修正
    }
}
