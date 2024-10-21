package com.example.demo_database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.example.demo_database.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Iterable<Person> findByUsername(String query);
    Page<Person> findByUsernameContainingOrFirstNameContainingOrLastNameContainingAllIgnoreCase(String username, String firstName, String lastName, Pageable pageable);
}