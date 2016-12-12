/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class SummonerTableAccessor {
    
    private DBHelper dbHelper;
    
    public SummonerTableAccessor(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    /**
     * Fetch a summoner by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public SummonerInfo getSummoner(long summonerId) {
        try {
            String query = "SELECT * from summonerinfo WHERE id = " + summonerId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                SummonerInfo summonerInfo = new SummonerInfo();
                summonerInfo.id = summonerId;
                summonerInfo.name = resultSet.getString("SummonerName");
                summonerInfo.profileIconId = resultSet.getInt("ProfileIconId");
                summonerInfo.summonerLevel = resultSet.getInt("SummonerLevel");
                summonerInfo.revisionDate = resultSet.getLong("RevisionDate");
                
                return summonerInfo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Save a summoner
     * @param summoner The summoner to save
     * @return the id of the new row, or 0 if unsuccessful.
     */
    public int saveSummoner(SummonerInfo summoner) {
        try {
            String queryString = String.format(
                    "Insert into summonerinfo values (%d, %s, %d, %d, %d",
                    summoner.id,
                    summoner.name,
                    summoner.profileIconId,
                    summoner.summonerLevel,
                    summoner.revisionDate
            );
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    /**
     * Update a summonerinfo object that already exists.
     * @param summoner The summoner object to update.
     * @return The result integer.
     */
    public int updateSummoner(SummonerInfo summoner) {
        
        String queryString = String.format(
                    "UPDATE summonerinfo set SummonerLevel = %d, "
                            + "SummonerName = %s, "
                            + "ProfileIconId = %d, "
                            + "RevisionDate = %d "
                            + "WHERE Id = %d",
                    summoner.summonerLevel,
                    summoner.name,
                    summoner.profileIconId,
                    summoner.revisionDate,
                    summoner.id
            );
         try {
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
