/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.XpPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class XpPerMinDeltaControl {
    private DBHelper dbhelper;
    
    public XpPerMinDeltaControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveXpPMD(XpPerMinDeltas xpPerMinDeltas) {
        try {
            String queryString = String.format(
                    "Insert into xppermindeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    xpPerMinDeltas.getZeroToTen(),
                    xpPerMinDeltas.getTenToTwenty(),
                    xpPerMinDeltas.getTwentyToThirty(),
                    xpPerMinDeltas.getThirtyToEnd()
            );
            int resultId = dbhelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Error occurred saving xp per min deltas. Error message is: "  + ex.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Fetch a summoner by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public XpPerMinDeltas getXpPMD(int XpPMDId) {
        try {
            String query = "SELECT * from xppermindeltas WHERE Id = " + XpPMDId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                XpPerMinDeltas xpPmd = new XpPerMinDeltas();
                xpPmd.setId(XpPMDId);
                xpPmd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                xpPmd.setZeroToTen(resultSet.getDouble("TenToTwenty"));
                xpPmd.setZeroToTen(resultSet.getDouble("TwentyToThirty"));
                xpPmd.setZeroToTen(resultSet.getDouble("ThirtyToEnd"));
                
                return xpPmd;
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
    public int updateXpPMD(XpPerMinDeltas XpPMDId) {
        
        String queryString = String.format(
                    "UPDATE xppermindeltas set "
                            + "ZeroToTen = %d, "
                            + "TenToTwenty = %d, "
                            + "TwentyToThirty = %d "
                            + "WHERE Id = %d",
                    XpPMDId.getZeroToTen(),
                    XpPMDId.getTenToTwenty(),
                    XpPMDId.getTwentyToThirty(),
                    XpPMDId.getThirtyToEnd(),
                    XpPMDId.getId()
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
