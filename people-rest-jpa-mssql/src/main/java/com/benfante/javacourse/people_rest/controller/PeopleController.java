package com.benfante.javacourse.people_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.benfante.javacourse.people_rest.model.Person;
import com.benfante.javacourse.people_rest.service.PeopleService;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping
    public Iterable<Person> findAll() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return peopleService.findById(id)
            .map(person -> ResponseEntity.ok(person))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@RequestBody Person person) {
        return peopleService.save(person);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        if (person.getId() != null && !id.equals(person.getId())) {
            return ResponseEntity.badRequest().build();
        }
        person.setId(id);
        return ResponseEntity.ok().body(peopleService.update(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        peopleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}