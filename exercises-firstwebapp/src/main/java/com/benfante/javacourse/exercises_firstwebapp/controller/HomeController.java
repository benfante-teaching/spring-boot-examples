package com.benfante.javacourse.exercises_firstwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @GetMapping("/")
    // @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(@RequestParam(name = "name", defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
}