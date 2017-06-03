/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.AppUser;
import domain.StatusType;
import domain.Task;
import java.util.ArrayList;
import java.util.List;
import so.user.SOAllTasks;
import so.user.SOAllUsers;
import so.user.SOSaveUser;

/**
 *
 * @author Nikola
 */
public class UserBackendController {

    private static UserBackendController instance;

    private UserBackendController() {
    }

    public static UserBackendController getController() {
        if (instance == null) {
            instance = new UserBackendController();
        }

        return instance;
    }

    public List<AppUser> getAllUsersFromDB() throws Exception {
        SOAllUsers soau = new SOAllUsers();
        soau.executeSO(null);
        return soau.getAllUsers();
    }
    
    public List<Task> getAllTasksForUser(AppUser appUser) throws Exception {
        SOAllTasks soat = new SOAllTasks();
        soat.executeSO(appUser);
        return soat.getAllTasks();
    }

    public AppUser saveAppUser(AppUser appUser) throws Exception {
        SOSaveUser gso = new SOSaveUser();
        gso.executeSO(appUser);
        return gso.getUser();
    }
}
