/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author jek40
 */
public class DatabaseTests {
    
    @Test
    public void TestLocalDBConnection() {
        DBHelper dbHelper = new DBHelper();
        boolean canConnect = dbHelper.Connect();
        Assert.assertTrue(canConnect);
    }
    
//    @Test
//    public void TestThatWeCanCreateTables() throws SQLException  {
//        DBHelper dbHelper = new DBHelper();
//        boolean canConnect = dbHelper.DbConnect("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
//        Assert.assertTrue(canConnect);
//        String query = "CREATE TABLE test(Test varchar(16))";
//        int result = dbHelper.ExecuteSqlScript(query);
//        assertTrue(result == 0);
//    }
    
    @Test
    public void TestThatWeCanSavePlayer() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        boolean canConnect = dbHelper.Connect();
        Assert.assertTrue(canConnect);
        
        String query = "Insert into summonerinfo values (123456, \"kloin\", 1, 30, 12345)";
        int result = dbHelper.ExecuteSqlScript(query);
        assertTrue(result == 1);
    }
    
    
    
    
}
