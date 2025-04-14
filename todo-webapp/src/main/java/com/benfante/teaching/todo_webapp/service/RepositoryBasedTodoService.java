package com.benfante.teaching.todo_webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.benfante.teaching.todo_webapp.model.Todo;
import com.benfante.teaching.todo_webapp.repository.TodoRepository;
import com.benfante.teaching.todo_webapp.util.NotFoundException;

@Service
@Primary
public class RepositoryBasedTodoService implements TodoService {
    @Autowired
    private TodoRepository todoRepository;


    @Override
    public List<Todo> getTodos() {
        List<Todo> result = new ArrayList<>();
        todoRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Todo> getTodos(String filter) {
        return todoRepository.findByTitleAndDescriptionContainingIgnoreCase(filter);
    }

    @Override
    public Todo getTodo(UUID uuid) {
        return todoRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Todo with uuid %s not found".formatted(uuid)));
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(UUID uuid) {
        getTodo(uuid); // Check if the Todo exists before deleting
        todoRepository.deleteByUuid(uuid);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        getTodo(todo.id()); // Check if the Todo exists before updating
        return todoRepository.save(todo);
    }
    
}