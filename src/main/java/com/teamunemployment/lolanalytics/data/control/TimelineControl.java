/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.stats.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall
 */
public class TimelineControl {
    
    private DeltaControl deltaControl;
    private DBHelper dbHelper;
    
    public TimelineControl(DBHelper dbHelper, DeltaControl deltaControl) {
        this.deltaControl = deltaControl;
        this.dbHelper = dbHelper;
    }
    
    public int SaveTimeline(Timeline timeline) {
        int cpmdId = deltaControl.SaveCreepsPerMinDeltas(timeline.getCreepsPerMinDeltas());
        int csdiffId = deltaControl.SaveCsDiffPerMinDeltas(timeline.getCsDiffPerMinDeltas());
        int gpmdId = deltaControl.SaveGoldPerMinDeltas(timeline.getGoldPerMinDeltas());
        int xpmId = deltaControl.SaveXpPerMinDeltas(timeline.getXpPerMinDeltas());
        int xpDiffId = deltaControl.SaveXpDiffPerMinDeltas(timeline.getXpDiffPerMinDeltas());
        int damageTakenPerMinDeltaId = deltaControl.SaveDamageTakenPerMinDeltas(timeline.getDamageTakenPerMinDeltas());
        int damageTakenDiffPerMinDeltaId = deltaControl.SaveDamageTakenDiffPerMinDeltas(timeline.getDamageTakenDiffPerMinDeltas());
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
                    xpDiffId,
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
                BaseDeltas creepsPerMinDeltas = deltaControl.GetCreepsPerMinDeltas(cpmdId);
                timeline.setCreepsPerMinDeltas(creepsPerMinDeltas);

                int csDiffPerMinId = resultSet.getInt("CsDiffPerMinDeltaId");
                BaseDeltas csDiffPerMinDeltas = deltaControl.GetCsDiffPerMinDeltas(csDiffPerMinId);
                timeline.setCsDiffPerMinDeltas(csDiffPerMinDeltas);

                int xpPerMindDeltasId = resultSet.getInt("XPPerMinDeltaId");
                BaseDeltas xpPerMinDeltas = deltaControl.GetXpPerMinDeltas(xpPerMindDeltasId);
                timeline.setXpPerMinDeltas(xpPerMinDeltas);

                int goldPerMinDeltasId = resultSet.getInt("GoldPerMinDeltaId");
                BaseDeltas goldPerMinDeltas = deltaControl.GetGoldPerMinDeltas(goldPerMinDeltasId);
                timeline.setGoldPerMinDeltas(goldPerMinDeltas);

                int xpDiffPerMinDeltasId = resultSet.getInt("XpDiffPerMinDeltaId");
                BaseDeltas xpDiffPerMinDeltas = deltaControl.GetXpDiffPerMinDeltas(xpDiffPerMinDeltasId);
                timeline.setXpDiffPerMinDeltas(xpDiffPerMinDeltas);

                int damageTakenPerMinDeltasId = resultSet.getInt("DamageTakenPerMinDeltaId");
                BaseDeltas damageTakenPerMinDeltas= deltaControl.GetDamageTakenPerMinDeltas(damageTakenPerMinDeltasId);
                timeline.setDamageTakenPerMinDeltas(damageTakenPerMinDeltas);

                int damageTakenDiffPerMinDeltaId = resultSet.getInt("DamageTakenDiffPerMinDeltaId");
                BaseDeltas damageTakenDiffPerMinDeltas = deltaControl.GetDamageTakenDiffPerMinDeltas(damageTakenDiffPerMinDeltaId);
                timeline.setDamageTakenDiffPerMinDeltas(damageTakenDiffPerMinDeltas);

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
