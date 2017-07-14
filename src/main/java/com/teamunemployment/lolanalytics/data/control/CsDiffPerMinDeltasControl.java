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

    public static int EARLY_GAME = 0;
    public static int MID_GAME = 1;
    public static int LATE_GAME = 2;


    private DBHelper dbhelper;
    public CsDiffPerMinDeltasControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param csDiffPerMinDeltas the CPMD stats for a match
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

    /**
     * Fetch the difference (in creeps per minutes) between the selected summoner and the enemy. If the result is negative,
     * the enemy scored better.
     * @return The difference
     */
    public double LoadAverageDifferenceBetweenEnemyAndSummonerScore(long summonerId, String season, String role, String lane, int gameStage) {

        String gameStageString = "zeroToTen";
        switch(gameStage) {
            case 1:
                gameStageString = "tenToTwenty";
                break;
            case 2:
                gameStageString = "twentyToThirty";
        }
        String queryString = "select avg(csdiffpermindeltas."+gameStageString+") " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId " +
                "join csdiffpermindeltas on csdiffpermindeltas.id = timeline.csDiffPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' and season = '" + season + " '"+
                "order by csdiffpermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                throw new IllegalStateException("Database Error: Query returned no data.");
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(csdiffpermindeltas."+gameStageString+")");
                return average;
            }
        } catch (SQLException | IllegalStateException ex) {
            throw new IllegalStateException(ex.getLocalizedMessage());
        }
        return 0;
    }


}
