/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.GeneralStats;
import com.teamunemployment.lolanalytics.models.HeadToHeadStats;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchParticipantSummaryJunctionModel;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josiah Kendall
 */
public class MatchDetailsControl {
    
    private ParticipantControl participantControl;
    private ParticipantIdentityControl participantIdentityControl;
    private DBHelper dbHelper;
    private MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl;
    
    public MatchDetailsControl(ParticipantControl participantControl, ParticipantIdentityControl participantIdentityControl, 
            DBHelper dbHelper, MatchParticipantSummaryJunctionControl control ) {
        this.participantControl = participantControl;
        this.participantIdentityControl = participantIdentityControl;
        this.dbHelper = dbHelper;
        this.matchParticipantSummaryJunctionControl = control;
    }
    
    /**
     * Save a match
     * @param matchDetails The match details to save
     * @return The id of the match that we just saved.
     */
    public int SaveMatch(MatchDetailsModel matchDetails, long summonerId) {
        try {
            // save participants
            String participantCSV = saveParticipants(matchDetails.participants, matchDetails.matchId, matchDetails.participantIdentities, matchDetails.season);
            String participantIdentitiesCSV = saveParticipantIdenties(matchDetails.participantIdentities, matchDetails.matchId);
            // save participantIdentities.

            String queryString = String.format(
                    "Insert into matchdetailsmodel ("
                            + "matchId,"
                            + "region,"
                            + "platformId,"
                            + "matchmode,"
                            + "matchtype,"
                            + "matchcreation,"
                            + "matchduration,"
                            + "queuetype,"
                            + "mapid,"
                            + "season,"
                            + "matchversion,"
                            + "participantsummary,"
                            + "participantidentitysummaryidscsv)"
                            + " values (%d, '%s', '%s', '%s', '%s', %d, %d, '%s', %d, '%s', '%s', '%s', '%s')",
                    matchDetails.matchId,
                    matchDetails.region, 
                    matchDetails.platformId,
                    matchDetails.matchMode,
                    matchDetails.matchType,
                    matchDetails.matchCreation,
                    matchDetails.matchDuration,
                    matchDetails.queueType,
                    matchDetails.mapId,
                    matchDetails.season,
                    matchDetails.matchVersion,
                    1,
                    participantIdentitiesCSV
            );
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("Error occurred saviing match details: "  + ex.getMessage());
        }
        
        return -1;
    }
    
    /**
     * Load a matchDetails model by the id of said match.
     * @param matchId The match id assigned by riot. Used to identify the match.
     * @return The match model info.
     */
    public MatchDetailsModel GetMatchDetailsModel(long matchId) {
        try {
            String query = "SELECT * from matchdetailsmodel WHERE matchId = " + matchId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.first()) {
                MatchDetailsModel matchModel = new MatchDetailsModel();
                matchModel.mapId = resultSet.getInt("MapId");
                matchModel.matchCreation = resultSet.getLong("MatchCreation");
                matchModel.matchDuration = resultSet.getLong("MatchDuration");
                matchModel.matchId = matchId;
                matchModel.matchMode = resultSet.getString("MatchMode");
                matchModel.matchType = resultSet.getString("MatchType");
                matchModel.matchVersion = resultSet.getString("MatchVersion");
                matchModel.participantIdentities = loadParticipantIdentities(resultSet.getString("ParticipantIdentitySummaryIdsCSV"));
                matchModel.participants = loadParticipants(resultSet.getString("ParticipantSummariesCSV"));
                matchModel.platformId = resultSet.getString("PlatformId");
                matchModel.queueType = resultSet.getString("QueueType");
                matchModel.region = resultSet.getString("Region");
                matchModel.season = resultSet.getString("Season");
                return matchModel;
            }
            
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred loading match details model: " + ex.getMessage());
        }
        
