package com.todo.gui;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;// for GUI components 
import javax.swing.table.DefaultTableModel;// for table model
import java.awt.*;// for layout managers and event handling
import java.awt.event.ActionEvent;// for action listeners
import java.awt.event.ActionListener;// for action listeners
import java.util.List;// for list of todos
import java.sql.SQLException;// for sql exceptions
import com.todo.model.Todo;// for todo model 
import com.todo.dao.TodoAppDAO;// for todo dao

public class TodoAppGUI extends JFrame {
    private TodoAppDAO todoDAO;// for connecting to ui and dao
    private JFrame todoTable;// main frame
    private DefaultTableModel tableModel;// table model
    private JTextField titleField;// title field
    private JTextArea descriptionArea;// description area
    private JCheckBox completedCheckBox;// completed checkbox
    private JButton addButton;// add button
    private JButton updateButton;// update button
    private JButton deleteButton;// delete button
    private JButton refreshButton;// refresh button
    private JComboBox<String> filterComboBox;// filter combobox

    public TodoAppGUI() {
        this.todoDAO = new TodoAppDAO();
        initialize();
    }
    private void initialize(){
        setTitle("Todo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
         todoTable = new JTable(tableModel);
         todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         todoTable.getSelectionModel().addListSelectionListener();


    }
}
