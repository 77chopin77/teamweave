package com.example.teamweave.infrastructure.security;

import com.example.teamweave.application.port.out.TokenProviderPort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

/* JWTの生成・検証を行い、ユーザー認証に使用するクラス */
@Component
public class JwtProvider implements TokenProviderPort { 

    private static final String SECRET = "your-secret-key-change-this-to-a-long-random-string"; // 署名用シークレットキー
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24時間
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); // 秘密鍵生成

    // トークン生成処理
    @Override
    public String generateToken(UUID userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);
        return Jwts.builder()
                .setSubject(userId.toString()) // トークンの主体としてuserIdを設定
                .setIssuedAt(now)              // 発行日時
                .setExpiration(expiry)         // 有効期限
                .signWith(key, SignatureAlgorithm.HS256) // 著名アルゴリズム指定
                .compact();                    // トークンを作成
    }

    // トークン検証とユーザーIDの取得処理
    @Override
    public String validateAndGetUserId(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder() 
                    .setSigningKey(key) // 署名検証用キー設定
                    .build()
                    .parseClaimsJws(token); // トークンを解析して署名を検証
            return claims.getBody().getSubject(); // トークン内のuserId(UUID文字列)を返す
        } catch (ExpiredJwtException e) {
            throw new SecurityException("Token expired"); // トークン期限切れ
        } catch (JwtException e) {
            throw new SecurityException("Invalid token"); // 不正なトークン
        }
    }
}
