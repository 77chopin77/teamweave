package com.example.teamweave.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 設定クラスを意味

/* ===== Cors(クロスオリジン設定)をカスタマイズしているクラス ===== */
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // 対象URL
                        .allowedOrigins("http://localhost:5173") // フロントのURLからのアクセスを許可
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 許可するHTTPメソッド
                        .allowedHeaders("*") // 全てのヘッダー許可
                        .allowCredentials(true);  // Cookie、認証情報も許可
            }
        };
    }
}
