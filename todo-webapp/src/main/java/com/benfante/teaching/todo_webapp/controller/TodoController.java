package com.benfante.teaching.todo_webapp.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.benfante.teaching.todo_webapp.model.Todo;
import com.benfante.teaching.todo_webapp.model.TodoForm;
import com.benfante.teaching.todo_webapp.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    
    @GetMapping
    public String list(@RequestParam(name="query", defaultValue = "") String query, Model model) {
        model.addAttribute("todos", todoService.getTodos(query));
        return "todos/list";
    }

    @GetMapping("/new")
    public String newTodo(Model model) {
        model.addAttribute("todo", new TodoForm());
        return "todos/form";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") UUID id, Model model) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return "redirect:/todos";
        }
        model.addAttribute("todo", new TodoForm(todo.id(), todo.title(), todo.description()));
        return "todos/form";
    }

    @PostMapping()
    public String save(@ModelAttribute("todo") TodoForm todo) {
        if (todo.getId() == null) {
            todoService.addTodo(new Todo(todo.getTitle(), todo.getDescription()));
        } else {
            todoService.updateTodo(new Todo(todo.getId(), todo.getTitle(), todo.getDescription(), false));
        }        
        return "redirect:/todos";
    }
    

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}
