package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;

@Controller
@RequestMapping("/contacts")
@SessionAttributes("user")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public String list(Model model) {
        Iterable<Contact> contacts = contactService.getAllContacts("", "");
        model.addAttribute("contacts", contacts);
        return "contacts/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Contact> contact = contactService.findById(id);
        if (contact.isPresent()) {
            model.addAttribute("contact", contact.get());
        } else {
            model.addAttribute("contact", new Contact(-1L, "", "", "", 0));
        }
        return "contacts/edit";
    }

    @PostMapping
    public String save(@ModelAttribute Contact contact, SessionStatus status) {
        log.info("Submitted by the form {}", contact);
        contactService.save(contact);
        status.setComplete();
        return "redirect:/contacts";
    }

    @ModelAttribute("user")
    public String defaultUser() {
        log.info("Generating default user");
        return "Lucio";
    }
}