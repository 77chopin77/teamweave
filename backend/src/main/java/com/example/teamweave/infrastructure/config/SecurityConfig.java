package com.example.teamweave.infrastructure.config;

import com.example.teamweave.infrastructure.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // パスワードエンコーダーのBean登録
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // セキュリティ設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {

        http
            // CSRF無効化（REST APIでは不要）
            .csrf(csrf -> csrf.disable())

            // セッションを使わない（JWT運用のため）
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // ルートごとのアクセス制御
            .authorizeHttpRequests(auth -> auth
                // 認証不要なエンドポイント
                .requestMatchers(
                    "/test",              // ← ★追加：curlテスト用
                    "/api/test",          // ← 念のため両方
                    "/api/auth/**",       // ← ログイン・サインアップ
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                // それ以外は認証が必要
                .anyRequest().authenticated()
            )

            // JWTフィルターを追加
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
