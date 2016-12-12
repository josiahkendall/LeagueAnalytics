/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.database;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 *
 * @author jek40
 */
public class SaveTableAccessorTests {
    
    @Test
    public void TestThatWeCanSaveCorrectly() throws SQLException {
        DBHelper dbHelper = Mockito.mock(DBHelper.class);
        SummonerTableAccessor summonerTableAccessor = new SummonerTableAccessor(dbHelper);
        
        SummonerInfo summoner = new SummonerInfo();
        summoner.id = 1;
        summoner.name = "test";
        summoner.profileIconId = 1;
        summoner.revisionDate = 11111;
        summoner.summonerLevel = 30;
        summonerTableAccessor.saveSummoner(summoner);
        String correctQuery = "Insert into summonerinfo values (1, test, 1, 30, 11111";
        Mockito.verify(dbHelper, Mockito.times(1)).ExecuteSqlScript(correctQuery);
    }
    
    @Test
    public void TestThatWeCanFetchSummoner() throws SQLException {
        DBHelper dbHelper = Mockito.mock(DBHelper.class);
        SummonerTableAccessor summonerTableAccessor = new SummonerTableAccessor(dbHelper);
        
        // Create our mock response from the database.
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getString("SummonerName")).thenReturn("test");
        Mockito.when(resultSet.getInt("ProfileIconId")).thenReturn(1);
        Mockito.when(resultSet.getInt("SummonerLevel")).thenReturn(30);
        Mockito.when(resultSet.getLong("RevisionDate")).thenReturn(new Long(111111));
        Mockito.when(resultSet.next()).thenReturn(true);

        // Ensure that when we call that mock response that we 
        Mockito.when(dbHelper.ExecuteSqlQuery(Matchers.anyString())).thenReturn(resultSet);
        
        SummonerInfo summoner = summonerTableAccessor.getSummoner(1);
        
        assertTrue(summoner.id == 1);
        assertTrue(summoner.name.equals("test"));
        assertTrue(summoner.profileIconId == 1);
        assertTrue(summoner.revisionDate == 111111);
        assertTrue(summoner.summonerLevel == 30);
    }
    
}
