package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.BaseDeltas;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Josiah Kendall
 */
public class BaseDeltaControl {

    private DBHelper dbHelper;

    public BaseDeltaControl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * Save a {@link BaseDeltas} object to a specific table
     * @param deltas The object to save.
     * @param tableName The table to save to.
     * @return The database row Id of the saved table.
     */
    public int saveDeltas(BaseDeltas deltas, String tableName) {
        try {
            String queryString = String.format(
                    "Insert into %s ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    tableName,
                    deltas.getZeroToTen(),
                    deltas.getTenToTwenty(),
                    deltas.getTwentyToThirty(),
                    deltas.getThirtyToEnd()
            );
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new IllegalStateException("Failed to save Deltas. Check logs for more stacktrace");
    }

    /**
     * Fetch a baseDelta stat from a specified table by Id.
     * @param tableName THe name of the table to fetch the data from. T
     * @param id The id of the delta that we are searching for.
     * @return A {@link BaseDeltas}object with the stats found in the DB.
     */
    public BaseDeltas getDeltas(String tableName,int id) {
        try {
            String query = "SELECT * from "+tableName+" WHERE Id = " + id;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                BaseDeltas delta = new BaseDeltas();
                delta.setId(id);
                delta.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                delta.setTenToTwenty(resultSet.getDouble("TenToTwenty"));
                delta.setTwentyToThirty(resultSet.getDouble("TwentyToThirty"));
                delta.setThirtyToEnd(resultSet.getDouble("ThirtyToEnd"));

                return delta;
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new IllegalStateException("Database error: An error occurred trying to retrieve data. Check logs for stacktrace");
    }

    public double LoadSummonerScore(long summonerId, String season, String role, String lane, int gameStage,
                                    String timelineJoinColumn, int numberOfResults, String deltaType) {
        String queryString = "select avg(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.zeroToTen)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Fetch the difference (in creeps per minutes) between the selected summoner and the enemy. If the result is negative,
     * the enemy scored better.
     * @param summonerId The summonerId for the summoner whom we are getting information for.
     * @param season The season to filter by.
     * @param role The role to filter by.
     * @param lane The lane to filter by.
     * @param gameStage The game stage to filter by (Early game, mid game, late game)
     * @param timelineJoinColumn The column to join the deltas type (e.g CreepsPerMin etc) that we want to join to the timeline and particpant tables.
     * @param numberOfResults The number of results we want to influence the average. For example, if we want to find the
     *                        average for the last 20 games, we would set this parameter to 20.
     * @param deltaType The column that we want the deltas from. e.g CreepsPerMinDeltas.
     * @return The difference between the summoners. A positive number represents our summoner gets more of a particular stat,
     *  while a negative number means less. The number is stat per minute.
     */
    public double LoadDeltaAverage(long summonerId, String season, String role, String lane, int gameStage, String timelineJoinColumn, int numberOfResults, String deltaType) {

        String gameStageString = "zeroToTen";
        switch(gameStage) {
            case 1:
                gameStageString = "tenToTwenty";
                break;
            case 2:
                gameStageString = "twentyToThirty";
        }
        String queryString = String.format(
                "SELECT avg(%s.%s)" +
                        "from participantSummary " +
                        "join timeline on timeline.id = participantsummary.timelineId " +
                        "join %s on %s.id = timeline.%s " +
                        "where participantsummary.summonerId = %d and " +
                        "role = '%s' and lane = '%s' and season = '%s' " +
                        "order by %s.id desc\n" +
                        "         limit 20",
                deltaType,
                gameStageString,
                deltaType,
                deltaType,
                timelineJoinColumn,
                summonerId,
                role,
                lane,
                season,
                deltaType);

        try {
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(queryString);
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
