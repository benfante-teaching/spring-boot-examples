package com.benfante.teaching.todo_webapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.benfante.teaching.todo_webapp.model.Todo;

public interface TodoRepository {
    Iterable<Todo> findAll();
    Optional<Todo> findById(Long id);
    Optional<Todo> findByUuid(UUID uuid);
    Todo save(Todo todo);
    void deleteById(Long id);
    void deleteByUuid(UUID uuid);
    List<Todo> findByTitleAndDescriptionContainingIgnoreCase(String filter);    
}