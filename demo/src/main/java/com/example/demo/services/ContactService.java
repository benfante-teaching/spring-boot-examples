package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Contact;

@Service
public class ContactService {

    private List<Contact> archive = new ArrayList<>(List.of(
        new Contact(1L, "Mario", "Rossi", "123456", 30),
        new Contact(2L, "Giovanna", "Bianchi", "123333453563463456", 25),
        new Contact(3L, "Sandra", "Neri", "111111123456", 34)));
    
    public List<Contact> getAllContacts() {
        return archive;
    }

    public Optional<Contact> findById(Long id) {
        return archive.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

    public Contact save(Contact contact) {
        this.archive = new ArrayList<>(archive.stream().filter(item -> !item.getId().equals(contact.getId())).toList());
        this.archive.add(contact);
        return contact;
    }

    public void deleteContact(Long id) {
        this.archive = new ArrayList<>(archive.stream().filter(item -> !item.getId().equals(id)).toList());
    }
}