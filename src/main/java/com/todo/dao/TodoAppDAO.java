package com.todo.dao;
import java.sql.SQLException;
import com.todo.model.Todo;
import com.todo.util.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class TodoAppDAO {
    private Todo getTodorow(ResultSet rs) throws SQLException{
        int id = rs.getInt("ID");
        String title = rs.getString("Title");
        String description = rs.getString("Description");
        boolean completed = rs.getBoolean("Completed");
        LocalDateTime createdAt = rs.getTimestamp("Created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("Updated_at").toLocalDateTime();
        return new Todo(id, title, description, completed, createdAt, updatedAt);
    }
    public List<Todo> getAllTodos() throws SQLException{
        List<Todo> todos = new ArrayList<>();
        try(Connection conn = new DatabaseConnection().getDBConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
        ResultSet rs = stmt.executeQuery();
        ){
            while (rs.next()) {
                todos.add(getTodorow(rs));
            }
        }
        return todos;
    }
}