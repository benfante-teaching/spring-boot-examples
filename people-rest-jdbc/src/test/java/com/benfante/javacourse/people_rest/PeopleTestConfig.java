package com.benfante.javacourse.people_rest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.benfante.javacourse.people_rest.repository.InMemoryPeopleRepository;
import com.benfante.javacourse.people_rest.repository.PeopleRepository;

@TestConfiguration
public class PeopleTestConfig {
    
    @Bean
    @Primary
    public PeopleRepository peopleRepository() {
        return new InMemoryPeopleRepository();
    }
}