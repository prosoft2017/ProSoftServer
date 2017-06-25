/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.issue.Issue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class IssueTableModel extends AbstractTableModel {

    String columns[] = new String[]{"Id", "Title", "Created at", "Status", "Reported by"};
    List<Issue> issuesList;
    List<Issue> originalList;

    public IssueTableModel(List<Issue> issuesList) {
        if (issuesList == null) {
            issuesList = new ArrayList<>();
        }
        this.issuesList = issuesList;
        this.originalList = issuesList;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return issuesList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Issue issue = issuesList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return issue.getId();
            case 1:
                return issue.getTitle();
            case 2:
                return controller.Controller.getController().formatter.format(issue.getCreatedAt());
            case 3:
                return issue.getIssueStatus().toString();
            case 4:
                return issue.getAppUser().toString();
            default:
                return "N/A";
        }
    }

    public Issue getIssueAt(int rowIndex) {
        return issuesList.get(rowIndex);
    }

    public void filterIssues(String hint) {
        if (hint.isEmpty()) {
            issuesList = originalList;
        } else {
            issuesList = originalList.stream()
                    .filter(issue -> issue.getDescription().toLowerCase().contains(hint.toLowerCase())
                    || issue.getTitle().toLowerCase().contains(hint.toLowerCase())
                    || issue.getAppUser().toString().toLowerCase().contains(hint.toLowerCase()))
                    .collect(Collectors.toList());
        }
        fireTableDataChanged();
    }

}
