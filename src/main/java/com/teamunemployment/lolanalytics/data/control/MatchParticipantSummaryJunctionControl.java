/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchParticipantSummaryJunctionModel;
import com.teamunemployment.lolanalytics.models.PlayerParticipant;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Josiah Kendall.
 */
public class MatchParticipantSummaryJunctionControl {
    
    private DBHelper dbHelper;
    /**
     * @param dbHelper
     */
    public MatchParticipantSummaryJunctionControl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int SaveMatchParticipantJunctionModel(MatchParticipantSummaryJunctionModel model) {

        try {
            String queryString = String.format(
                    "Insert into MatchParticipantSummaryJunction ("
                            + "MatchId,"
                            + "ParticipantSummaryId)"
                            + " values (%d, %d)",
                   model.MatchId,
                   model.ParticipantSummaryId
            );
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException ex) {
            System.out.println("An error occurred saving participant: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

//    public MatchParticipantSummaryJunctionModel GetMatchParticipantSummary(match) {
//        try {
//            String query = "SELECT * from MatchParticipantSummaryJunction WHERE id = " + matchId;
//            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
//            if (resultSet.first()) {
//                MatchDetailsModel matchModel = new MatchDetailsModel();
//                matchModel.mapId = resultSet.getInt("MapId");
//                matchModel.matchCreation = resultSet.getLong("MatchCreation");
//                matchModel.matchDuration = resultSet.getLong("MatchDuration");
//                matchModel.matchId = matchId;
//                matchModel.matchMode = resultSet.getString("MatchMode");
//                matchModel.matchType = resultSet.getString("MatchType");
//                matchModel.matchVersion = resultSet.getString("MatchVersion");
//                matchModel.participantIdentities = loadParticipantIdentities(resultSet.getString("ParticipantIdentitySummaryIdsCSV"));
//                matchModel.participants = loadParticipants(resultSet.getString("ParticipantSummariesCSV"));
//                matchModel.platformId = resultSet.getString("PlatformId");
//                matchModel.queueType = resultSet.getString("QueueType");
//                matchModel.region = resultSet.getString("Region");
//                matchModel.season = resultSet.getString("Season");
//                return matchModel;
//            }
//            return result;
//        } catch (SQLException ex) {
//            System.out.println("An error occurred saving participant: " + ex.getMessage());
//        } catch (IllegalStateException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}
