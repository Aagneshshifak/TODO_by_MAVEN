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
    public List<Todo> getAllTodos() throws SQLException{
        List<Todo> todos = new ArrayList<>();
        try(Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
        ResultSet rs = stmt.executeQuery();
        ){
            while (rs.next()) {
                Todo todo = new Todo();
                todo.setID(rs.getInt("ID"));
                todo.setTitle(rs.getString("Title"));
                todo.setDescription(rs.getString("Description"));
                todo.setCompleted(rs.getBoolean("Completed"));
                LocalDateTime createdAt = rs.getTimestamp("Created_at").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("Updated_at").toLocalDateTime();
                todo.setCreated_at(createdAt);
                todo.setUpdated_at(updatedAt);
                todos.add(todo);
            }
        }
        return todos;
    }
}