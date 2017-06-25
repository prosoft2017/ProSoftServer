/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.user.AppUser;
import domain.user.StatusType;
import domain.task.Task;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;
import javafx.beans.InvalidationListener;
import so.user.SOAllTasks;
import so.user.SOAllUsers;
import so.user.SOBanUser;
import so.user.SOSaveUser;
import so.user.SOUpdateUser;
import so.user.SOUpdateUserTask;
import so.user.task.SOCloseTask;
import so.user.task.SOSaveNewUserTask;

/**
 *
 * @author Nikola
 */
public class UserBackendController extends Observable {

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

    public AppUser editAppUser(AppUser appUser) throws Exception {
        SOUpdateUser oUpdateUser = new SOUpdateUser();
        oUpdateUser.executeSO(appUser);
        setChanged();
        notifyObservers();
        return appUser;
    }

    public AppUser saveAppUser(AppUser appUser) throws Exception {
        SOSaveUser gso = new SOSaveUser();
        gso.executeSO(appUser);
        setChanged();
        notifyObservers();
        return gso.getUser();
    }

    public Task saveUserTask(Task task) throws Exception {
        SOSaveNewUserTask sosu = new SOSaveNewUserTask();
        sosu.executeSO(task);
        setChanged();
        notifyObservers();
        return sosu.getUserTask();
    }

    public Task updateUserTask(Task task) throws Exception {
        SOUpdateUserTask sosu = new SOUpdateUserTask();
        sosu.executeSO(task);
        setChanged();
        notifyObservers();
        return sosu.getTask();
    }

    public Task closeUserTask(Task task) throws Exception {
        SOCloseTask sosu = new SOCloseTask();
        sosu.executeSO(task);
        setChanged();
        notifyObservers();
        return sosu.getUserTask();
    }

    public boolean banUser(AppUser appUser) throws Exception {
        SOBanUser sosu = new SOBanUser();
        sosu.executeSO(appUser);
        setChanged();
        notifyObservers();
        return sosu.getResponse();
    }

}
