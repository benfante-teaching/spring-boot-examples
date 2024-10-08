package com.benfante.teaching.springbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.benfante.teaching.springbase.service.NameService;

@Component
public class PropertyNameService implements NameService {

    private static final String DEFAULT_NAME = "World";
    private static final String DEFAULT_KEY = "default";
    private static final String PROPERTY_PREFIX = "app.names.";
    
    @Autowired
    private Environment env;

    @Override
    public String getName() {
        return env.getProperty(PROPERTY_PREFIX + DEFAULT_KEY, DEFAULT_NAME);
    }

    @Override
    public String getName(String key) {
        if (key == null || key.isEmpty()) {
            return getName();
        }
        return env.getProperty(PROPERTY_PREFIX + key, key);
    }
    
}