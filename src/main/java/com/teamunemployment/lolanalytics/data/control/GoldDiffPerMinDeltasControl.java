package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.BaseDeltas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Josiah Kendall
 */
public class GoldDiffPerMinDeltasControl {
    private DBHelper dbhelper;
    public GoldDiffPerMinDeltasControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param goldDiffPerMin the goldPerMinDelta stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveGPMD(BaseDeltas goldDiffPerMin) {
        try {
            String queryString = String.format(
                    "Insert into goldDiffPerMinDeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    goldDiffPerMin.getZeroToTen(),
                    goldDiffPerMin.getTenToTwenty(),
                    goldDiffPerMin.getTwentyToThirty(),
                    goldDiffPerMin.getThirtyToEnd()
            );
            int resultId = dbhelper.ExecuteSqlScript(queryString);
            return resultId;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            System.out.println("An Error occurred saving gold per min detlas. Exception follows : \n" + ex.getMessage());
        }

        return 0;
    }

    /**
     * Fetch a summoner by the given id.
     * @param id The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null
     * if summoner was not found.
     */
    public BaseDeltas getGPMD(int id) {
        try {
            String query = "SELECT * from goldDiffPerMinDeltas WHERE Id = " + id;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                BaseDeltas gpmd = new BaseDeltas();
                gpmd.setId(id);
                gpmd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                gpmd.setTenToTwenty(resultSet.getDouble("TenToTwenty"));
                gpmd.setTwentyToThirty(resultSet.getDouble("TwentyToThirty"));
                gpmd.setThirtyToEnd(resultSet.getDouble("ThirtyToEnd"));

                return gpmd;
            }
        } catch (SQLException | IllegalStateException ex) {
            // does this work?
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Load the average gold difference between the summoner and the enemy during a certain game stage
     * @param summonerId The summoner for whom we are going to compare with their
     * @param season the season to filter to
     * @param role The role to filter to
     * @param lane the lane to filter to
     * @param gameStage the game stage to get the stats for (Early,Middle,Late)
     * @return
     */
    public double LoadAverageGoldDifferenceBetweenEnemyAndSummoner(long summonerId, String season, String role, String lane, int gameStage) {

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
