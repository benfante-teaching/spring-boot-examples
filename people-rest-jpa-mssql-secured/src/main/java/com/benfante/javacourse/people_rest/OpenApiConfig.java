package com.benfante.javacourse.people_rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI configOpenApi(@Value("${spring.application.name}") String name,
            @Value("${yrs-api.api.version:v1.0}") String version,
            @Value("${yrs-api.description:A simple REST API}") String description) {
        return new OpenAPI().info(new Info().title(name).version(version).description(description)
                .termsOfService("https://github.com/benfante-teaching/spring-boot-examples/tree/current_04?tab=License-1-ov-file#")
                .license(new License().name("Apache License, Version 2.0").identifier("Apache-2.0")
                        .url("https://opensource.org/license/apache-2-0/")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication",
                        createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT")
                .scheme("bearer");
    }
}