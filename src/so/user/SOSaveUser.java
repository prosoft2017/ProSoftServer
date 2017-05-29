/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.AppUser;
import db.DatabaseBroker;
import so.GeneralSO;

/**
 *
 * @author Nikola
 */
public class SOSaveUser extends GeneralSO{

    private AppUser user;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        user = DatabaseBroker.getInstance().saveUser((AppUser) obj);
    }

    public AppUser getUser() {
        return user;
    }
    
}
