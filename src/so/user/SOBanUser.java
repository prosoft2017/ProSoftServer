/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.user.AppUser;
import so.GeneralSO;

/**
 *
 * @author Nikola
 */
public class SOBanUser extends GeneralSO {

    private boolean response;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        response = DatabaseBroker.getInstance().banUser((AppUser) obj);
    }

    public boolean getResponse() {
        return response;
    }

}
