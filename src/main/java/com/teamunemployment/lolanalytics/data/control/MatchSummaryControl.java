/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.Role;
import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall
 */
public class MatchSummaryControl {
    
    public DBHelper dbhelper;
    
    public MatchSummaryControl(DBHelper dbhelper) {
        this.dbhelper = dbhelper;
    }
    
    /**
     * Save a match summary.
     * @param matchSummary The match summary to save.
     * @param summonerId The summoner involved in the match.
     * @return 
     */
    public int saveMatchSummary(MatchSummary matchSummary, long summonerId) {
        try {
            String queryString = String.format(
                    "Insert into matchsummary values ('%s', '%s', %d, %d, '%s', '%s', %d, '%s', '%s', %d)",
                    matchSummary.region,
                    matchSummary.platformId,
                    matchSummary.matchId,
                    matchSummary.champion,
                    matchSummary.queue,
                    matchSummary.season,
                    matchSummary.timestamp,
                    matchSummary.lane,
                    matchSummary.role,
                    summonerId
            );
            int result = dbhelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Get match summary
     * @param matchSummaryId The id to get
     * @return The match summary, or null if it was not found.
     */
    public MatchSummary getMatchSummary(long matchSummaryId) {
           try {
            String query = "SELECT * from matchsummary WHERE MatchId = " + matchSummaryId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                MatchSummary matchSummary = new MatchSummary();
                matchSummary.region = resultSet.getString("Region");
                matchSummary.platformId = resultSet.getString("PlatformId");
                matchSummary.matchId = resultSet.getLong("MatchId");
                matchSummary.champion = resultSet.getInt("Champion");
                matchSummary.queue = resultSet.getString("Queue");
                matchSummary.season = resultSet.getString("Season");
                matchSummary.timestamp = resultSet.getLong("GameTimeStamp");
                matchSummary.lane = resultSet.getString("Lane");
                matchSummary.role = resultSet.getString("Role");
                return matchSummary;
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Update a match summary
     * @param matchSummary
     * @return The identifier of the updated status
     */
    public int updateMatchSummary(MatchSummary matchSummary) {
        String queryString = String.format(
                        "UPDATE matchsummary set "
                                + "Region = %s, "
                                + "PlatformId = %s, "
                                + "MatchId = %d "
                                + "Champion = %d "
                                + "Queue = %s "
                                + "Season = %s "
                                + "GameTimeStamp = %d "
                                + "Lane = %s "
                                + "Role = %s "
                                + "WHERE Id = %d",
                        matchSummary.region,
                        matchSummary.platformId,
                        matchSummary.matchId,
                        matchSummary.champion,
                        matchSummary.queue,
                        matchSummary.season,
                        matchSummary.timestamp,
                        matchSummary.lane,
                        matchSummary.role
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
     * Determine whether or not a match summary exists.
     * @param matchId The matchId of the requested summary.
     * @return boolean indicator of whether or not this exists
     */
    public boolean hasMatchSummary(long matchId) {
        return getMatchSummary(matchId) != null;
    }
    
    /**
     * Get an arraylist of all the match summaries that we have for a summoner.
     * @param summonerId the summoner id in question.
     * @return An {@link ArrayList} of {@link MatchSummary} objects for this summoner.
     */
    public ArrayList<MatchSummary> GetAllMatchSummariesForSummoner(long summonerId) {
        ArrayList<MatchSummary> matchSummaries = new ArrayList<>();
        try {
            String query = "SELECT * from matchsummary WHERE SummonerId = " + summonerId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            while (resultSet.next()) {
                MatchSummary matchSummary = new MatchSummary();
                matchSummary.region = resultSet.getString("Region");
                matchSummary.platformId = resultSet.getString("PlatformId");
                matchSummary.matchId = resultSet.getLong("MatchId");
                matchSummary.champion = resultSet.getInt("Champion");
                matchSummary.queue = resultSet.getString("Queue");
                matchSummary.season = resultSet.getString("Season");
                matchSummary.timestamp = resultSet.getLong("GameTimeStamp");
                matchSummary.lane = resultSet.getString("Lane");
                matchSummary.role = resultSet.getString("Role");
                matchSummaries.add(matchSummary);
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchSummaries;
    } 
    
    /**
     * Get an {@link ArrayList} of match summaries that we have stored for a summoner
     * at a specific {@link Role}.
     * @param summonerId
     * @param role {@link Role}
     * @return An {@link ArrayList} of {@link MatchSummary} that match the request.
     */
    public ArrayList<MatchSummary> GetAllMatchSummariesForSummonerMatchingSpecificRole(long summonerId, String role) {
        ArrayList<MatchSummary> matchSummaries = new ArrayList<>();
        try {
            String query = "SELECT * from matchsummary WHERE SummonerId = " + summonerId + " AND Lane = '" + role + "'";
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            while (resultSet.next()) {
                MatchSummary matchSummary = new MatchSummary();
                matchSummary.region = resultSet.getString("Region");
                matchSummary.platformId = resultSet.getString("PlatformId");
                matchSummary.matchId = resultSet.getLong("MatchId");
                matchSummary.champion = resultSet.getInt("Champion");
                matchSummary.queue = resultSet.getString("Queue");
                matchSummary.season = resultSet.getString("Season");
                matchSummary.timestamp = resultSet.getLong("GameTimeStamp");
                matchSummary.lane = resultSet.getString("Lane");
                matchSummary.role = resultSet.getString("Role");
                matchSummaries.add(matchSummary);
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred getting match summaries for a role. " + ex.getMessage());
        }
        return matchSummaries;
    }
}
