package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findByFirstNameContaining(String firstName);
}