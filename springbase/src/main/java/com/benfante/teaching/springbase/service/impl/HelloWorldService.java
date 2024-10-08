package com.benfante.teaching.springbase.service.impl;

import org.springframework.stereotype.Service;
import com.benfante.teaching.springbase.service.GreetingService;
import com.benfante.teaching.springbase.service.NameService;

@Service
public class HelloWorldService implements GreetingService {
    
    private NameService nameService;

    public HelloWorldService(NameService nameService) {
        this.nameService = nameService;
    }

    @Override
    public String sayHello(String name) {
        if (name != null && !name.isEmpty()) {
            return "Hello " + name + "!";
        }
        return "Hello " + nameService.getName() + "!";
    }

}