package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo") 
public class HomeController {

    @GetMapping(path = "/list")
    public String home(
        @RequestParam(name = "message", defaultValue = "Hello Spring Boot Todos (dyn)") String message,
        @RequestParam(name = "age", defaultValue = "0") int age,
        Model model) {
        model.addAttribute("message", message);
        model.addAttribute("age", age);
        return "todo/index";
    }

}
