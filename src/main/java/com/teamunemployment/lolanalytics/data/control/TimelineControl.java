/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.CsDiffPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.GoldPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import com.teamunemployment.lolanalytics.models.stats.XpPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall
 */
public class TimelineControl {
    
    private CreepsPerMinDeltaControl creepsPerMinDeltaControl;
    private CsDiffPerMinDeltasControl csDiffPerMinDeltasControl;
    private GoldPerMinDeltasControl goldPerMinDeltasControl;
    private XpPerMinDeltaControl xpPerMinDeltaControl;
    private DBHelper dbHelper;
    
    public TimelineControl (CreepsPerMinDeltaControl cmpControl, CsDiffPerMinDeltasControl csdContorl,
            GoldPerMinDeltasControl gpmControl, XpPerMinDeltaControl xpPerMinDeltaControl, DBHelper dbHelper) {
        this.creepsPerMinDeltaControl = cmpControl;
        this.csDiffPerMinDeltasControl = csdContorl;
        this.goldPerMinDeltasControl = gpmControl;
        this.xpPerMinDeltaControl = xpPerMinDeltaControl;
        this.dbHelper = dbHelper;
    }
    
    public int SaveTimeline(Timeline timeline) {
        int cpmdId = creepsPerMinDeltaControl.saveCPMD(timeline.getCreepsPerMinDeltas());
        int csdiffId = csDiffPerMinDeltasControl.saveCDPMD(timeline.getCsDiffPerMinDeltas());
        int gpmdId = goldPerMinDeltasControl.saveGPMD(timeline.getGoldPerMinDeltas());
        int xpmId = xpPerMinDeltaControl.saveXpPMD(timeline.getXpPerMinDeltas());
        int damageTakenPerMinDeltaId = 0;
        int damageTakenDiffPerMinDeltaId = 0;
        
        try {
            String queryString = String.format(
                    "Insert into timeline ("
                            + "CreepsPerMinDeltaId,"
                            + "XpPerMinDeltaId,"
                            + "GoldPerMinDeltaId,"
                            + "CsDiffPerMinDeltaId,"
                            + "XpDiffPerMinDeltaId,"
                            + "DamageTakenPerMinDeltaId,"
                            + "DamageTakenDiffPerMinDeltaId,"
                            + "Role,"
                            + "Lane) values (%d, %d, %d, %d, %d, %d, %d, '%s', '%s')",
                    cpmdId,
                    xpmId,
                    gpmdId,
                    csdiffId,
                    0,
                    damageTakenPerMinDeltaId,
                    damageTakenDiffPerMinDeltaId,
                    timeline.getRole(),
                    timeline.getLane()
            );
            int resultId = dbHelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred doing timeline save: " + ex.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Get a timeline by the timeline id.
     * @param timelineId 
     * @return 
     */
    public Timeline getTimeline(int timelineId) {
       try {
            String query = "SELECT * from timeline WHERE Id = " + timelineId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                Timeline timeline = new Timeline();
                timeline.setId(timelineId);
                
                // Set creeps per min deltas
                int cpmdId = resultSet.getInt("CreepsPerMinDeltaId");
                CreepsPerMinDeltas creepsPerMinDeltas = creepsPerMinDeltaControl.getCPMD(cpmdId);
                timeline.setCreepsPerMinDeltas(creepsPerMinDeltas);
                
                int csDiffPerMinId = resultSet.getInt("CsDiffPerMinDeltaId");
                CsDiffPerMinDeltas csDiffPerMinDeltas = csDiffPerMinDeltasControl.getGPMD(csDiffPerMinId);
                timeline.setCsDiffPerMinDeltas(csDiffPerMinDeltas);
                
                int xpPerMindDeltasId = resultSet.getInt("XPPerMinDeltaId");
                XpPerMinDeltas xpPerMinDeltas = xpPerMinDeltaControl.getXpPMD(xpPerMindDeltasId);
                timeline.setXpPerMinDeltas(xpPerMinDeltas);
                
                int goldPerMinDeltasId = resultSet.getInt("GoldPerMinDeltaId");
                GoldPerMinDeltas goldPerMinDeltas = goldPerMinDeltasControl.getGPMD(goldPerMinDeltasId);
                timeline.setGoldPerMinDeltas(goldPerMinDeltas);
                
                timeline.setXpDiffPerMinDeltas(null); // not implemented yet
                timeline.setDamageTakenDiffPerMinDeltas(null); // not yet implemented
                timeline.setDamageTakenPerMinDeltas(null); // not yet implemented
                timeline.setRole(resultSet.getString("Role"));
                timeline.setLane(resultSet.getString("Lane"));
                return timeline;
                
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred fetching timeline: " + ex.getMessage());
        }
       
       return null;
    }
}
