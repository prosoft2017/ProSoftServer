/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.task.Task;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOUpdateUserTask extends GeneralSO{

    private Task task;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        task = DatabaseBroker.getInstance().updateUserTask((Task) obj);
    }

    public Task getTask() {
        return task;
    }
    
}
