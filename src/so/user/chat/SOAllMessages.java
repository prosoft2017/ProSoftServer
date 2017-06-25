/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user.chat;

import db.DatabaseBroker;
import domain.user.AppUser;
import java.util.List;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOAllMessages extends GeneralSO {

    private String message;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        message = DatabaseBroker.getInstance().getAllMessages((List<AppUser>) obj);
    }

    public String getMessage() {
        return message;
    }

}
