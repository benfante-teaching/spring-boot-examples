package com.example.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySchool {

    @Autowired
    private MyClass cls;

    @Override
    public String toString() {
        return "This is my MyClass object: %s".formatted(cls);
    }

    
}