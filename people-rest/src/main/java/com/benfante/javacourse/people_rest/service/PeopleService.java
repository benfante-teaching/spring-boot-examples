package com.benfante.javacourse.people_rest.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benfante.javacourse.people_rest.model.Person;
import com.benfante.javacourse.people_rest.repository.PeopleRepository;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public Iterable<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return peopleRepository.findById(id);
    }

    public Person save(Person person) {
        return peopleRepository.save(person);
    }

    public Person update(Person person) {
        return peopleRepository.save(person);
    }
    
    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }
    
}