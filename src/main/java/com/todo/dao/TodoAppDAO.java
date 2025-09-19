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
        try(Connection conn = DatabaseConnection.getDBConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
        ResultSet rs = stmt.executeQuery();
        ){
            while (rs.next()) {
                Todo todo = new Todo();
                todo.setID(rs.getInt("ID"));
                todo.setTitle(rs.getString("title"));
                todo.setDescription(rs.getString("description"));
                todo.setCompleted(rs.getBoolean("completed"));
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
                todo.setCreated_at(createdAt);
                todo.setUpdated_at(updatedAt);
                todos.add(todo);
    
            }
        }
        return todos;
    }

    public void addTodo(Todo todo) throws SQLException {
        String insertQuery = "INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(todo.getCreated_at()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(todo.getUpdated_at()));
            
            // Debug: Check what we're saving
            System.out.println("DAO - Saving Todo:");
            System.out.println("  Title: '" + todo.getTitle() + "'");
            System.out.println("  Description: '" + todo.getDescription() + "'");
            System.out.println("  Completed: " + todo.isCompleted());
            
            stmt.executeUpdate();
        }
    }
}