        return null;
    }
   
    /**
     * Load participants
     * @param idCSV The particpants for this match
     * @return The particpant objects
     */
    private List<ParticipantSummary> loadParticipants(String idCSV) {
        List<ParticipantSummary> participants = new ArrayList<>();
        String [] splitIds = idCSV.split(",");
        for (String id : splitIds) {
            participants.add(participantControl.getParticipantSummary(Integer.parseInt(id)));
        }
        
        return participants;
    }
    
    /**
     * Load the participant Identities for this match.
     * @param idCSV The ids that we are looking for.
     * @return The list of participant identity summaries.
     */
    private List<ParticipantIdentitySummary> loadParticipantIdentities(String idCSV) {
        List<ParticipantIdentitySummary> identities = new ArrayList<>();
        String [] splitIds = idCSV.split(",");
        for (String id : splitIds) {
            // fetch participant id;
            identities.add(participantIdentityControl.GetParticipantIdentity(Integer.parseInt(id)));
        }
        
        return identities;
    }
    
    /**
     * Save the list of participants that are involved in a match.
     * @param participants The list of participants involved in this match
     * @return A comma seperated string of ids of the saved participant list.
     */
    private String saveParticipants(List<ParticipantSummary> participants, long matchId, List<ParticipantIdentitySummary> identities, String season) {
        int count = 0;
        String resultString = "";
        Iterator<ParticipantSummary> participantsIterator = participants.iterator();
        while (participantsIterator.hasNext()) {
            ParticipantSummary next = participantsIterator.next();
            
            // Grab identity so that we can get summoner id for this participant summary.
            ParticipantIdentitySummary identity = identities.get(count);
            count += 1;

            next.setMatchId(matchId);
            long id = participantControl.saveParticipant(next, identity.player.summonerId, season);
            MatchParticipantSummaryJunctionModel model = new MatchParticipantSummaryJunctionModel();
            model.ParticipantSummaryId = id;
            model.MatchId = matchId;
            matchParticipantSummaryJunctionControl.SaveMatchParticipantJunctionModel(model);
            if (resultString.length() == 0) {
                resultString = resultString + Long.toString(id);
            } else {
                resultString = resultString + ","+ id;
            }
        }
        
        return resultString;
    }
    
    /**
     * Save a list of participant Identities
     * @param participantIdentities The participant identities to display.
     * @return The CSV of ids.
     */
    private String saveParticipantIdenties(List<ParticipantIdentitySummary> participantIdentities, long matchId) {
        String resultString = "";
        Iterator<ParticipantIdentitySummary> participantIdentitiesIterator = participantIdentities.iterator();
        while (participantIdentitiesIterator.hasNext()) {
            ParticipantIdentitySummary next = participantIdentitiesIterator.next();
            next.matchId = matchId;
            next.summonerId = next.player.summonerId; // this is lazy.
            int id = participantIdentityControl.SaveParticipantIdentity(next);
            if (resultString.length() == 0) {
                resultString = resultString + Integer.toString(id);
            } else {
                resultString = resultString + ","+ id;
            }
        }
        
        return resultString;
    }
    
    /**
     * Get the most recent 20 matches for a summoner matching the specific role.
     * @param summonerId 
     * @param role e.g JUNGLE
     * @return 
     */
    public ArrayList<Long> GetTwentyRecentMatchIdsForSpecifiedRole(long summonerId, String role) {
        ArrayList<Long> matchIds = new ArrayList<>();
        String queryString = String.format(
                    "select * from matchdetailsmodel \n" +
                    "join matchmarticipantsummaryjunction \n" +
                    "on matchdetailsmodel.MatchId = matchmarticipantsummaryjunction.MatchId\n" +
                    "join participantsummary \n" +
                    "on matchmarticipantsummaryjunction.ParticipantSummaryId = participantsummary.Id\n" +
                    "join timeline \n" +
                    "on ParticipantSummary.TimelineId = timeline.Id\n" +
                    "join goldpermindeltas\n" +
                    "on GoldPerMinDeltaId = goldpermindeltas.Id\n" +
                    "where summonerId = %d and Lane = '%s'\n" +
                    "order by MatchCreation DESC\n" +
                    "Limit 20;",
                    summonerId,
                    role   
            );
        
        try {
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            while (result.next()) {
                Long matchId = result.getLong("MatchId");
                matchIds.add(matchId);
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred Getting recent 20 matches for a summoner with specified role: " + role);
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            System.out.println("An error occurred Getting recent 20 matches for a summoner with specified role: " + role);
            ex.printStackTrace();
        }
        
        return matchIds;
    }
    /**
     * Fetch the stats for a summoner in a game, as well as the stats for their opponent.
     * @param matchId
     * @param role
     * @return The head to head stats.
     */
    public HeadToHeadStats fetchHeadToHeadStatsForMatch(long matchId, String role, long summonerId) {
        HeadToHeadStats headToHead = new HeadToHeadStats();
        String queryString = String.format(
                "select goldpermindeltas.ZeroToTen as 'gpm10', goldpermindeltas.TenToTwenty as 'gpm20', summonerId, stats.Kills, stats.Deaths, creepspermindeltas.TenToTwenty, creepspermindeltas.ZeroToTen, MinionsKilled from matchdetailsmodel \n" +
                "join matchmarticipantsummaryjunction \n" +
                "on matchdetailsmodel.MatchId = matchmarticipantsummaryjunction.MatchId\n" +
                "join participantsummary \n" +
                "on matchmarticipantsummaryjunction.ParticipantSummaryId = participantsummary.Id\n" +
                "join timeline \n" +
                "on ParticipantSummary.TimelineId = timeline.Id\n" +
                "join stats\n" +
                "on stats.Id = StatId\n" +
                "join creepspermindeltas\n" +
                "on creepspermindeltas.Id = CreepsPerMinDeltaId\n" +
                "join goldpermindeltas\n" +
                "on GoldPerMinDeltaId = goldpermindeltas.Id\n" +
                "where matchdetailsmodel.MatchId = %d and Lane = '%s'" +
                "Limit 20;",
                matchId,
                role   
            );
        
        try {
            ResultSet result = dbHelper.ExecuteSqlQuery(queryString);
            while (result.next()) {
                long tempSummonerId = result.getLong("SummonerId");
                
                // If true, we are looking at us. If not, our opponent.
                if (tempSummonerId == summonerId) {
                    headToHead.csTenMe = (int) (result.getDouble("ZeroToTen")*10);
                    headToHead.csTwentyMe = (int) (result.getDouble("TenToTwenty")*10);
                    headToHead.csTotalMe = result.getInt("MinionsKilled");
                    headToHead.killsMe = result.getInt("Kills");
                    headToHead.deathsMe = result.getInt("Deaths");
                    headToHead.goldFirst10 = result.getInt("gpm10") * 10;
                    headToHead.goldSecond10 = result.getInt("gpm20") * 10;
                } else {
                    headToHead.csTenThem = (int) (result.getDouble("ZeroToTen")*10);
                    headToHead.csTwentyThem = (int) (result.getDouble("TenToTwenty")*10);
                    headToHead.csTotalThem = result.getInt("MinionsKilled");
                    headToHead.killsThem = result.getInt("Kills");
                    headToHead.deathsThem = result.getInt("Deaths");
                    headToHead.goldFirst10Them = result.getInt("gpm10") * 10;
                    headToHead.goldSecond10Them = result.getInt("gpm20") * 10;
                }
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred Getting recent 20 matches for a summoner with specified role: " + role);
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            System.out.println("An error occurred Getting recent 20 matches for a summoner with specified role: " + role);
            ex.printStackTrace();
        }
        
        return headToHead;
    }
}
