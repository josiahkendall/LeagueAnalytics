/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.models.stats.summary;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class SummaryModel {

    private DBHelper dbHelper;
    public SummaryModel(DBHelper dbHepler) {
        this.dbHelper = dbHepler;
    }
    
    public int GetAllWins(long summonerId, String season, int champId) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Get the total number of ranked games won by a summoner.
     * @param summonerId
     * @return The number of ranked games that this summoner has won.
     */
    public int getRankedWinsAllTime(int summonerId) {
        // Fetch all the participant instances that match our summonerId. 
         try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s",
                    summonerId);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int winCount = 0;
            while (result.next()) {
                if (testIfMatchWasAWin(result)) {
                    winCount += 1;
                }
            }
            return winCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }
    
        
    public int getRankedLossesAllTime(int summonerId) {
        try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s",
                    summonerId);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int lossCount = 0;
            while (result.next()) {
                if (!testIfMatchWasAWin(result)) {
                    lossCount += 1;
                }
            }
            return lossCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }
    
    public int getRankedWinsSeason(long summonerId, String season) {
        try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s",
                    summonerId);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int winCount = 0;
            while (result.next()) {
                if (testIfMatchWasAWin(result)) {
                    winCount += 1;
                }
            }
            return winCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }
    
    public int getRankedlossesSeason(long summonerId, String season) {
        try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s and season = %s",
                    summonerId, 
                    season);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int lossCount = 0;
            while (result.next()) {
                if (!testIfMatchWasAWin(result)) {
                    lossCount += 1;
                }
            }
            return lossCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }

    
    public int getRankedWinsSeasonAndChampSpecific(long summonerId, String season, int champion) {
         try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s and season = %s and championId = %d",
                    summonerId, 
                    season,
                    champion);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int winCount = 0;
            while (result.next()) {
                if (testIfMatchWasAWin(result)) {
                    winCount += 1;
                }
            }
            return winCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }
    
    public int getRankedlossesSeasonAndChampSpecific(long summonerId, String season, int champion) {
        try {
            String queryString = String.format(
                    "select * from participantSummary where summonerId = %s and season = %s and championId = %d",
                    summonerId, 
                    season, champion);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            int lossCount = 0;
            while (result.next()) {
                if (!testIfMatchWasAWin(result)) {
                    lossCount += 1;
                }
            }
            return lossCount;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        // Count the number of participant instance where win = WIN_REFERENCE 
        return 0;
    }
    
    public int GetAllLosses(long summonerId, String season, int champId) {
        return 0;
    }

    // Test if the result givenw as a win.
    private boolean testIfMatchWasAWin(ResultSet next) {
        try {
            // Get row with stat id.
            String statId = next.getString("StatId");
            
            // fetch statistics row.
            String queryString = String.format(
                    "select * from Stats where Id = %s",
                    statId);
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            if (result.next()) {
                int winner =  result.getInt("Winner");
                System.out.println(winner);
                return winner == 1;
            }
           
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Exception occurred. Exception follows");
            ex.printStackTrace();
        }  
        
        return false;
    }
    
}
