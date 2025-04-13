package com.benfante.teaching.todo_webapp.model;

import java.util.UUID;

public class TodoForm {
    private UUID id;
    private String title;
    private String description;

    public TodoForm() {}

    public TodoForm(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
