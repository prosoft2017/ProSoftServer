/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.user.AppUser;
import domain.user.Country;
import domain.user.StatusType;
import filter.user.UserFilter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class UserTableModel extends AbstractTableModel {

    private final String[] columns = new String[]{"Firstname", "Lastname", "Username", "Email", "Status", "Baned"};
    private final List<AppUser> initialUserList;
    private List<AppUser> filteredListOfUsers;
    private List<AppUser> listOfUsers;
    private UserFilter filter;

    public UserTableModel(List<AppUser> listOfUsers, int offset) {
        if (listOfUsers == null) {
            listOfUsers = new ArrayList<>();
        }
        this.initialUserList = listOfUsers;
        this.filteredListOfUsers = listOfUsers;
        this.listOfUsers = filteredListOfUsers.subList(0, (filteredListOfUsers.size() > offset ? offset : filteredListOfUsers.size()));
        filter = new UserFilter();
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

    public int filterByHint(String hint, int offset) {
        filter.setSearchString(hint);
        
        return doAllFilters(offset);
    }

    public void changePage(int page, int offset) {
        this.listOfUsers = filteredListOfUsers.subList((page - 1) * offset, filteredListOfUsers.size() > page * offset ? page * offset : filteredListOfUsers.size());
        fireTableDataChanged();
    }

    public int resetAllFilters(int offset) {
        filter.resetAllFilters();
        filteredListOfUsers = initialUserList;
        changePage(1, offset);

        return (int) Math.ceil(filteredListOfUsers.size() / (double) (offset));
    }

    public int addStatusFilter(StatusType statusType, int offset) {
        filter.addStatusType(statusType);

        return doAllFilters(offset);
    }
    
    public int addCountryFilter(List<Country>countryList, int offset) {
        filter.addCountyFilter(countryList);
        
        return doAllFilters(offset);
    }

    public int removeStatusFilter(StatusType statusType, int offset) {
        filter.removeStatusType(statusType);

        return doAllFilters(offset);
    }

    public int addBanedFilter(boolean selected, int offset) {
        filter.setBanned(selected);

        return doAllFilters(offset);
    }

    private int doAllFilters(int offset) {
        filteredListOfUsers = new ArrayList<>();
        initialUserList.stream()
                .forEach(user -> {
                    if (filter.doFiltration(user)) {
                        filteredListOfUsers.add(user);
                    }
                });
        changePage(1, offset);

        return (int) Math.ceil(filteredListOfUsers.size() / (double) (offset));
    }

    public List<Country> getCountryFilter() {
        return filter.getCountryList();
    }

}
