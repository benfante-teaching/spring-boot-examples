package com.benfante.javacourse.people_rest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@SpringBootTest
// @TestPropertySource(properties = {
//     "spring.jpa.generate-ddl=true",
//     "spring.jpa.hibernate.ddl-auto=create",
//     "spring.jpa.properties.hibernate.hbm2ddl.create_namespaces=true"
// })
@Transactional
class PeopleServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PeopleServiceTest() {
        if (!postgresqlContainer.isRunning()) {
            postgresqlContainer.start();
        }
    }    

    @BeforeAll
    void initDb() {
        jdbcTemplate.execute("delete from people.Person;");
        jdbcTemplate.execute("delete from people.City;");
        jdbcTemplate.execute("insert into people.City (city_id, name) values (10001, 'Rome');");
        jdbcTemplate.execute("insert into people.City (city_id, name) values (10002, 'Milan');");
        jdbcTemplate.execute("insert into people.City (city_id, name) values (10003, 'Neaples');");
        jdbcTemplate.execute("insert into people.Person (person_id, first_name, last_name, email, city_id) values (20001, 'Mario', 'Rossi', 'mario.rossi@email.it', 10001)");
        jdbcTemplate.execute("insert into people.Person (person_id, first_name, last_name, email, city_id) values (20002, 'Giovanna', 'Bianchi', 'giovanna.bianchi@email.it', 10003)");
    }

    @Test
    public void testSave() {
        Person person = new Person(null, "Paolo", "Verdi", "paolo.verdi@email.it", new City(null, "Verona"));
        Person result = peopleService.save(person);
        assertThat(result.getId(), notNullValue());
        assertThat(result.getCity().getId(), notNullValue());
        peopleService.findById(result.getId()).ifPresentOrElse(p -> {
            assertThat(p.getId(), is(result.getId()));
            assertThat(p.getFirstName(), is("Paolo"));
            assertThat(p.getLastName(), is("Verdi"));
            assertThat(p.getEmail(), is("paolo.verdi@email.it"));
            assertThat(p.getCity().getId(), is(result.getCity().getId()));
            assertThat(p.getCity().getName(), is("Verona"));
        }, () -> {
            fail("Person not found");
        });
    }

    @Test
    public void testFindAll() {
        Iterable<Person> people = peopleService.findAll();
        assertThat(people, iterableWithSize(2));
    }

    @Test
    public void testFindById() {
        peopleService.findById(20001L).ifPresentOrElse(person -> {
            assertThat(person.getId(), is(20001L));
            assertThat(person.getFirstName(), is("Mario"));
            assertThat(person.getLastName(), is("Rossi"));
        }, () -> {
            fail("Person not found");
        });
    }

}