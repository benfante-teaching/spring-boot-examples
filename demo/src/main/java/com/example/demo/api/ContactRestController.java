package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/contacts")
public class ContactRestController {

    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public Iterable<Contact> getAllContacts(@RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName) {
        return contactService.getAllContacts(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable("id") Long id) {
         Optional<Contact> result = contactService.findById(id);
/* *** Verbose method!!
         if (!result.isEmpty()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }        
 */

        /* Less Verboese
        return ResponseEntity.of(result);
        */

        /* Using un exception handler */
        return result.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.save(contact);
    }
    
    @PatchMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        return contactService.save(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}