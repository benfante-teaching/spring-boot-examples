package com.benfante.teaching.todo_webapp.model;

import java.util.UUID;

public record Todo(UUID id, String title, String description, boolean completed) {
    public Todo {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    public Todo(String title) {
        this(UUID.randomUUID(), title, null, false);
    }

    public Todo(String title, String description) {
        this(UUID.randomUUID(), title, description, false);
    }
}
