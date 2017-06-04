/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.user.AppUser;
import domain.task.Task;
import java.util.List;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOAllTasks extends GeneralSO{

    private List<Task> allTasks;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        allTasks = DatabaseBroker.getInstance().getAllTasksForUser((AppUser) obj);
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }
    
}
