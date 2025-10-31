package com.example.teamweave.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI teamWeaveOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TeamWeave API")
                        .description("チームタスク管理APIドキュメント")
                        .version("v1.0"));
    }
}
