package com.example.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClass {
    @Autowired
    private List l;

    public String toString() {
        return "Has a %s list".formatted(l.getClass());
    }
}