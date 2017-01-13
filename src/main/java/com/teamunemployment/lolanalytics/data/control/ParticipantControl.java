/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.stats.Rune;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Josiah Kendall
 */
public class ParticipantControl {
    
    private DBHelper dbhelper;
    private RuneControl runeControl;
    private TimelineControl timelineControl;
    private StatControl statControl;
    private MasteriesControl masteriesControl;
    
    /**
     * 
     * @param dbHelper
     * @param runeControl
     * @param timelineControl
     * @param statControl
     * @param masteriesControl 
     */
    public ParticipantControl(DBHelper dbHelper, RuneControl runeControl, TimelineControl timelineControl,
            StatControl statControl, MasteriesControl masteriesControl) {
        this.dbhelper = dbHelper;
        this.runeControl = runeControl;
        this.masteriesControl = masteriesControl;
        this.timelineControl = timelineControl;
        this.statControl = statControl;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveParticipant(ParticipantSummary participantSummary, long summonerId, String season) {
        
        int timelineId = timelineControl.SaveTimeline(participantSummary.getTimeline());
        int statsId = statControl.saveStats(participantSummary.getStats());
        String runesCsv = "test";
        try {
            String queryString = String.format(
                    "Insert into participantsummary ("
                            + "TeamId,"
                            + "Spell1Id,"
                            + "Spell2Id,"
                            + "ChampionId,"
                            + "HighestAchievedSeasonTier,"
                            + "TimelineId,"
                            + "StatId,"
                            + "ParticipantId,"
                            + "SummonerId,"
                            + "Season)"
                            + " values (%d, %d, %d, %d, '%s', '%s', %d, %d, %d, '%s')",
                    participantSummary.getTeamId(),
                    participantSummary.getSpell1Id(),
                    participantSummary.getSpell2Id(),
                    participantSummary.getChampionId(),
                    participantSummary.getHighestAchievedSeasonTier(),
                    timelineId,
                    statsId,
                    participantSummary.getParticipantId(),
                    summonerId,
                    season
            );
            int result = dbhelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            System.out.println("An error occurred saving participant: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        
        return -1;
    }
    
//    private String buildRunesCSV(ArrayList<Rune> runes) {
//        String resultStringCSV = "";
//        Iterator<Rune> runesIterator = runes.iterator();
//        while (runesIterator.hasNext()) {
//            Rune rune = runesIterator.next();
//            RuneControl
//        }
//    }
    
    /**
     * Fetch a participant by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public ParticipantSummary getParticipantSummary(int participantId) {
        try {
            String query = "SELECT * from participantsummary WHERE Id = " + participantId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                ParticipantSummary participantSummary = new ParticipantSummary();
                participantSummary.setId(participantId);
                participantSummary.setChampionId(resultSet.getInt("ChampionId"));
                participantSummary.setHighestAchievedSeasonTier(resultSet.getString("HighestAchievedSeasonTier"));
                participantSummary.setSpell1Id(resultSet.getInt("Spell1Id"));
                participantSummary.setSpell2Id(resultSet.getInt("Spell2Id"));
                participantSummary.setSummonerId(resultSet.getLong("SummonerId"));
                int statId = resultSet.getInt("StatId");
                // Get stats
                Stats stats = statControl.getStats(statId);
                participantSummary.setStats(stats);

                participantSummary.setTeamId(resultSet.getInt("TeamId"));
                
                int timelineId = resultSet.getInt("TimelineId");
                Timeline timeline = timelineControl.getTimeline(timelineId);
                participantSummary.setTimeline(timeline);
                
                participantSummary.setParticipantId(participantId);
                return participantSummary;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        
        return null;
    }
    
     /**
     * Update a CPMD object that already exists.
     * @param cpmd the CreepsPerMinDelta object to update.
     * @return The result integer.
     */
//    public int updateCPMD(CreepsPerMinDeltas cpmd) {
//        
//        String queryString = String.format(
//                    "UPDATE creepspermindeltas set "
//                            + "ZeroToTen = %d, "
//                            + "TenToTwenty = %d, "
//                            + "TwentyToThirty = %d "
//                            + "WHERE Id = %d",
//                    cpmd.getZeroToTen(),
//                    cpmd.getTenToTwenty(),
//                    cpmd.getTwentyToThirty(),
//                    cpmd.getThirtyToEnd(),
//                    cpmd.getId()
//            );
//         try {
//            int result = dbhelper.ExecuteSqlScript(queryString);
//            return result;
//        } catch (SQLException | IllegalStateException ex) {
//            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return 0;
//    }
}
