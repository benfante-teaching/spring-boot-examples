package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Iterable<Contact> getAllContacts(String firstName, String lastName) {
        return contactRepository.findAllByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Contact save(Contact contact) {
        if (contact.getId() != null) {
            Contact dbContact = contactRepository.findById(contact.getId()).get();
            if (contact.getFirstName() != null) {
                dbContact.setFirstName(contact.getFirstName());
            }
            if (contact.getLastName() != null) {
                dbContact.setLastName(contact.getLastName());
            }
            if (contact.getPhone() != null) {
                dbContact.setPhone(contact.getPhone());
            } 
            return dbContact;
        } else {
            return contactRepository.save(contact);
        }
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}