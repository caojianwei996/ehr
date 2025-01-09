package com.neusoft.ehr.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * @author 曹健伟
 * <p>
 * OpenApi配置类
 */
@Configuration
public class OpenApiConfiguration {
    /**
     * OpenAPI配置
     *
     * @return OpenAPI Bean
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                // 基本信息
                .info(
                        new Info()
                                .title("EHR Project")
                                .description("EHR Project OpenAPI Documentation")
                                .version("1.0.0-SNAPSHOT")
                )
                // 安全管理
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(HttpHeaders.AUTHORIZATION)
                )
                // 组件管理
                .components(
                        new Components()
                                // Token格式
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
