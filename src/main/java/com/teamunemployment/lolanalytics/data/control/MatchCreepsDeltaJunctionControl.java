package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchCreepsDeltaJunctionModel;
import com.teamunemployment.lolanalytics.models.stats.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class MatchCreepsDeltaJunctionControl {

    private DBHelper dbHelper;

    public MatchCreepsDeltaJunctionControl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     *  Save a matchCreepsDeltaJunctionModel object.
     * @param matchCreepsDeltaJunctionModel The object to save
     * @return The id of the saved object, or -1 if the save failed.
     */
    public int SaveMatchCreepsDeltaJunctionControl(MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel) {

        try {
            String queryString = String.format(
                    "Insert into matchCreepScoreDeltaJunction ("
                            + "MatchId,"
                            + "SummonerId,"
                            + "CreepScoreDeltaId) values (%d, %d, %d)",
                    matchCreepsDeltaJunctionModel.getMatchId(),
                    matchCreepsDeltaJunctionModel.getSummonerId(),
                    matchCreepsDeltaJunctionModel.getCreepScoreDeltaId()
            );
            int resultId = dbHelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred doing matchCreepsDeltaJunctionModel save: " + ex.getMessage());
        }

        return -1;
    }

    public MatchCreepsDeltaJunctionModel GetMatchCreepsDeltaJunctionModel(int id) {
        try {
            String query = "SELECT * from matchCreepScoreDeltaJunction WHERE Id = " + id;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel = new MatchCreepsDeltaJunctionModel();
                matchCreepsDeltaJunctionModel.setId(id);

                long matchId = resultSet.getLong("MatchId");

                long summonerId = resultSet.getLong("SummonerId");
                int creepScoreDeltaId = resultSet.getInt("CreepScoreDeltaId");
                matchCreepsDeltaJunctionModel.setMatchId(matchId);
                matchCreepsDeltaJunctionModel.setSummonerId(summonerId);
                matchCreepsDeltaJunctionModel.setCreepScoreDeltaId(creepScoreDeltaId);
                return matchCreepsDeltaJunctionModel;

            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred fetching matchCreepsDeltaJunctionModel: " + ex.getMessage());
        }

        return null;
    }

    /**
     * Get all the matchCreepsDeltaJunction objects for the
     * @param matchId
     * @return
     */
    public ArrayList<MatchCreepsDeltaJunctionModel> getAllMatchCreepsDeltaJunctionModelsForAMatch(long matchId) {
        try {
            String query = "SELECT * from matchCreepScoreDeltaJunction WHERE MatchId = " + matchId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            ArrayList<MatchCreepsDeltaJunctionModel> matchCreepsDeltaJunctionModels = new ArrayList<>();
            while (resultSet.next()) {
                MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel = new MatchCreepsDeltaJunctionModel();
                matchCreepsDeltaJunctionModel.setId(resultSet.getInt("Id"));
                long summonerId = resultSet.getLong("SummonerId");
                int creepScoreDeltaId = resultSet.getInt("CreepScoreDeltaId");
                matchCreepsDeltaJunctionModel.setMatchId(matchId);
                matchCreepsDeltaJunctionModel.setSummonerId(summonerId);
                matchCreepsDeltaJunctionModel.setCreepScoreDeltaId(creepScoreDeltaId);
                matchCreepsDeltaJunctionModels.add(matchCreepsDeltaJunctionModel);
            }
            return matchCreepsDeltaJunctionModels;
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred fetching matchCreepsDeltaJunctionModel: " + ex.getMessage());
        }

        return new ArrayList<>();


    }
}
