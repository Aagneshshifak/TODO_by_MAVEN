package com.todo;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.todo.gui.TodoAppGUI;
//import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;
public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConn = new DatabaseConnection();
        try {
            dbConn.getConnection();
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
        System.err.println("Failed to set look and feel."+ e.getMessage());
       // e.printStackTrace();
    }
    SwingUtilities.invokeLater(
        () ->{
            try{
            new TodoAppGUI().setVisible(true);
            }
            catch(Exception e){
                System.err.println("Failed to create GUI."+ e.getLocalizedMessage());
            }
        }
    );
    }
}