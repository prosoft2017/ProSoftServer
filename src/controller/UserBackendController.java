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

    public List<AppUser> getAllUsersFromDB() throws Exception {
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser("1", "qwerq", "asdfasdfa", "qwer@qwer.qwer", StatusType.Active, true, "qwer", "sfas", null));
        userList.add(new AppUser("2", "q22wer22q", "fsadf34432", "lllllllllllllll@qwer.qwer", StatusType.Active, true, "22222", "asfdasd", null));
        userList.add(new AppUser("3", "gdsfg", "34523", "asdfasd", StatusType.Active, true, "22222", "asdfa222222sd", null));
        userList.add(new AppUser("4", "gdfg", "asdf2222222asdfa", "asdfsaf@qwer.qwer", StatusType.Pending, false, "22222", "asdfsd", null));
        userList.add(new AppUser("5", "q22werrtsert22q", "23423", "lllllllllllllll@qwer.qwer", StatusType.Locked, true, "22222", "ewrwq", null));
        userList.add(new AppUser("6", "s", "asdf2222222asdfa", "dsfgsd@qwer.qwer", StatusType.Active, true, "23423", "234234", null));
        userList.add(new AppUser("7", "sert", "asdf2222222asdfa", "23d4r43r@qwer.qwer", StatusType.Active, false, "grt", "asdfa222222sd", null));
        userList.add(new AppUser("8", "q22wgdfger22q", "asdf2222222asdfa", "fsdf@qwer.qwer", StatusType.Locked, true, "1231", "asdfa222222sd", null));
        userList.add(new AppUser("9", "34", "asdf2222222asdfa", "dsfrs@qwer.qwer", StatusType.Pending, true, "123123", "423", null));
        userList.add(new AppUser("10", "ewt", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("11", "ew45y64t", "ascf435", "asdcdsafcsdfads@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("12", "ewt", "asdf2222222asdfa", "12312312@qwer.qwer", StatusType.Active, true, "123123", "sadfsad", null));
        userList.add(new AppUser("13", "5y7", "768i876u", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("14", "ewt", "ij87ij87", "ascdfdsacf@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("15", "ewt", "uj67juh", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("16", "45y654", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("17", "45y645y", "uh567hu", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("18", "gdfg", "asdf2222222asdfa", "asdfsaf@qwer.qwer", StatusType.Pending, false, "22222", "asdfsd", null));
        userList.add(new AppUser("19", "q22werrtsert22q", "23423", "lllllllllllllll@qwer.qwer", StatusType.Locked, true, "22222", "ewrwq", null));
        userList.add(new AppUser("20", "s", "asdf2222222asdfa", "dsfgsd@qwer.qwer", StatusType.Active, true, "23423", "234234", null));
        userList.add(new AppUser("21", "sert", "asdf2222222asdfa", "23d4r43r@qwer.qwer", StatusType.Active, false, "grt", "asdfa222222sd", null));
        userList.add(new AppUser("22", "q22wgdfger22q", "asdf2222222asdfa", "fsdf@qwer.qwer", StatusType.Locked, true, "1231", "asdfa222222sd", null));
        userList.add(new AppUser("23", "34", "asdf2222222asdfa", "dsfrs@qwer.qwer", StatusType.Pending, true, "123123", "423", null));
        userList.add(new AppUser("24", "ewt", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("25", "ew45y64t", "ascf435", "asdcdsafcsdfads@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));
        userList.add(new AppUser("26", "ewt", "asdf2222222asdfa", "12312312@qwer.qwer", StatusType.Active, true, "123123", "sadfsad", null));
        userList.add(new AppUser("27", "5y7", "768i876u", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", null));

        return userList;
    }

    public AppUser saveAppUser(AppUser appUser) throws Exception {
        SOSaveUser gso = new SOSaveUser();
        gso.executeSO(appUser);
        return gso.getUser();
    }
}
