package com.example.teamweave.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* Spring Boot アプリに Swaggerを設定するクラス */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI teamWeaveOpenAPI() {

        // セキュリティスキーム（認証方式）の名前を定義
        final String schemeName = "bearerAuth";

        // OpenAPIオブジェクトを構築
        return new OpenAPI()
            .info(new Info().title("TeamWeave API").version("1.0")) // SwaggerUIのタイトル
            .components(new Components().addSecuritySchemes(
                schemeName, // // "bearerAuth" という名前で登録
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)     // HTTPヘッダーで認証
                    .scheme("bearer")           // Bearerトークン方式 
                    .bearerFormat("JWT")  // トークン形式をJWTと明示
            ))

            // 認証方式を全APIエンドポイントに適用
            .addSecurityItem(new SecurityRequirement().addList(schemeName));
    }
}

