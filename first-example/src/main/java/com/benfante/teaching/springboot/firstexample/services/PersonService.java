package com.benfante.teaching.springboot.firstexample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.benfante.teaching.springboot.firstexample.model.Person;

@Service
public class PersonService {
    
    private List<Person> data = List.of(
        new Person(1L, "Mario", "Rossi"),
        new Person(2L, "Giovanna", "Bianchi"),
        new Person(3L, "Carlo", "Neri"),
        new Person(4L, "Laura", "Biachi")
    );

    public List<Person> getAll() {
        return this.data;
    }

    public Optional<Person> findById(Long id) {
        return this.data.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
}