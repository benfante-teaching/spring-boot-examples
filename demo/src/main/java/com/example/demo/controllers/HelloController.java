package com.example.demo.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(path = "/main")
@SessionAttributes({"loggedUser", "prova"})
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    // @RequestMapping(path="/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(@RequestParam(name="_name", defaultValue = "Lucio") String name,
            @RequestParam("id") int id,
            Model model) {
        model.addAttribute("user", name);
        model.addAttribute("id", id);
        model.addAttribute("loggedUser", "Giovanni");
        log.info("{}", model.getAttribute("loggedUser"));
        return "hello";
    }

    @GetMapping("/var/{id}")
    public String helloWithPathVariable(@RequestParam(name="_name", defaultValue = "Lucio") String name,
            @PathVariable(name = "id") int id,            
            Model model,
            @ModelAttribute("loggedUser") String loggedUser) {
        model.addAttribute("user", name);
        model.addAttribute("id", id);
        log.info("{}", loggedUser);
        log.info("prova is {}", model.getAttribute("prova"));
        return "hello";
    }

    @ModelAttribute("prova")
    public String initLoggedUser() {
        log.info("Called initLoggedUser");
        return "Carlo "  + new Date();
    }
}