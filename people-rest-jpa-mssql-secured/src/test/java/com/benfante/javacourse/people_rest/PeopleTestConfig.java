package com.benfante.javacourse.people_rest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.benfante.javacourse.people_rest.security.JwtService;
import com.benfante.javacourse.people_rest.service.PeopleService;

@TestConfiguration
public class PeopleTestConfig {

    @Bean
    public PeopleService peopleService() {
        return new PeopleService();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}