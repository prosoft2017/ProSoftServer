/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.user.AppUser;
import java.util.List;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOAllUsers extends GeneralSO{

    private List<AppUser> allUsers;

    
    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
       allUsers = DatabaseBroker.getInstance().getAllUsers();
    }

    public List<AppUser> getAllUsers() {
        return allUsers;
    }
    
}
