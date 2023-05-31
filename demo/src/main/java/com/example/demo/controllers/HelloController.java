package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    
    @GetMapping("/")
    public String hello(Model model) {
        log.info("Responding to the request on the main path!!!");
        model.addAttribute("name", "Lucio Benfante");
        return "example/hello";
    }
}