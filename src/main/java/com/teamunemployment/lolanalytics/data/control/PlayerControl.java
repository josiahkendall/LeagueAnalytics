/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.Player;
import com.teamunemployment.lolanalytics.models.PlayerParticipant;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Josiah Kendall
 */
public class PlayerControl {
    
    private DBHelper dbhelper;
    
    /**
     * @param dbHelper
     */
    public PlayerControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param player the player to save
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int SavePlayer(PlayerParticipant player) {

        try {
            String queryString = String.format(
                    "Insert into playerparticipant ("
                            + "summonerid,"
                            + "summonername,"
                            + "matchhistoryuri,"
                            + "profileiconid)"
                            + " values (%d, '%s', '%s', %d)",
                   player.summonerId,
                   player.summonerName,
                   player.matchHistoryUri,
                   player.profileIcon
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
    
    
    /**
     * Fetch a participant by the given id.
     * @param summonerId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public PlayerParticipant getPlayer(int playerId) {
        try {
            String query = "SELECT * from playerparticpant WHERE Id = " + playerId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                PlayerParticipant playerParticipant = new PlayerParticipant();
                playerParticipant.matchHistoryUri = resultSet.getString("MatchHistoryUri");
                playerParticipant.profileIcon = resultSet.getInt("ProfileIconId");
                playerParticipant.summonerId  = resultSet.getLong("SummonerId");
                playerParticipant.summonerName = resultSet.getString("SummonerName");
                
                return playerParticipant;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        
        return null;
    }
}
