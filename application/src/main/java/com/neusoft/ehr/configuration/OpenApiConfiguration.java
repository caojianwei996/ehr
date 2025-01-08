package com.neusoft.ehr.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("EHR Project")
                                .description("EHR Project OpenAPI Documentation")
                                .version("1.0.0-SNAPSHOT")
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(HttpHeaders.AUTHORIZATION)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        HttpHeaders.AUTHORIZATION,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .description("Token")
                                                .name(HttpHeaders.AUTHORIZATION)
                                                .in(SecurityScheme.In.HEADER)
                                                .scheme("Bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
