package com.example.teamweave.application.port.out;

import java.util.UUID;

/* 認証トークン（JWTなど）の発行、検証を行うための「出力ポート」 */
public interface TokenProviderPort {
    // トークンを生成する
    String generateToken(UUID userId);

    // トークンを検証して、ユーザーIDを取り出す
    String validateAndGetUserId(String token);
}
