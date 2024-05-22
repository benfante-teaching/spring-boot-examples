package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.model.Person;
import com.example.demo.api.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public Iterable<Person> getPeople() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable(name="id", required = true) Long id) {
		return personRepository.findById(id).get();
	}
}
