package com.benfante.teaching.todo_webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.benfante.teaching.todo_webapp.model.Todo;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>(List.of(new Todo("Learn Spring Boot", "Start learning Spring Boot from spring.io"),
            new Todo("Learn Java"), new Todo("Learn React")));

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodo(UUID id) {
        return todos.stream().filter(todo -> todo.id().equals(id)).findFirst().orElse(null);
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public void deleteTodo(UUID id) {
        Todo todo = getTodo(id);
        todos.remove(todo);
    }
}
