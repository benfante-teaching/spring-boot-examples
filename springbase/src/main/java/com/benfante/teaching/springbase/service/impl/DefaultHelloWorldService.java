package com.benfante.teaching.springbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.benfante.teaching.springbase.service.GreetingService;
import com.benfante.teaching.springbase.service.NameService;

@Service
@Primary
public class DefaultHelloWorldService implements GreetingService {
    
    @Autowired
    private NameService nameService;

    @Override
    public String sayHello(String name) {
        return "Hello, " + nameService.getName(name) + "!";
    }
}