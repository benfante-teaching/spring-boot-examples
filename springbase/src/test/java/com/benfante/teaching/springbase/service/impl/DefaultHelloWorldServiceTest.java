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
public class DefaultHelloWorldServiceTest {

    @Autowired
    private DefaultHelloWorldService defaultHelloWorldService;

    @Test
    void testSayHelloToTest() {
        String greeting = defaultHelloWorldService.sayHello("test");
        assertEquals("Hello, Test name!", greeting);
    }

    @Test
    void testSayHelloToUnknown() {
        String greeting = defaultHelloWorldService.sayHello("unknown");
        assertEquals("Hello, unknown!", greeting);
    }

    @Test
    void testSayHelloWithNull() {
        String greeting = defaultHelloWorldService.sayHello(null);
        assertEquals("Hello, Default name!", greeting);
    }
}
