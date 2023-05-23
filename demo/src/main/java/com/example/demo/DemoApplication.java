package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    public OpenAPI configOpenApi(
            @Value("${spring.application.name}") String name,
            @Value("${app.version}") String version,
            @Value("${app.description}") String description) {
        return new OpenAPI().info(new Info().title(name).version(version).description(description)
                .termsOfService("http://pmidashboard.it/terms/")
                .license(new License().name("Apache License, Version 2.0").identifier("Apache-2.0")
                        .url("https://opensource.org/license/apache-2-0/")));
    }	

}
