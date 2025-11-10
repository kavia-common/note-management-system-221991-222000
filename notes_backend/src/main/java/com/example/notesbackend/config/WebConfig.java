package com.example.notesbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration: CORS and OpenAPI metadata.
 */
@Configuration
public class WebConfig {

    // PUBLIC_INTERFACE
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // Permissive CORS for local development
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes Backend API")
                        .version("0.1.0")
                        .description("Simple CRUD API for managing notes with pagination and search"));
    }
}
