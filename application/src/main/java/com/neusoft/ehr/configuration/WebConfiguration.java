package com.neusoft.ehr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 曹健伟
 * <p>
 * Web配置类
 */
@Configuration
public class WebConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedOrigins(CorsConfiguration.ALL);
            }
        };
    }
}
