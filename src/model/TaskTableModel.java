/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Task;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class TaskTableModel extends AbstractTableModel {

    private final String[] columns = new String[]{"Title", "User", "Start Date", "End Date", "Status"};
    private final List<Task> taskList;

    public TaskTableModel(List<Task> taskList) {
        if (taskList == null) {
            taskList = new ArrayList<>();
        }

        this.taskList = taskList;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return taskList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = taskList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return task.getTitle();
            case 1:
                return task.getAppUser().getUsername();
            case 2:
                return task.getStartDate();
            case 3:
                return task.getEndDate();
            case 4:
                return task.getTaskStatus();
            default:
                return "N/A";
        }

    }

    public void addNewTask(Task userTask) {
        taskList.add(userTask);
        fireTableDataChanged();
    }

    public Task getTaskAt(int selectedIndex) {
        return taskList.get(selectedIndex);
    }
    
    public void removeTask(int row) {
        taskList.remove(row);
        fireTableDataChanged();
    }
    
    public void removeTask(Task task) {
        taskList.remove(task);
        fireTableDataChanged();
    }

}
