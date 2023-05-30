package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class MyConfig {
    
    @Bean
    public List myList() {
        return new ArrayList<>();
    }

    @Bean
    public List anotherList() {
        return new LinkedList<>();
    }

}