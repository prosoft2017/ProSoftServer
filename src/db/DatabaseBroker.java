/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constant.ConstantMessages;
import domain.chat.Message;
import domain.issue.Issue;
import domain.issue.IssueStatus;
import domain.user.AppUser;
import domain.user.StatusType;
import domain.task.Task;
import domain.task.TaskStatus;
import domain.user.Address;
import domain.user.Country;
import domain.user.UserTitle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
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
        try {
            String sql = "SELECT u.id, u.username, u.password, u.firstname, u.lastname, u.email, u.last_active, u.status, u.baned, u.title, "
                    + "a.id, a.city, a.postal_code, a.street_name, a.street_number, c.id, c.name "
                    + "FROM appuser u JOIN address a ON (u.address_id=a.id) JOIN country c ON(c.id=a.country_id) "
                    + "WHERE u.username ='" + appUser.getUsername() + "' LIMIT 1";

            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            boolean hasResult = rs.next();
            if (!hasResult || !appUser.getPassword().equals(rs.getString("u.password"))) {
                throw new Exception("Wrong credintials");
            }
            Country country = new Country(rs.getInt("c.id"), rs.getString("c.name"));
            Address address = new Address();
            address.setId(rs.getInt("a.id"));
            address.setCountry(country);
            address.setCity(rs.getString("a.city"));
            address.setPostalCode(rs.getString("a.postal_code"));
            address.setStreetNumber(rs.getString("a.street_number"));
            address.setStreetName(rs.getString("a.street_name"));

            appUser.setId(rs.getInt("u.id"));
            appUser.setAddress(address);
            appUser.setUsername(rs.getString("u.username"));
            appUser.setFirstname(rs.getString("u.firstname"));
            appUser.setLastname(rs.getString("u.lastname"));
            appUser.setEmail(rs.getString("u.email"));
            appUser.setLastActive(rs.getDate("u.last_active").toLocalDate());
            appUser.setStatus(StatusType.valueOf(rs.getString("u.status")));
            appUser.setBaned(rs.getBoolean("u.baned"));
            appUser.setTitle(UserTitle.valueOf(rs.getString("u.title")));

            appUser.setAllTasks(getAllTasksForUser(appUser));

            sqlStatement.close();
            rs.close();

            updateLastActiveForUser(appUser);

            return appUser;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public AppUser saveUser(AppUser appUser) throws SQLException {
        try {
            if (appUser.getId() != 0) {
                updateUser(appUser);
                return appUser;
            }
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

    public void updateUser(AppUser appUser) throws SQLException {
        try {
            Address address = appUser.getAddress();

            updateUserAddress(address);
            updateUserWithAddress(appUser);
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

    public void saveMessage(Message message) throws SQLException {
        try {
            String sql = "INSERT INTO message "
                    + "VALUES (NULL, ?, ?, ?, 1)";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, message.getMessageContent());
            sqlStatment.setInt(2, message.getAppUserSender().getId());
            sqlStatment.setInt(3, message.getAppUserReciver().get(0).getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public String getAllMessages(List<AppUser> appUser) throws SQLException {
        try {
            String message = "";
            String sql = "SELECT m.content as content, aps.username "
                    + "FROM message m "
                    + "JOIN appuser aps on(aps.id = m.user_sender_id) "
                    + " WHERE (user_sender_id = ? and user_reciver_id =? ) or (user_sender_id = ? and user_reciver_id = ?)";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setInt(1, appUser.get(0).getId());
            sqlStatment.setInt(2, appUser.get(1).getId());
            sqlStatment.setInt(3, appUser.get(1).getId());
            sqlStatment.setInt(4, appUser.get(0).getId());

            ResultSet rs = sqlStatment.executeQuery();
            while (rs.next()) {
                message += System.lineSeparator() + System.lineSeparator() + "@" + rs.getString("aps.username") + ": " + rs.getString("content");
            }
            sqlStatment.close();
            return message;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public Task updateUserTask(Task userTask) throws SQLException {
        try {
            String sql = "UPDATE task SET title=?, description=?, start_date=?, end_date=?, task_status=? WHERE id=?";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, userTask.getTitle());
            sqlStatment.setString(2, userTask.getDescription());
            sqlStatment.setDate(3, Date.valueOf(userTask.getStartDate()));
            sqlStatment.setDate(4, Date.valueOf(userTask.getEndDate()));
            sqlStatment.setString(5, userTask.getTaskStatus().toString());
            sqlStatment.setInt(6, userTask.getId());

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

    public Task closeUserTask(Task userTask) throws SQLException {
        try {
            String sql = "UPDATE task SET task_status = ? WHERE id = ?";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, TaskStatus.DELETED.toString());
            sqlStatment.setInt(2, userTask.getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();

            userTask.setTaskStatus(TaskStatus.DELETED);
            return userTask;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public List<Issue> getAllIssues() throws SQLException {
        try {
            List<Issue> issues = new ArrayList<>();

            String sql = "SELECT u.id, u.username, u.firstname, u.lastname, u.email, u.last_active, u.status, u.baned, u.title, "
                    + "i.id as issue_id, i.title, i.description, i.help_text, i.created_at, i.issue_status, i.resolved_at "
                    + "FROM issue i JOIN appuser u ON (u.id=i.appuser_id)";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                AppUser appUser = new AppUser();
                appUser.setId(rs.getInt("u.id"));
                appUser.setUsername(rs.getString("u.username"));
                appUser.setFirstname(rs.getString("u.firstname"));
                appUser.setLastname(rs.getString("u.lastname"));
                appUser.setEmail(rs.getString("u.email"));
                appUser.setLastActive(rs.getDate("u.last_active").toLocalDate());
                appUser.setStatus(StatusType.valueOf(rs.getString("u.status")));
                appUser.setBaned(rs.getBoolean("u.baned"));
                appUser.setTitle(UserTitle.valueOf(rs.getString("u.title")));

                Issue issue = new Issue();
                issue.setId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("i.title"));
                issue.setDescription(rs.getString("i.description"));
                issue.setHelpText(rs.getString("i.help_text"));
                issue.setCreatedAt(rs.getDate("i.created_at").toLocalDate());
                issue.setIssueStatus(IssueStatus.valueOf(rs.getString("i.issue_status")));
                issue.setAppUser(appUser);

                issues.add(issue);
            }

            sqlStatement.close();
            rs.close();

            return issues;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public List<AppUser> getAllUsers() throws SQLException {
        try {
            List<AppUser> userList = new ArrayList<>();

            String sql = "SELECT u.id, u.username, u.firstname, u.lastname, u.email, u.last_active, u.status, u.baned, u.title, "
                    + "a.id, a.city, a.postal_code, a.street_name, a.street_number, c.id, c.name "
                    + "FROM appuser u JOIN address a ON (u.address_id=a.id) JOIN country c ON(c.id=a.country_id)";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                Country country = new Country(rs.getInt("c.id"), rs.getString("c.name"));
                Address address = new Address();
                address.setId(rs.getInt("a.id"));
                address.setCountry(country);
                address.setCity(rs.getString("a.city"));
                address.setPostalCode(rs.getString("a.postal_code"));
                address.setStreetNumber(rs.getString("a.street_number"));
                address.setStreetName(rs.getString("a.street_name"));

                AppUser appUser = new AppUser();
                appUser.setId(rs.getInt("u.id"));
                appUser.setAddress(address);
                appUser.setUsername(rs.getString("u.username"));
                appUser.setFirstname(rs.getString("u.firstname"));
                appUser.setLastname(rs.getString("u.lastname"));
                appUser.setEmail(rs.getString("u.email"));
                appUser.setLastActive(rs.getDate("u.last_active").toLocalDate());
                appUser.setStatus(StatusType.valueOf(rs.getString("u.status")));
                appUser.setBaned(rs.getBoolean("u.baned"));
                appUser.setTitle(UserTitle.valueOf(rs.getString("u.title")));

                appUser.setAllTasks(getAllTasksForUser(appUser));

                userList.add(appUser);
            }

            sqlStatement.close();
            rs.close();

            return userList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            throw ex;
        }
    }

    public List<Task> getAllTasksForUser(AppUser appUser) throws SQLException {
        try {
            List<Task> taskList = new ArrayList<>();

            String sql = "SELECT * FROM task WHERE appuser_id = " + appUser.getId();
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
        String sqlAddress = "INSERT INTO address(city, postal_code, street_number, street_name,  country_id) VALUES (?,?,?,?, ?)";
        PreparedStatement sqlAddressStatement = connection.prepareStatement(sqlAddress);
        sqlAddressStatement.setString(1, address.getCity());
        sqlAddressStatement.setString(2, address.getPostalCode());
        sqlAddressStatement.setString(3, address.getStreetNumber());
        sqlAddressStatement.setString(4, address.getStreetName());
        sqlAddressStatement.setInt(5, address.getCountry().getId());

        sqlAddressStatement.executeUpdate();
        sqlAddressStatement.close();
    }

    private void updateUserAddress(Address address) throws SQLException {
        String sqlAddress = "UPDATE address SET city=?, postal_code=?, street_number=?, street_name=?, country_id=? WHERE id=?";
        PreparedStatement sqlAddressStatement = connection.prepareStatement(sqlAddress);
        sqlAddressStatement.setString(1, address.getCity());
        sqlAddressStatement.setString(2, address.getPostalCode());
        sqlAddressStatement.setString(3, address.getStreetNumber());
        sqlAddressStatement.setString(4, address.getStreetName());
        sqlAddressStatement.setInt(5, address.getCountry().getId());
        sqlAddressStatement.setInt(6, address.getId());

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
        String sql = "INSERT INTO appuser(username, email, firstname, lastname, `status`, baned, image_path, `password`, last_active, title, address_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        sqlStatment.setString(10, appUser.getTitle().toString());
        sqlStatment.setInt(11, appUser.getAddress().getId());

        sqlStatment.executeUpdate();
        sqlStatment.close();
    }

    private void updateUserWithAddress(AppUser appUser) throws SQLException {
        String sql = "UPDATE appuser SET email=?, firstname=?, lastname=?, `status`=?, baned=?, image_path=?, last_active=?, address_id=? "
                + "WHERE id= ?";
        PreparedStatement sqlStatment = connection.prepareStatement(sql);
        sqlStatment.setString(1, appUser.getEmail());
        sqlStatment.setString(2, appUser.getFirstname());
        sqlStatment.setString(3, appUser.getLastname());
        sqlStatment.setString(4, appUser.getStatus().toString());
        sqlStatment.setBoolean(5, appUser.isBaned());
        sqlStatment.setString(6, appUser.getImagePath());
        sqlStatment.setDate(7, Date.valueOf(appUser.getLastActive()));
        sqlStatment.setInt(8, appUser.getAddress().getId());
        sqlStatment.setInt(9, appUser.getId());

        sqlStatment.executeUpdate();
        sqlStatment.close();
    }

    public void reportIssue(Issue issue) throws SQLException {
        try {
            String sql = "INSERT INTO issue "
                    + "VALUES (NULL, ?, ?, ?, ?, ?, NULL, ?)";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setString(1, issue.getTitle());
            sqlStatment.setString(2, issue.getDescription());
            sqlStatment.setString(3, issue.getHelpText());
            sqlStatment.setDate(4, Date.valueOf(issue.getCreatedAt()));
            sqlStatment.setString(5, issue.getIssueStatus().toString());
            sqlStatment.setInt(6, issue.getAppUser().getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public boolean banUser(AppUser appUser) throws SQLException {
        try {
            String sql = "UPDATE appuser SET baned = 1 WHERE id = ?";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setInt(1, appUser.getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();

            appUser.setBaned(true);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public void updateIssues(Issue issue) throws SQLException {
        try {
            String sql = "UPDATE issue SET issue_status = '" + issue.getIssueStatus() + "' WHERE id = ?";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setInt(1, issue.getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();
        } catch (SQLException ex) {
            System.out.println("usao");
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    private void updateLastActiveForUser(AppUser appUser) throws SQLException {
        LocalDate lastActive = LocalDate.now();
        try {
            String sql = "UPDATE appuser SET last_active = ? WHERE id = ?";
            PreparedStatement sqlStatment = connection.prepareStatement(sql);
            sqlStatment.setDate(1, Date.valueOf(lastActive));
            sqlStatment.setInt(2, appUser.getId());

            sqlStatment.executeUpdate();
            sqlStatment.close();

            appUser.setLastActive(lastActive);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
