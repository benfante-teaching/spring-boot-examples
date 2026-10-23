package com.benfante.javacourse.people_rest.repository;

import java.sql.PreparedStatement;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;

@Repository
public class JdbcPeopleRepository implements PeopleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<City> cityRowMapper = (rs, rowNum) -> {
        return new City(
            rs.getLong("city_id"),
            rs.getString("name")
        );
    };

    private RowMapper<Person> personRowMapper = (rs, rowNum) -> {
        return new Person(
            rs.getLong("person_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            cityRowMapper.mapRow(rs, rowNum)
        );
    };

    @Override
    public Iterable<Person> findAll() {
        return jdbcTemplate.query("""
                select p.*, c.*
                  from person p JOIN city c USING(city_id);
                """,
            personRowMapper);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jdbcTemplate.queryForStream("""
                select p.*, c.*
                  from person p JOIN city c USING(city_id)
                 where person_id = ?;
                """,
            personRowMapper, id).findFirst();
    }

    @Override
    public Person save(Person person) {
        City city = save(person.city());
        Long personId = person.id();
        if (personId == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("""
                    insert into person(first_name, last_name, email, city_id)
                      values(?, ?, ?, ?);
                    """, new String[] {"person_id"});
                ps.setString(1, person.firstName());
                ps.setString(2, person.lastName());
                ps.setString(3, person.email());
                ps.setLong(4, city.id().longValue());
                return ps;
            }, keyHolder);
            personId = keyHolder.getKey().longValue();
        } else {
            Person current = findById(person.id()).get();
            jdbcTemplate.update("""
                update person
                  set first_name = ?,
                      last_name = ?,
                      email = ?,
                      city_id = ?
                  where person_id = ?;
                """,
                person.firstName() != null ? person.firstName():current.firstName(),
                person.lastName() != null ? person.lastName():current.lastName(),
                person.email() != null ? person.email():current.email(),
                city != null ? city.id():current.city().id(),
                personId);
        }
        return findById(personId).get();
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("""
            delete from person
             where person_id = ?;
            """, id);
    }

    @Override
    public void delete(Person person) {
        deleteById(person.id());
    }

    private City save(City city) {
        if (city == null) {
            return null;
        }
        Long cityId = city.id();
        if (cityId != null) {
            jdbcTemplate.update("""
                update city
                  set name = ?
                  where city_id = ?;                  
                """,
                city.name(), city.id());
        } else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("""
                    insert into city(name)
                      values(?);
                    """, new String[] {"city_id"});
                ps.setString(1, city.name());
                return ps;
            }, keyHolder);
            cityId = keyHolder.getKey().longValue();
        }
        return findCityById(cityId);
    }

    private City findCityById(Long id) {
        return jdbcTemplate.queryForObject("""
            select *
              from city
             where city_id = ?;
            """,
            cityRowMapper, id);
    }

}