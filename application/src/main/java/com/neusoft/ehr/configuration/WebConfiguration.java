package com.neusoft.ehr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 曹健伟
 * <p>
 * Web配置类
 */
@Configuration
public class WebConfiguration {
    /**
     * Web配置
     *
     * @return WebMVC配置类
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(HandlerInterceptor[] interceptors) {
        return new WebMvcConfigurer() {
            /**
             * 后端处理跨域
             * @param registry 跨域注册链
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedOrigins(CorsConfiguration.ALL);
            }

            /**
             * 拦截器链
             * @param registry 拦截器注册链
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                for (HandlerInterceptor interceptor : interceptors) {
                    registry.addInterceptor(interceptor).addPathPatterns("/**");
                }
            }
        };
    }
}
