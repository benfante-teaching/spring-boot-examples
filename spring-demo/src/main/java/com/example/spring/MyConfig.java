package com.example.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppJavaConfiguration.class)
public class MyConfig {
  @Bean
  public List myList() {
    return new ArrayList();
  }
}