package com.benfante.javacourse.people_rest.repository;

import java.util.Optional;
import com.benfante.javacourse.people_rest.model.Person;

public interface PeopleRepository {
    Iterable<Person> findAll();
    Optional<Person> findById(Long id);
    Person save(Person person);
    void deleteById(Long id);
    void delete(Person person);
}