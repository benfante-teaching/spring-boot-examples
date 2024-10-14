package com.benfante.javacourse.people_rest.repository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;

@Repository
public class JdbcPeopleRepository implements PeopleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Person> findAll() {
        return jdbcTemplate.query("select p.*, c.* from person p JOIN city c USING(city_id);",
         (rs, rowNum) -> new Person(
            rs.getLong("person_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            new City(
                rs.getLong("city_id"),
                rs.getString("name")
            )));
    }

    @Override
    public Optional<Person> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Person save(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}