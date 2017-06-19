/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constant.ConstantMessages;
import domain.issue.Issue;
import domain.user.AppUser;
import domain.user.StatusType;
import domain.task.Task;
import domain.task.TaskStatus;
import domain.user.Address;
import domain.user.Country;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Address address = appUser.getAddress();

            saveUserAddress(address);
            int addressId = getLastAddressId();
            address.setId(addressId);
            saveUserWithAddress(appUser);

            return appUser;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public Task saveUserTask(Task userTask) throws SQLException {
        try {
            String sql = "INSERT INTO task(title, description, start_date, end_date, task_status, appuser_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, userTask.getTitle());
            sqlStatment.setString(2, userTask.getDescription());
            sqlStatment.setDate(3, Date.valueOf(userTask.getStartDate()));
            sqlStatment.setDate(4, Date.valueOf(userTask.getEndDate()));
            sqlStatment.setString(5, userTask.getTaskStatus().toString());
            sqlStatment.setInt(6, userTask.getAppUser().getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();
            return userTask;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public Issue saveUserIssue(Issue issue) throws SQLException {
        try {
            String sql = "INSERT INTO issue(title, description, help_text, created_at, issue_status, resolved_at, appuser_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, issue.getTitle());
            sqlStatment.setString(2, issue.getDescription());
            sqlStatment.setString(3, issue.getHelpText());
            sqlStatment.setDate(4, Date.valueOf(issue.getCreatedAt()));
            sqlStatment.setString(5, issue.getIssueStatus().toString());
            sqlStatment.setDate(6, Date.valueOf(issue.getResolvedAt()));
            sqlStatment.setInt(7, issue.getAppUser().getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();

            return issue;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public Task closeUserTask(Task userTask) {

        return userTask;
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> userList = new ArrayList<>();
        Address a = new Address();
        Address b = new Address();
        a.setCountry(new Country(1, "Serbia"));
        b.setCountry(new Country(2, "France"));
        userList.add(new AppUser("1", "Pera", "Peric", "qwer@qwer.qwer", StatusType.Active, true, "qwer", "sfas", a));
        userList.add(new AppUser("2", "Admin", "Adminovic", "lllllllllllllll@qwer.qwer", StatusType.Active, true, "22222", "asfdasd", a));
        userList.add(new AppUser("3", "gdsfg", "34523", "asdfasd", StatusType.Active, true, "22222", "asdfa222222sd", a));
        userList.add(new AppUser("4", "gdfg", "asdf2222222asdfa", "asdfsaf@qwer.qwer", StatusType.Pending, false, "22222", "asdfsd", a));
        userList.add(new AppUser("5", "q22werrtsert22q", "23423", "lllllllllllllll@qwer.qwer", StatusType.Locked, true, "22222", "ewrwq", a));
        userList.add(new AppUser("6", "s", "asdf2222222asdfa", "dsfgsd@qwer.qwer", StatusType.Active, true, "23423", "234234", a));
        userList.add(new AppUser("7", "sert", "asdf2222222asdfa", "23d4r43r@qwer.qwer", StatusType.Active, false, "grt", "asdfa222222sd", a));
        userList.add(new AppUser("8", "q22wgdfger22q", "asdf2222222asdfa", "fsdf@qwer.qwer", StatusType.Locked, true, "1231", "asdfa222222sd", a));
        userList.add(new AppUser("9", "34", "asdf2222222asdfa", "dsfrs@qwer.qwer", StatusType.Pending, true, "123123", "423", a));
        userList.add(new AppUser("10", "ewt", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", a));
        userList.add(new AppUser("11", "ew45y64t", "ascf435", "asdcdsafcsdfads@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", a));
        userList.add(new AppUser("12", "ewt", "asdf2222222asdfa", "12312312@qwer.qwer", StatusType.Active, true, "123123", "sadfsad", a));
        userList.add(new AppUser("13", "5y7", "768i876u", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", a));
        userList.add(new AppUser("14", "ewt", "ij87ij87", "ascdfdsacf@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", b));
        userList.add(new AppUser("15", "ewt", "uj67juh", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", b));
        userList.add(new AppUser("16", "45y654", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", b));
        userList.add(new AppUser("17", "45y645y", "uh567hu", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", b));
        userList.add(new AppUser("18", "gdfg", "asdf2222222asdfa", "asdfsaf@qwer.qwer", StatusType.Pending, false, "22222", "asdfsd", b));
        userList.add(new AppUser("19", "q22werrtsert22q", "23423", "lllllllllllllll@qwer.qwer", StatusType.Locked, true, "22222", "ewrwq", b));
        userList.add(new AppUser("20", "s", "asdf2222222asdfa", "dsfgsd@qwer.qwer", StatusType.Active, true, "23423", "234234", b));
        userList.add(new AppUser("21", "sert", "asdf2222222asdfa", "23d4r43r@qwer.qwer", StatusType.Active, false, "grt", "asdfa222222sd", b));
        userList.add(new AppUser("22", "q22wgdfger22q", "asdf2222222asdfa", "fsdf@qwer.qwer", StatusType.Locked, true, "1231", "asdfa222222sd", b));
        userList.add(new AppUser("23", "34", "asdf2222222asdfa", "dsfrs@qwer.qwer", StatusType.Pending, true, "123123", "423", b));
        userList.add(new AppUser("24", "ewt", "asdf2222222asdfa", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", a));
        userList.add(new AppUser("25", "ew45y64t", "ascf435", "asdcdsafcsdfads@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", a));
        userList.add(new AppUser("26", "ewt", "asdf2222222asdfa", "12312312@qwer.qwer", StatusType.Active, true, "123123", "sadfsad", a));
        userList.add(new AppUser("27", "5y7", "768i876u", "456fd@qwer.qwer", StatusType.Active, true, "22222", "sadfsad", b));

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

    public List<Task> getAllTasksForUser(AppUser appUser) throws SQLException {
        try {
            List<Task> taskList = new ArrayList<>();

            String sql = "SELECT * FROM task WHERE appuser_id = 1";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStartDate(rs.getDate("start_date").toLocalDate());
                task.setEndDate(rs.getDate("end_date").toLocalDate());
                task.setTaskStatus(TaskStatus.valueOf(rs.getString("task_status")));
                task.setAppUser(appUser);

                taskList.add(task);
            }

            sqlStatement.close();
            rs.close();

            return taskList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public List<Country> getAllAvailableCountries() throws SQLException {
        try {
            List<Country> countryList = new ArrayList<>();

            String sql = "SELECT * FROM country";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));

                countryList.add(country);
            }

            sqlStatement.close();
            rs.close();

            return countryList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    private void saveUserAddress(Address address) throws SQLException {
        String sqlAddress = "INSERT INTO address(city, postal_code, street_number, country_id) VALUES (?,?,?,?)";
        PreparedStatement sqlAddressStatement = connection.prepareStatement(sqlAddress);
        sqlAddressStatement.setString(1, address.getCity());
        sqlAddressStatement.setString(2, address.getPostalCode());
        sqlAddressStatement.setString(3, address.getStreetNumber());
        sqlAddressStatement.setInt(4, address.getCountry().getId());

        sqlAddressStatement.executeUpdate();
        sqlAddressStatement.close();
    }

    private int getLastAddressId() throws SQLException {
        String addressIdSql = "SELECT id FROM address ORDER BY id DESC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(addressIdSql);
        rs.next();

        int id = rs.getInt("id");
        statement.close();
        rs.close();

        return id;
    }

    private void saveUserWithAddress(AppUser appUser) throws SQLException {
        String sql = "INSERT INTO appuser(username, email, firstname, lastname, `status`, baned, image_path, `password`, last_active, address_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sqlStatment = connection.prepareStatement(sql);
        sqlStatment.setString(1, appUser.getUsername());
        sqlStatment.setString(2, appUser.getEmail());
        sqlStatment.setString(3, appUser.getFirstname());
        sqlStatment.setString(4, appUser.getLastname());
        sqlStatment.setString(5, appUser.getStatus().toString());
        sqlStatment.setBoolean(6, appUser.isBaned());
        sqlStatment.setString(7, appUser.getImagePath());
        sqlStatment.setString(8, appUser.getPassword());
        sqlStatment.setDate(9, Date.valueOf(appUser.getLastActive()));
        sqlStatment.setInt(10, appUser.getAddress().getId());

        sqlStatment.executeUpdate();
        sqlStatment.close();
    }

    public boolean banUser(AppUser appUser) {
        appUser.setBaned(true);
        
        return true;
    }
}
