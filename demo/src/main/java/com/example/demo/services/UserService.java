package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {

    private List<User> archive = List.of(
        new User(1L, "lucio.benfante", "Lucio", "Benfante", 50),
        new User(2L, "mario.rossi", "Mario", "Rossi", 25),
        new User(3L, "giovanna.bianchi", "Giovanna", "Bianchi", 30)
    );

    public Iterable<User> findAll() {
        return archive;
    }

    public Optional<User> findById(Long id) {
        return archive.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User add(User user) {
        archive = new ArrayList<User>(archive);
        archive.add(user);
        return user;
    }
    
}