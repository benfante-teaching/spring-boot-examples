package com.benfante.javacourse.people_rest.controller;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.benfante.javacourse.people_rest.PeopleTestConfig;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;
import com.benfante.javacourse.people_rest.repository.PeopleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(controllers = PeopleController.class)
@Import(PeopleTestConfig.class)
public class PeopleControllerTest {

    private static final String BASE_URL = "/api/v1/people";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PeopleRepository peopleRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() throws Exception {
        Mockito.when(peopleRepository.findAll()).thenReturn(List.of(
            new Person(1L, "Mario", "Rossi", "mario.rossi@email.it", new City(1L,"Rome")),
            new Person(2L, "Luigi", "Verdi", "luigi.verdi@email.it", new City(2L,"Milan")),
            new Person(3L, "Giovanni", "Bianchi", "giovanni.bianchi@email.it", new City(3L,"Naples"))
        ));

        mvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].firstName", is("Mario")))
            .andExpect(jsonPath("$[0].lastName", is("Rossi")))
            .andExpect(jsonPath("$[0].city.id", is(1)));
    }

    @Test
    void testFindById() throws Exception {
        Mockito.when(peopleRepository.findById(2L)).thenReturn(Optional.of(
            new Person(2L, "Luigi", "Verdi", "luigi.verdi@email.it", new City(2L,"Milan"))));

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
        Mockito.when(peopleRepository.save(Mockito.any(Person.class))).thenReturn(
            new Person(4L, "Paolo", "Bianchi", "paolo.bianchi@email.it", new City(3L,"Naples")));

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
    }

    @Test
    void testUpdate() throws Exception {
        Mockito.when(peopleRepository.findById(2L)).thenReturn(Optional.of(
            new Person(2L, "Luigi", "Verdi", "luigi.verdi@email.it", new City(2L,"Milan"))));

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
    }

    @Test
    void testDeleteById() throws Exception {
        mvc.perform(delete(BASE_URL + "/2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

}
