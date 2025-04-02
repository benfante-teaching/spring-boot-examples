package com.example.esercizio_due.controller;

import com.example.esercizio_due.model.Person;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author lucio
 */
@Controller
public class PeopleController {
    
    private List<Person> data = List.of(
            new Person("Lucio", "Benfante"),
            new Person("Maria", "Verdi"),
            new Person("Mario", "Rossi")
    );
    
    @GetMapping("/people")
    public String list(Model model) {
        model.addAttribute("people", data);
        return "people/list";
    }
}
