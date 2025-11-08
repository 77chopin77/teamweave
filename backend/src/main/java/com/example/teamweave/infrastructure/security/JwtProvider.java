package com.example.teamweave.infrastructure.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

@Component
public class JwtProvider {

    private static final String SECRET = "your-secret-key-change-this-to-a-long-random-string";
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24時間
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /** ✅ トークン生成 */
    public String generateToken(UUID userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** ✅ トークン検証＆UserId取得 */
    public String validateAndGetUserId(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody().getSubject(); // userId(UUID文字列)
        } catch (ExpiredJwtException e) {
            throw new SecurityException("Token expired");
        } catch (JwtException e) {
            throw new SecurityException("Invalid token");
        }
    }
}
