/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.issue.Issue;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOUpdateIssues extends GeneralSO {

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        DatabaseBroker.getInstance().updateIssues((Issue) obj);
    }

}
