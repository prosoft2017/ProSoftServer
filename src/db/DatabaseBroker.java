/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constant.ConstantMessages;
import domain.user.AppUser;
import domain.user.StatusType;
import domain.task.Task;
import domain.task.TaskStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.FileUtil;

/**
 *
 * @author Filip
 */
public class DatabaseBroker {

    private Connection connection;
    private static DatabaseBroker instance;

    private DatabaseBroker() {

    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public void loadDriver() throws Exception {
        try {
            Class.forName(FileUtil.getInstance().get("driver"));
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }

    public void openConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(FileUtil.getInstance().get("url"), FileUtil.getInstance().get("user"), FileUtil.getInstance().get("password"));
            connection.setAutoCommit(false);
            // Zahteva eksplicitnu potvrdu transakcije
        } catch (SQLException ex) {
            throw new Exception("Error while opening connection with database!", ex);
        }
    }

    public void commitTransaction() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan commit transakcije!", ex);
        }
    }

    public void rollbackTransaction() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Error while rollback transaction!", ex);
        }
    }

    public void closeConection() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Error while closing connection with database!", ex);
        }
    }

    public AppUser validateUser(Object obj) throws Exception {
        if (!(obj instanceof AppUser)) {
            throw new Exception(ConstantMessages.SYSTEM_ERROR_MSG);
        }

        AppUser appUser = (AppUser) obj;

        switch (appUser.getUsername()) {
            case "fiki":
            case "admin":
                return appUser
                        .setFirstname("Filip")
                        .setLastname("Admin");
            default:
                throw new Exception("Please provide right credintials!!!");
        }
    }

    public AppUser saveUser(AppUser appUser) throws SQLException {
        try {
            String sql = "INSERT INTO Address VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
//            sqlStatement.setInt(1, pp.getPartnerID());
//            sqlStatement.setString(2, pp.getNaziv());
//            sqlStatement.setInt(3, pp.getPib());
//            sqlStatement.setInt(4, pp.getMaticniBroj());
//            sqlStatement.setDate(5, new java.sql.Date(pp.getDatumOsnivanja().getTime()));
//            sqlStatement.setString(6, pp.getZiroRacun());
//            sqlStatement.setString(7, pp.getUlica());
//            sqlStatement.setString(8, pp.getBroj());
//            sqlStatement.setInt(9, pp.getMesto().getPtt());

            sqlStatement.executeUpdate();
            sqlStatement.close();

            return appUser;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser("1", "Pera", "Peric", "qwer@qwer.qwer", StatusType.Active, true, "qwer", "sfas", null));
        userList.add(new AppUser("2", "Admin", "Adminovic", "lllllllllllllll@qwer.qwer", StatusType.Active, true, "22222", "asfdasd", null));
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

        Task t = new Task();
        t.setTitle("Task 1");
        t.setAppUser(userList.get(0));
        List<Task> listT = new ArrayList<>();
        listT.add(t);
        userList.forEach((user) -> {
            user.setAllTasks(listT);
            user.setLastActive(LocalDate.now());
        });
        return userList;
    }

    public List<Task> getAllTasksForUser(AppUser appUser) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("User All Page", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now(), LocalDate.now().plusDays(7), TaskStatus.Active));
        taskList.add(new Task("User CRUD Page", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now(), LocalDate.now().plusDays(7), TaskStatus.Active));
        taskList.add(new Task("User Tasks All Page", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now().minusDays(18), LocalDate.now().minusDays(7), TaskStatus.Finished));
        taskList.add(new Task("Meeting Schedule", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now(), LocalDate.now().plusDays(7), TaskStatus.Active));
        taskList.add(new Task("Backup Information", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now(), LocalDate.now().plusDays(7), TaskStatus.Active));
        taskList.add(new Task("Meeting Schedule", "Page JPanelUserAll needs to be updated to have posibility to filter users by date they were last active",
                appUser, LocalDate.now(), LocalDate.now().plusDays(7), TaskStatus.Active));

        return taskList;
    }

    public Task saveUserTask(Task userTask) {

        return userTask;
    }

    public Task closeUserTask(Task userTask) {

        return userTask;
    }
}
