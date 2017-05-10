/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.AppUser;
import domain.StatusType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getController() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public List<AppUser> getAllUsersFromDB() throws Exception{
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser("qwer", "qwerq", "asdfasdfa", "qwer@qwer.qwer", StatusType.Active, true, "qwer", "asdfasd"));
        userList.add(new AppUser("22222", "q22wer22q", "asdf2222222asdfa", "lllllllllllllll@qwer.qwer", StatusType.Active, true, "22222", "asdfa222222sd"));
        
        return userList;
    }
    
    public AppUser saveAppUser(AppUser appUser) throws Exception {
        
        return appUser;
    }

    public boolean checkUsername(String username) {
        return true;
    }

    public boolean checkEmail(String email) {
        return true;
    }
}
