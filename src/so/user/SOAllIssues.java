/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import com.sun.javafx.image.impl.General;
import db.DatabaseBroker;
import domain.issue.Issue;
import java.util.List;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOAllIssues extends GeneralSO {

    private List<Issue> issues;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        issues = DatabaseBroker.getInstance().getAllIssues();
    }

    public List<Issue> getIssues() {
        return issues;
    }

}
