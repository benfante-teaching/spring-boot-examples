package com.benfante.teaching.todo_webapp.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.benfante.teaching.todo_webapp.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("todos", todoService.getTodos());
        return "todos/list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}
