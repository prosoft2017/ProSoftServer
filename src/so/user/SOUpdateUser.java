/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.task.Task;
import domain.user.AppUser;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOUpdateUser extends GeneralSO{

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        DatabaseBroker.getInstance().updateUser((AppUser) obj);
    }
    
}
