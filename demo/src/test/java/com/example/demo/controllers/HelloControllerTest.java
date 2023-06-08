package com.example.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/5"))
            .andExpect(status().isOk())
            .andExpect(view().name("example/hello"))
            .andExpect(content().string(Matchers.containsString("<h1>Hello")))
            .andExpect(content().string(Matchers.containsString("Lucio Benfante")))
            .andExpect(model().attributeExists("name"))
            .andExpect(model().attribute("name", "Lucio Benfante"));
    }

    @Test
    public void testHelloWithParam() throws Exception {
        mockMvc.perform(get("/5").queryParam("user", "Mario Rossi"))
            .andExpect(status().isOk())
            .andExpect(view().name("example/hello"))
            .andExpect(content().string(Matchers.containsString("<h1>Hello")))
            .andExpect(content().string(Matchers.containsString("Mario Rossi")))
            .andExpect(model().attributeExists("name"))
            .andExpect(model().attribute("name", "Mario Rossi"));
    }

}