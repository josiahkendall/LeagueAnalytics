/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.GoldPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.XpPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class GoldPerMinDeltasControl {
    private DBHelper dbhelper;
        public GoldPerMinDeltasControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param goldPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveGPMD(GoldPerMinDeltas goldPerMinDeltas) {
        try {
            String queryString = String.format(
                    "Insert into goldpermindeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    goldPerMinDeltas.getZeroToTen(),
                    goldPerMinDeltas.getTenToTwenty(),
                    goldPerMinDeltas.getTwentyToThirty(),
                    goldPerMinDeltas.getThirtyToEnd()
            );
            int resultId = dbhelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Error occurred saving gold per min detlas. Exception follows : \n" + ex.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Fetch a summoner by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public GoldPerMinDeltas getGPMD(int GPMDId) {
        try {
            String query = "SELECT * from goldpermindeltas WHERE Id = " + GPMDId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                GoldPerMinDeltas gpmd = new GoldPerMinDeltas();
                gpmd.setId(GPMDId);
                gpmd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                gpmd.setTenToTwenty(resultSet.getDouble("TenToTwenty"));
                gpmd.setTwentyToThirty(resultSet.getDouble("TwentyToThirty"));
                gpmd.setThirtyToEnd(resultSet.getDouble("ThirtyToEnd"));
                
                return gpmd;
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
    public int updateGPMD(GoldPerMinDeltas gpmd) {
        
        String queryString = String.format(
                    "UPDATE goldpermindeltas set "
                            + "ZeroToTen = %s, "
                            + "TenToTwenty = %s, "
                            + "TwentyToThirty = %s "
                            + "WHERE Id = %s",
                    gpmd.getZeroToTen(),
                    gpmd.getTenToTwenty(),
                    gpmd.getTwentyToThirty(),
                    gpmd.getThirtyToEnd(),
                    gpmd.getId()
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
