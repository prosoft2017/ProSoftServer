/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.AppUser;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOValidateUser extends GeneralSO{

    private AppUser appUser;
    
    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        appUser = DatabaseBroker.getInstance().validateUser(obj);
    }

    public AppUser getAppUser() {
        return appUser;
    }
    
    
    
}
