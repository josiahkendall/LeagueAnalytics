/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.database;

import com.google.cloud.sql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall.
 * 
 * A helper class for the connecting to and run queries against a database.
 */
public class DBHelper {
    private String host;
    private String username;
    private String password;
    
    private Connection connection;
    private boolean currentlyConnected = false;
    
    /**
     * Creates a default localhost connection;
     */
    public DBHelper() {
        this.host = "jdbc:mysql://localhost:3306/local_lolanlaytics";
        this.username = "root";
        this.password = "Idnw2bh2";
    }
    
    public DBHelper(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }
    /**
     * Connect to a database instance.
     */
    public boolean Connect() {
        try {
            connection = DriverManager.getConnection(host, username, password);
            currentlyConnected = connection.isValid(1000);
            return currentlyConnected;
        } catch (SQLException sqlException) {
            System.out.println("An error occured whist getting a connection to the database.\n"
                    + "host: "+ host + "\n"
                    + "username: " + username );
            System.out.println(sqlException.getMessage());
            return false;
        } 
    }
    
    public void Disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Execute a query against the database that we are currently connected to.
     * Use execute sql script if you are updating, deleting, or creating data.
     * @param query The query to execute
     * @return The result.
     * @throws SQLException
     * @throws IllegalStateException 
     */
    public ResultSet ExecuteSqlQuery(String query) throws SQLException, IllegalStateException {
        if (connection == null || !currentlyConnected) {
            throw new IllegalStateException("No Current database connection. Use DbConnect() to connect to a database");
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    
    /**
     * Execute a statement that updates the database
     * @param query The query to run against the database.
     * @return 0 if nothing, or the number of rows effected (i think).
     * @throws SQLException
     * @throws IllegalStateException 
     */
    public int ExecuteSqlScript(String query) throws SQLException, IllegalStateException {
        if (connection == null || !currentlyConnected) {
            throw new IllegalStateException("No Current database connection. Use DbConnect() to connect to a database");
        }
        
        Statement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int resultSet = statement.executeUpdate(query);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            resultSet = (int) generatedKeys.getLong(1);
        }
        return resultSet;
    }
}
