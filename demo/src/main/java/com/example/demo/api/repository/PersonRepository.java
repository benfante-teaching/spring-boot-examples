package com.example.demo.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.api.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
