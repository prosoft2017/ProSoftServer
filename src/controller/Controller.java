/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.AppUser;
import so.user.SOValidateUser;

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

    public boolean checkUsername(String username) {
        return true;
    }

    public boolean checkEmail(String email) {
        return true;
    }

    public AppUser validateUser(Object parameter) throws Exception {
        SOValidateUser validateUser = new SOValidateUser();
        validateUser.executeSO(parameter);

        return validateUser.getAppUser();
    }
}
