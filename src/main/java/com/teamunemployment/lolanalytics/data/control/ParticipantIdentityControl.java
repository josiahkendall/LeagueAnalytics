/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The database control for the participant Identity Control.
 * @author Josiah Kendall.
 */
public class ParticipantIdentityControl {
    
    private DBHelper dbHelper;
    
    /**
     * Default constructor
     * @param dbHelper The dbHelper required for this contorl.
     */
    public ParticipantIdentityControl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    
    /**
     * Save a participantIdentitySummary
     * @param pis The pis that we are saving
     * @return The row identifier, or -1 if it fails.
     */
    public int SaveParticipantIdentity(ParticipantIdentitySummary pis) {
        try {
            String queryString = String.format(
                    "Insert into participantidentitysummary ("
                            + "participantid,"
                            + "playerparticipantid,"
                            + "summonerid,"
                            + "matchid) values (%d, %d, %d, %d)",
                    pis.participantId,
                    0,
                    pis.player.summonerId,
                    pis.matchId
            );
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    /**
     * Get a specific participant identity record by id. this wont actually work yet :P
     * @param id
     * @return 
     */
    public ParticipantIdentitySummary GetParticipantIdentity(int id) {
        try {
            String query = "SELECT * from participantidentitysummary WHERE Id = " + id;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                ParticipantIdentitySummary pis = new ParticipantIdentitySummary();
                pis.matchId = resultSet.getLong("MatchId");
                pis.participantId = resultSet.getInt("ParticipantId");
                pis.summonerId = resultSet.getLong("SummonerId");
                return pis;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * Get all the participant identities for a summoner. This will return an array
     * of all the participant instances for a summoner for every match that they have competed in.
     * @param summonerId
     * @return an array list of particpant records.
     */
    public ArrayList<ParticipantIdentitySummary> GetAllParticipantIdentitiesForChampion(long summonerId) {
        
        ArrayList<ParticipantIdentitySummary> participantIdentitySummaries = new ArrayList<>();
        
        try {
            String query = "SELECT * from participantidentitysummary WHERE SummonerId = " + summonerId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            while (resultSet.next()) {
                ParticipantIdentitySummary participantIdentitySummary = new ParticipantIdentitySummary();
                participantIdentitySummary.matchId = resultSet.getLong("MatchId");
                participantIdentitySummary.participantId = resultSet.getInt("ParticipantId");
                participantIdentitySummary.summonerId = summonerId;
                
                participantIdentitySummaries.add(participantIdentitySummary);
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        
        return participantIdentitySummaries;
    }
    
}
