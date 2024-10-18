package com.benfante.javacourse.people_rest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@SpringBootTest
@TestPropertySource(properties = {
    "spring.jpa.generate-ddl=true",
    "spring.jpa.hibernate.ddl-auto=create",
    "spring.jpa.properties.hibernate.hbm2ddl.create_namespaces=true"
})
@Transactional
class PeopleServiceTest {
    
    @SuppressWarnings("resource")
    @Container
    @ServiceConnection
    static MSSQLServerContainer<?> mssqlServerContainer = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest").acceptLicense();

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PeopleServiceTest() {
        if (!mssqlServerContainer.isRunning()) {
            mssqlServerContainer.start();
        }
    }    

    @BeforeAll
    void initDb() {
        jdbcTemplate.execute("delete from people.Person;");
        jdbcTemplate.execute("delete from people.City;");
        jdbcTemplate.execute("insert into people.City (name) values ('Rome');");
        jdbcTemplate.execute("insert into people.City (name) values ('Milan');");
        jdbcTemplate.execute("insert into people.City (name) values ('Neaples');");
        jdbcTemplate.execute("insert into people.Person (first_name, last_name, email, city_id) values ('Mario', 'Rossi', 'mario.rossi@email.it', 1)");
        jdbcTemplate.execute("insert into people.Person (first_name, last_name, email, city_id) values ('Giovanna', 'Bianchi', 'giovanna.bianchi@email.it', 3)");
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
        peopleService.findById(1L).ifPresentOrElse(person -> {
            assertThat(person.getId(), is(1L));
            assertThat(person.getFirstName(), is("Mario"));
            assertThat(person.getLastName(), is("Rossi"));
        }, () -> {
            fail("Person not found");
        });
    }

}