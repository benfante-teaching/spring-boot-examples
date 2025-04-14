package com.benfante.teaching.todo_webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.benfante.teaching.todo_webapp.model.Todo;
import com.benfante.teaching.todo_webapp.util.NotFoundException;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>(
            List.of(new Todo("Learn Spring Boot", "Start learning Spring Boot from spring.io"),
                    new Todo("Learn Java"), new Todo("Learn React")));

    public List<Todo> getTodos() {
        return todos;
    }

    public List<Todo> getTodos(String filter) {
        if (filter == null || filter.isEmpty()) {
            return todos;
        }
        return todos.stream()
                .filter(todo -> todo.title().toLowerCase().contains(filter.toLowerCase())
                        || (todo.description() != null
                                && todo.description().toLowerCase().contains(filter.toLowerCase())))
                .toList();
    }

    public Todo getTodo(UUID id) {
        return todos.stream().filter(todo -> todo.id().equals(id)).findFirst().orElseThrow(
                () -> new NotFoundException("Todo with id %s not found".formatted(id)));
    }

    public Todo addTodo(Todo todo) {
        todos.add(todo);
        return todo;
    }

    public void deleteTodo(UUID id) {
        Todo todo = getTodo(id);
        todos.remove(todo);
    }

    public Todo updateTodo(Todo todo) {
        int index = todos.indexOf(todo);
        if (index == -1) {
            throw new NotFoundException("Todo with id %s not found".formatted(todo.id()));
        }
        todos.set(index, todo);
        return todo;
    }
}
