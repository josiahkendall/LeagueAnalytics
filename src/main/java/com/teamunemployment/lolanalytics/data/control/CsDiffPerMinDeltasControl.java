/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CsDiffPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.GoldPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall
 */
public class CsDiffPerMinDeltasControl {
    private DBHelper dbhelper;
        public CsDiffPerMinDeltasControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveCDPMD(CsDiffPerMinDeltas csDiffPerMinDeltas) {
        // temp fix.
        if (csDiffPerMinDeltas == null) {
            return 0;
        }
        try {
            String queryString = String.format(
                    "Insert into csdiffpermindeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    csDiffPerMinDeltas.getZeroToTen(),
                    csDiffPerMinDeltas.getTenToTwenty(),
                    csDiffPerMinDeltas.getTwentyToThirty(),
                    csDiffPerMinDeltas.getThirtyToEnd()
            );
            int resultId = dbhelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
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
    public CsDiffPerMinDeltas getGPMD(int csDPMDId) {
        try {
            String query = "SELECT * from csdiffpermindeltas WHERE Id = " + csDPMDId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                CsDiffPerMinDeltas csdpmd = new CsDiffPerMinDeltas();
                csdpmd.setId(csDPMDId);
                csdpmd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                csdpmd.setZeroToTen(resultSet.getDouble("TenToTwenty"));
                csdpmd.setZeroToTen(resultSet.getDouble("TwentyToThirty"));
                csdpmd.setZeroToTen(resultSet.getDouble("ThirtyToEnd"));
                
                return csdpmd;
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
     /**
     * Update a CPMD object that already exists.
     * @param cpmd the CreepsPerMinDelta object to update.
     * @return The result integer.
     */
    public int updateGPMD(CsDiffPerMinDeltas csdpm) {
        
        String queryString = String.format(
                    "UPDATE csdiffpermindeltas set "
                            + "ZeroToTen = %d, "
                            + "TenToTwenty = %d, "
                            + "TwentyToThirty = %d "
                            + "WHERE Id = %d",
                    csdpm.getZeroToTen(),
                    csdpm.getTenToTwenty(),
                    csdpm.getTwentyToThirty(),
                    csdpm.getThirtyToEnd(),
                    csdpm.getId()
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
