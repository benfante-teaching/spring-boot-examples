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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
