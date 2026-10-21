package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Person;

@Controller
@RequestMapping("/person")
public class PersonController {

    private Person person = new Person(1, "Lucio");

    @GetMapping("/form")
    public String formPerson(Model model) {
        model.addAttribute("person", person);
        return "person/form";
    }

    @PostMapping("/update")
    public String updatePerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes, Model model) {
        this.person = person;
        if (person.getName().length() < 3) {
            model.addAttribute("error", "Name must be at least 3 characters long");
            return "person/form";
        }
        redirectAttributes.addFlashAttribute("message", "Person updated successfully");
        return "redirect:/person/form";
    }
}
