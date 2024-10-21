package com.example.demo_database.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_database.model.Person;
import com.example.demo_database.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping
    public Page<Person> getPeople(@RequestParam(name = "q", defaultValue = "") String q, @ParameterObject Pageable pageable) {
        return personRepository.findByUsernameContainingOrFirstNameContainingOrLastNameContainingAllIgnoreCase(q, q, q, pageable);
    }

}