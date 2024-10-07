package com.benfante.teaching.springbase.service.impl;

import org.springframework.stereotype.Component;
import com.benfante.teaching.springbase.service.NameService;

@Component
public class DefaultNameService implements NameService {

    private String defaultName;

    public DefaultNameService() {
        this.defaultName = "World";
    }

    public DefaultNameService(String defaultName) {
        this.defaultName = defaultName;
    }

    @Override
    public String getName() {
        return defaultName;
    }

}
