/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user.chat;

import db.DatabaseBroker;
import domain.chat.Message;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOSaveMessage extends GeneralSO {

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        DatabaseBroker.getInstance().saveMessage((Message) obj);
    }

}
