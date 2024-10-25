package com.benfante.javacourse.people_rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Admincontroller {
    
    @GetMapping
    public String admin() {
        return "Hello Admin!";
    }
}