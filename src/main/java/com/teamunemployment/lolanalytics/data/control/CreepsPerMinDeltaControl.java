/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class CreepsPerMinDeltaControl {
    
    private DBHelper dbhelper;
    
    public CreepsPerMinDeltaControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveCPMD(CreepsPerMinDeltas creepsPerMinDeltas) {
        try {
            String queryString = String.format(
                    "Insert into creepspermindeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    creepsPerMinDeltas.getZeroToTen(),
                    creepsPerMinDeltas.getTenToTwenty(),
                    creepsPerMinDeltas.getTwentyToThirty(),
                   creepsPerMinDeltas.getThirtyToEnd()
          );
            int result = dbhelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return 0;
    }
    
    /**
     * Fetch a summoner by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public CreepsPerMinDeltas getCPMD(int cpmdId) {
        try {
            String query = "SELECT * from creepspermindeltas WHERE Id = " + cpmdId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                CreepsPerMinDeltas cmpd = new CreepsPerMinDeltas();
                cmpd.setId(cpmdId);
                cmpd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                cmpd.setTenToTwenty(resultSet.getDouble("TenToTwenty"));
                cmpd.setTwentyToThirty(resultSet.getDouble("TwentyToThirty"));
                cmpd.setThirtyToEnd(resultSet.getDouble("ThirtyToEnd"));
                
                return cmpd;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred when getting creeps per min deltas: " + ex.getMessage());
        }
        
        return null;
    }
    
     /**
     * Update a CPMD object that already exists.
     * @param cpmd the CreepsPerMinDelta object to update.
     * @return The result integer.
     */
    public int updateCPMD(CreepsPerMinDeltas cpmd) {
        
        String queryString = String.format(
                    "UPDATE creepspermindeltas set "
                            + "ZeroToTen = %d, "
                            + "TenToTwenty = %d, "
                            + "TwentyToThirty = %d "
                            + "WHERE Id = %d",
                    cpmd.getZeroToTen(),
                    cpmd.getTenToTwenty(),
                    cpmd.getTwentyToThirty(),
                    cpmd.getThirtyToEnd(),
                    cpmd.getId()
            );
         try {
            int result = dbhelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    
}
