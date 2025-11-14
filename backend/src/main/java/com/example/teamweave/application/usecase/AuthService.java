package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.in.AuthUseCase;
import com.example.teamweave.application.port.out.UserRepositoryPort;
import com.example.teamweave.application.port.out.TokenProviderPort;
import com.example.teamweave.domain.model.User;
import com.example.teamweave.domain.model.UserId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;



@Service
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort users;
    private final PasswordEncoder encoder;
    private final TokenProviderPort tokenProvider;

    public AuthService(UserRepositoryPort users, PasswordEncoder encoder, TokenProviderPort tokenProvider) {
        this.users = users;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    // DBに同じメールがすでにあったら、エラーを投げる
    @Override
    public void signup(String email, String password) {
        if (users.findByEmail(email).isPresent())
            throw new IllegalArgumentException("Email already exists");

        // 新しいユーザーを作る
        var user = new User(new UserId(UUID.randomUUID()), email, encoder.encode(password));
        users.save(user);
    }

    // 入力されたメールアドレスでユーザーを検索
    @Override
    public String login(String email, String password) {
        var user = users.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // 入力されたパスワードと、DBに保存されているハッシュ値を照合
        if (!encoder.matches(password, user.getPasswordHash()))
            throw new IllegalArgumentException("Invalid credentials");

        // ユーザーIDをもとにJWTトークンを作り、それを返す
        return tokenProvider.generateToken(user.getId().value());
    }
}
