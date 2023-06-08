package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Contact;

public interface ContactRepository  extends CrudRepository<Contact, Long> {
    
    Iterable<Contact> findAllByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
}