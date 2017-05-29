/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.AppUser;
import domain.StatusType;
import java.util.ArrayList;
import java.util.List;
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

    public List<AppUser> getAllUsersFromDB(int page, int offset, List filter) throws Exception {
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser("qwer", "qwerq", "asdfasdfa", "qwer@qwer.qwer", StatusType.Active, true, "qwer", "sfas", null));
        userList.add(new AppUser("22222", "q22wer22q", "fsadf34432", "lllllllllllllll@qwer.qwer", StatusType.Active, true, "22222", "asfdasd", null));
        userList.add(new AppUser("4537656", "gdsfg", "34523", "asdfasd", StatusType.Active, true, "22222", "asdfa222222sd", null));
        userList.add(new AppUser("123123", "gdfg", "asdf2222222asdfa", "asdfsaf@qwer.qwer", StatusType.Pending, false, "22222", "asdfsd", null));
        userList.add(new AppUser("asdfasdf", "q22werrtsert22q", "23423", "lllllllllllllll@qwer.qwer", StatusType.Locked, true, "22222", "ewrwq", null));
        userList.add(new AppUser("222nbvn455322", "s", "asdf2222222asdfa", "dsfgsd@qwer.qwer", StatusType.Active, true, "23423", "234234", null));
        userList.add(new AppUser("324etgg", "sert", "asdf2222222asdfa", "23d4r43r@qwer.qwer", StatusType.Active, false, "grt", "asdfa222222sd", null));
        userList.add(new AppUser("dfgh5467", "q22wgdfger22q", "asdf2222222asdfa", "fsdf@qwer.qwer", StatusType.Locked, true, "1231", "asdfa222222sd", null));
        userList.add(new AppUser("wertre56", "34", "asdf2222222asdfa", "dsfrs@qwer.qwer", StatusType.Pending, true, "123123", "423", null));
        userList.add(new AppUser("fhfd", "ewt", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));

        return userList;
    }

    public AppUser saveAppUser(AppUser appUser) throws Exception {
        SOSaveUser gso = new SOSaveUser();
        gso.executeSO(appUser);
        return gso.getUser();
    }
}
