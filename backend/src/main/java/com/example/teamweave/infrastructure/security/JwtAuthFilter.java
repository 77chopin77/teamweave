package com.example.teamweave.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/* JWTトークンを検証し、認証済みユーザーをSecurityContextに登録するリクエストフィルター */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // JWTの生成・検証を行うクラス
    private final JwtProvider jwtProvider;

    // DI注入
    public JwtAuthFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    // リクエストごとにJWTを検証して認証情報を設定
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer "を除去してトークン抽出

            // トークンを検証してユーザーID取得
            try {
                String userId = jwtProvider.validateAndGetUserId(token);

                // 認証済みユーザーとしてSecurityContextに登録
                var auth = new UsernamePasswordAuthenticationToken(UUID.fromString(userId), null, null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (SecurityException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // レスポンスのステータスコードを 401 Unauthorized に設定する
                response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
                return;
            }
        }

        // 次のフィルターへ処理を渡す
        chain.doFilter(request, response);
    }
}
