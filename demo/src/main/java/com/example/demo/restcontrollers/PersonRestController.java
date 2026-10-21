package com.example.demo.restcontrollers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Person;

@RestController 
@RequestMapping("/api/v1/people")
public class PersonRestController {

    private Person person = new Person(1, "Lucio");

    @GetMapping
    public List<Person> list() {
        return List.of(person);
    }

    @GetMapping("/{id}")
    public Optional<Person> person(@PathVariable int id) {
        if (id != 1) {
            throw new NoSuchElementException("Person not found");                
        }
        return Optional.of(person);
    }

}
