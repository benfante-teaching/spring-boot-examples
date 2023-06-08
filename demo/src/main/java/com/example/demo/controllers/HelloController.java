package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/")
@SessionAttributes("user")
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("{id}")
    public String hello(@RequestParam(name = "user", defaultValue = "Lucio Benfante") String user,
            @PathVariable(name="id") int id, Model model) {
        log.info("Responding to the request on the main path!!!");
        log.info("The value of the parameter id is {}", id);
        model.addAttribute("name", user);
        return "example/hello";
    }
}