package com.example.mealprepapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI mealPrepApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meal Prep API")
                        .description("API for meal preparation application")
                        .version("1.0"));
    }
}
