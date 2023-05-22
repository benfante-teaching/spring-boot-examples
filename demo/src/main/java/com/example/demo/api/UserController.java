package com.example.demo.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }
    
    // @GetMapping("/{id}")
    // public ResponseEntity<User> getById(@PathVariable Long id) {
    //     Optional<User> user = userService.findById(id);
    //     if (user.isPresent()) {
    //         return ResponseEntity.ok().body(user.get());
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }        
    // }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }
}