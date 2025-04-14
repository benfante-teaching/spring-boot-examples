package com.benfante.teaching.todo_webapp.service;

import java.util.List;
import java.util.UUID;
import com.benfante.teaching.todo_webapp.model.Todo;


public interface TodoService {

    List<Todo> getTodos();

    List<Todo> getTodos(String filter);

    Todo getTodo(UUID id);

    Todo addTodo(Todo todo);

    void deleteTodo(UUID id);

    Todo updateTodo(Todo todo);

}
