package com.example.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyClass {
    @Autowired @Qualifier("anotherList")
    private List l;

    public String toString() {
        return "Questa è la mia classe con lista di tipo %s".formatted(l.getClass().getName());
    }
}