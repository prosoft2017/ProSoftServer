/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user.task;

import db.DatabaseBroker;
import domain.task.Task;
import so.GeneralSO;

/**
 *
 * @author Nikola
 */
public class SOCloseTask extends GeneralSO {

    private Task userTask;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        userTask = DatabaseBroker.getInstance().closeUserTask((Task) obj);
    }

    public Task getUserTask() {
        return userTask;
    }
}
