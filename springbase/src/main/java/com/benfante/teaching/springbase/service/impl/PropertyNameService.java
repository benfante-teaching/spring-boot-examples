package com.benfante.teaching.springbase.service.impl;

import java.io.IOException;
import java.util.Properties;

import com.benfante.teaching.springbase.service.NameService;

public class PropertyNameService implements NameService {
    private static final String DEFAULT_ID = "default";
    private static final String PROPERTIES_PREFIX = "app.names.";
    private static final String DEFAULT_FIXED_NAME = "World";
    private static final String UNKNOWN_FIXED_NAME = "Unknown";
    private static final String APPLICATION_PROPERTIES_RESOURCE = "/application.properties";

    private Properties names;

    public PropertyNameService() throws IOException {
        this.names = new Properties();
        this.names.load(PropertyNameService.class.getResourceAsStream(APPLICATION_PROPERTIES_RESOURCE));
    }

    @Override
    public String getName() {
        return names.getProperty(PROPERTIES_PREFIX + DEFAULT_ID, DEFAULT_FIXED_NAME);
    }

    @Override
    public String getName(String id) {
        return names.getProperty(PROPERTIES_PREFIX + id, UNKNOWN_FIXED_NAME);
    }

}
