package com.benfante.javacourse.people_rest.repository;

import org.springframework.data.repository.CrudRepository;
import com.benfante.javacourse.people_rest.model.Person;

public interface PeopleRepository extends CrudRepository<Person, Long> {
}