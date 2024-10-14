package com.benfante.javacourse.people_rest.controller;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.benfante.javacourse.people_rest.PeopleTestConfig;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;
import com.benfante.javacourse.people_rest.repository.InMemoryPeopleRepository;
import com.benfante.javacourse.people_rest.repository.PeopleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = PeopleTestConfig.class)
@AutoConfigureMockMvc
public class PeopleControllerTest {

    private static final String BASE_URL = "/api/v1/people";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PeopleRepository peopleRepository;

    @BeforeEach
    void setUp() {
        InMemoryPeopleRepository inMemoryPeopleRepository = (InMemoryPeopleRepository)peopleRepository;
        inMemoryPeopleRepository.setPeople(List.of(
            new Person(1L, "Mario", "Rossi", "mario.rossi@email.it", new City(1L,"Rome")),
            new Person(2L, "Luigi", "Verdi", "luigi.verdi@email.it", new City(2L,"Milan")),
            new Person(3L, "Giovanni", "Bianchi", "giovanni.bianchi@email.it", new City(3L,"Naples"))
        ));
    }

    @Test
    void testFindAll() throws Exception {
        mvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].firstName", is("Mario")))
            .andExpect(jsonPath("$[0].lastName", is("Rossi")))
            .andExpect(jsonPath("$[0].city.id", is(1)));
    }

    @Test
    void testFindById() throws Exception {
        mvc.perform(get(BASE_URL + "/2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(2)))
            .andExpect(jsonPath("$.firstName", is("Luigi")))
            .andExpect(jsonPath("$.lastName", is("Verdi")))
            .andExpect(jsonPath("$.city.id", is(2)))
            .andExpect(jsonPath("$.city.name", is("Milan")));
    }

    @Test
    void testSave() throws Exception {
        mvc.perform(post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("""
            {
                "firstName": "Paolo",
                "lastName": "Bianchi",
                "email": "paolo.bianchi@email.it",
                "city": {"id": 3, "name": "Naples"}
            }
            """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(4)));

        Iterable<Person> allPeople = peopleRepository.findAll();
        assertThat(allPeople, iterableWithSize(4));
    }

    @Test
    void testUpdate() throws Exception {
        mvc.perform(patch(BASE_URL + "/2")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("""
            {
                "lastName": "Updated"
            }
            """))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.lastName", is("Updated")));

        Person person = peopleRepository.findById(2L).get();
        assertThat(person.lastName(), is("Updated"));
    }

    @Test
    void testDeleteById() throws Exception {
        mvc.perform(delete(BASE_URL + "/2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        Iterable<Person> allPeople = peopleRepository.findAll();
        assertThat(allPeople, iterableWithSize(2));
    }

}
