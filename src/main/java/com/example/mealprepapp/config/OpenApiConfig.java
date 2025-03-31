package com.example.mealprepapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Meal Prep API",
        version = "1.0",
        description = "API Documentation for Meal Prep Application"
    )
)
public class OpenApiConfig {
} 