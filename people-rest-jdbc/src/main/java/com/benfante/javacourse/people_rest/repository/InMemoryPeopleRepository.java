package com.benfante.javacourse.people_rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.benfante.javacourse.people_rest.model.City;
import com.benfante.javacourse.people_rest.model.Person;

// @Repository
public class InMemoryPeopleRepository implements PeopleRepository {

    private final List<Person> people;

    public InMemoryPeopleRepository() {
        this(List.of(
            new Person(1L, "John", "Doe", "john.doe@email.com", new City(1L, "New York")),
            new Person(2L, "Jane", "Doe", "jane.doe@email.com", new City(2L, "Los Angeles")),
            new Person(3L, "Alice", "Smith", "alice.smith@email.com", new City(3L, "Chicago")),
            new Person(4L, "Bob", "Johnson", "bob.johnson@email.com", new City(4L, "Houston"))
        ));
    }

    public InMemoryPeopleRepository(List<Person> people) {
        this.people = new ArrayList<>(people);
    }

    @Override
    public Iterable<Person> findAll() {
        return people;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return people.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    @Override
    public Person save(Person person) {
        if (person.id() == null) {
            Long id = people.stream().map(Person::id).max(Long::compareTo).orElse(0L) + 1;
            person = new Person(id, person.firstName(), person.lastName(), person.email(), person.city());
            people.add(person);
        } else {
            Person current = findById(person.id()).get();
            Person updated = new Person(current.id(),
                person.firstName() != null ? person.firstName():current.firstName(),
                person.lastName() != null ? person.lastName():current.lastName(),
                person.email() != null ? person.email():current.email(),
                person.city() != null ? person.city():current.city());
            people.set(people.indexOf(current), updated);
            person = updated;            
        }
        return person;
    }

    @Override
    public void deleteById(Long id) {
        people.removeIf(p -> p.id().equals(id));
    }

    @Override
    public void delete(Person person) {
        people.remove(person);
    }

    public void setPeople(List<Person> people) {
        this.people.clear();
        this.people.addAll(people);
    }
}
