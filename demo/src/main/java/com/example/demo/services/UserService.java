package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll(String filterByFirstName) {
        if (filterByFirstName == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByFirstNameContaining(filterByFirstName);
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
        
}