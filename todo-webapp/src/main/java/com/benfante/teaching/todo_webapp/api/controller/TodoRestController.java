package com.benfante.teaching.todo_webapp.api.controller;

import java.util.Collection;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.benfante.teaching.todo_webapp.model.Todo;
import com.benfante.teaching.todo_webapp.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public Collection<Todo> list(@RequestParam(name = "query", defaultValue = "") String query) {
        return todoService.getTodos(query);
    }

    @Operation(summary = "Get a todo by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Todo.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)})
    @GetMapping("/{id}")
    public Todo get(@Parameter(description = "id of the todo to be searched") @PathVariable(name = "id") UUID id) {
        return todoService.getTodo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo add(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable(name = "id") UUID id, @RequestBody Todo todo) {
        if (!id.equals(todo.id())) {
            throw new IllegalArgumentException("Todo ID in path and body do not match");
        }
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") UUID id) {
        todoService.deleteTodo(id);
    }
}
