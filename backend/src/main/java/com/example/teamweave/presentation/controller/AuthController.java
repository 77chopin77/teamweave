package com.example.teamweave.presentation.controller;

import com.example.teamweave.application.port.in.AuthUseCase;
import org.springframework.web.bind.annotation.*;


/*  ===== 「サインアップ」、「ログイン」を扱う、認証APIの窓口 =====  */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // 依存関係が逆転する（アプリケーション層のインターフェースを呼び出していて、実装（AuthService）を知らないから）
    private final AuthUseCase authUseCase;

    // フィールドと依存注入
    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    // ユーザー登録
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authUseCase.signup(request.getEmail(), request.getPassword());
        return "User created successfully";
    }

    // ログイン
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        String token = authUseCase.login(request.getEmail(), request.getPassword());
        return new TokenResponse(token);
    }

    // 内部DTO(データの入れ物) 
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
