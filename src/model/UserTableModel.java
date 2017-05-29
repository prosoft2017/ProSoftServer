/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.AppUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class UserTableModel extends AbstractTableModel {

    private final String[] columns = new String[]{"Firstname", "Lastname", "Username", "Email", "Status", "Baned"};
    private List<AppUser> listOfUsers;

    public UserTableModel(List<AppUser> listOfUsers) {
        if (listOfUsers == null) {
            listOfUsers = new ArrayList<>();
        }
        this.listOfUsers = listOfUsers;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 5:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return listOfUsers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AppUser appUser = listOfUsers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return appUser.getFirstname();
            case 1:
                return appUser.getLastname();
            case 2:
                return appUser.getUsername();
            case 3:
                return appUser.getEmail();
            case 4:
                return appUser.getStatus();
            case 5:
                return appUser.isBaned();
            default:
                return "N/A";
        }
    }

    public AppUser getUserAt(int selectedIndex) {
        return listOfUsers.get(selectedIndex);
    }

    public void setListOfUsers(List<AppUser> listOfUsers) {
        this.listOfUsers = listOfUsers;
        fireTableDataChanged();
    }

}
