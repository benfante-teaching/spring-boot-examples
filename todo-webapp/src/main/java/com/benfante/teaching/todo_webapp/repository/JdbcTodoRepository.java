package com.benfante.teaching.todo_webapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.benfante.teaching.todo_webapp.model.Todo;

@Repository
public class JdbcTodoRepository implements TodoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unused")
    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) -> {
        return new Todo(rs.getObject("uuid", UUID.class), rs.getString("title"),
                rs.getString("description"), rs.getBoolean("completed"));
    };

    @Override
    public Iterable<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return DataAccessUtils.optionalResult(
                jdbcTemplate.query("SELECT * FROM todo WHERE id = ?", todoRowMapper, id));
    }

    @Override
    public Optional<Todo> findByUuid(UUID uuid) {
        return DataAccessUtils.optionalResult(
                jdbcTemplate.query("SELECT * FROM todo WHERE uuid = ?", todoRowMapper, uuid));
    }

    @Override
    public Todo save(Todo todo) {
        Todo existingTodo = findByUuid(todo.id()).orElse(null);
        if (existingTodo == null) {
            jdbcTemplate.update("INSERT INTO todo (uuid, title, description, completed) VALUES (?, ?, ?, ?)",
                    todo.id(), todo.title(), todo.description(), todo.completed());
        } else {
            jdbcTemplate.update("UPDATE todo SET title = ?, description = ?, completed = ? WHERE uuid = ?",
                    todo.title(), todo.description(), todo.completed(), todo.id());
        }
        return findByUuid(todo.id()).orElseThrow(() -> new IllegalStateException("Todo not found"));
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM todo WHERE id = ?", id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jdbcTemplate.update("DELETE FROM todo WHERE uuid = ?", uuid);
    }

    @Override
    public List<Todo> findByTitleAndDescriptionContainingIgnoreCase(String filter) {
        return jdbcTemplate.query("SELECT * FROM todo WHERE title ILIKE ? OR description ILIKE ?",
                todoRowMapper, "%" + filter + "%", "%" + filter + "%");
    }

}
