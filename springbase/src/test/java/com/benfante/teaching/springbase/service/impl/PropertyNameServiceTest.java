package com.benfante.teaching.springbase.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.benfante.teaching.springbase.TestConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(properties = { "app.names.default=Default name", "app.names.test=Test name" })
public class PropertyNameServiceTest {

    @Autowired
    private PropertyNameService propertyNameService;

    @Test
    void testGetDefaultName() {
        String name = propertyNameService.getName();
        assertEquals("Default name", name);
    }

    @Test
    void testGetTestName() {
        String name = propertyNameService.getName("test");
        assertEquals("Test name", name);
    }

    @Test
    void testGetUnknownName() {
        String name = propertyNameService.getName("unknown");
        assertEquals("unknown", name);
    }
}
