package com.benfante.teaching.springboot.firstexample.controllers.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfante.teaching.springboot.firstexample.model.Person;
import com.benfante.teaching.springboot.firstexample.services.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping()
    private List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    private Person getById(@PathVariable Long id) {
        return personService.findById(id).get();        
    }
}