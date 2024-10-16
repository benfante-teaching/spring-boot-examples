package com.example.demo_database.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_database.model.Person;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    private DataSource dataSource;
    
    @GetMapping
    public Iterable<Person> getPeople() throws SQLException {
        List<Person> result = new ArrayList<>();
        Connection conn = dataSource.getConnection();
        try (var stmt = conn.createStatement();
             var rs = stmt.executeQuery("SELECT * FROM Userdata")) {
            while (rs.next()) {
                result.add(new Person(rs.getLong("id"), rs.getString("username"), rs.getString("first_name"), rs.getString("last_name")));
            }
        }
        return result;
    }

}