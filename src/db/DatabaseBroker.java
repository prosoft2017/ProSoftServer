/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.AppUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
