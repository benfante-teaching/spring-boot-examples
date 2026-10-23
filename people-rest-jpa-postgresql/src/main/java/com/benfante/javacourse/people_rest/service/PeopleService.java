package com.benfante.javacourse.people_rest.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.benfante.javacourse.people_rest.model.Person;
import com.benfante.javacourse.people_rest.repository.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public Iterable<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return peopleRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Person save(Person person) {
        return peopleRepository.save(person);
    }

    @Transactional(readOnly = false)
    public Person update(Person person) {
        Person current = peopleRepository.findById(person.getId()).get();
        if (person.getFirstName() != null) {
            current.setFirstName(person.getFirstName());
        }
        if (person.getLastName() != null) {
            current.setLastName(person.getLastName());
        }
        if (person.getEmail() != null) {
            current.setEmail(person.getEmail());
        }
        if (person.getCity() != null) {
            current.setCity(person.getCity());
        }
        return current;
    }
    
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }
    
}