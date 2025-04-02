package com.example.esercizio_due.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lucio
 */
@Controller
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", defaultValue = "Thymeleaf") String name, Model model) {
        log.debug("Il controller hello è stato chiamato!!!");
        model.addAttribute("destination", name);
        return "hello";
    }
}
