/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class CreepsPerMinDeltaControl {
    
    private DBHelper dbhelper;
    
    public CreepsPerMinDeltaControl(DBHelper dbHelper) {
        this.dbhelper = dbHelper;
    }

    /**
     * Save CPMD
     * @param creepsPerMinDeltas the CPMD stats for a match
     * @return The save identifier (-1 for failure, 0 for nothing, id for success)
     */
    public int saveCPMD(CreepsPerMinDeltas creepsPerMinDeltas) {
        try {
            String queryString = String.format(
                    "Insert into creepspermindeltas ("
                            + "ZeroToTen,"
                            + "TenToTwenty,"
                            + "TwentyToThirty,"
                            + "ThirtyToEnd) values (%s, %s, %s, %s)",
                    creepsPerMinDeltas.getZeroToTen(),
                    creepsPerMinDeltas.getTenToTwenty(),
                    creepsPerMinDeltas.getTwentyToThirty(),
                   creepsPerMinDeltas.getThirtyToEnd()
          );
            int result = dbhelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException | NullPointerException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return 0;
    }
    
    /**
     * Fetch a summoner by the given id.
     * @param cpmdId The id of the required summoner.
     * @return A summoner object representing the requested summoner, or null 
     * if summoner was not found.
     */
    public CreepsPerMinDeltas getCPMD(int cpmdId) {
        try {
            String query = "SELECT * from creepspermindeltas WHERE Id = " + cpmdId;
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
            if (resultSet == null) {
                return null;
            }
            if (resultSet.next()) {
                CreepsPerMinDeltas cmpd = new CreepsPerMinDeltas();
                cmpd.setId(cpmdId);
                cmpd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
                cmpd.setTenToTwenty(resultSet.getDouble("TenToTwenty"));
                cmpd.setTwentyToThirty(resultSet.getDouble("TwentyToThirty"));
                cmpd.setThirtyToEnd(resultSet.getDouble("ThirtyToEnd"));
                
                return cmpd;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occurred when getting creeps per min deltas: " + ex.getMessage());
        }
        
        return null;
    }
    
     /**
     * Update a CPMD object that already exists.
     * @param cpmd the CreepsPerMinDelta object to update.
     * @return The result integer.
     */
    public int updateCPMD(CreepsPerMinDeltas cpmd) {
        
        String queryString = String.format(
                    "UPDATE creepspermindeltas set "
                            + "ZeroToTen = %d, "
                            + "TenToTwenty = %d, "
                            + "TwentyToThirty = %d "
                            + "WHERE Id = %d",
                    cpmd.getZeroToTen(),
                    cpmd.getTenToTwenty(),
                    cpmd.getTwentyToThirty(),
                    cpmd.getThirtyToEnd(),
                    cpmd.getId()
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
     * Get all the creepsPerMinDeltas for a season, for a specific summoner.
     * @param summonerId The summoner to get the creeps for.
     * @param season The season we are interested in.
     * @return The
     */
    public ArrayList<CreepsPerMinDeltas> GetAllEarlyGameCreepsPerMinForASeason(long summonerId, String season, String lane, String role) {


        return new ArrayList<>();
    }

    /**
     * Get the most recent 20 creepScoreDelta objects from the database matching the users given parameters
     * @param summonerId The summonerId of the summoner we are searching for.
     * @param lane the lane to filter the results by.
     * @param role The role to filter the results by.
     * @return A list (with a max size of 20, but can be 0) that contains the CreepScoreDeltas
     */
    public ArrayList<CreepsPerMinDeltas> GetMostRecent20EarlyGameCreepScoresForASummoner(long summonerId, String lane, String role) {

        return new ArrayList<>();
    }


    public ArrayList<CreepsPerMinDeltas> GetMostRecent20EarlyGameCreepScoresForASummoner(long summonerId, int championId, String lane, String role) {
        return new ArrayList<>();
    }

    public double GetEarlyGameMaxCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select max(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("max(creepspermindeltas.zeroToTen)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double GetEarlyGameMinCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select min(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("min(creepspermindeltas.zeroToTen)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for their last 20 games in that role.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetRecentEarlyGameAverageForRecent20Matches(long summonerId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
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
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetEarlyGameSeasonAverage(long summonerId, String season, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
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
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param season The season to filter to.
     * @param champId The champ we want to filter to.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetEarlyGameSeasonAverage(long summonerId, String season, int champId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.zeroToTen) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "and participantsummary.championId = "+champId + " " +
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
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

    /**============================================
     * MID GAME BELOW HERE
     *      |
     *      |
     *      V
     ================================================
     */

    /**
     * Get all the creepsPerMinDeltas for a season, for a specific summoner.
     * @param summonerId The summoner to get the creeps for.
     * @param season The season we are interested in.
     * @return The
     */
    public ArrayList<CreepsPerMinDeltas> GetAllMidGameCreepsPerMinForASeason(long summonerId, String season, String lane, String role) {


        return new ArrayList<>();
    }

    /**
     * Get the most recent 20 creepScoreDelta objects from the database matching the users given parameters
     * @param summonerId The summonerId of the summoner we are searching for.
     * @param lane the lane to filter the results by.
     * @param role The role to filter the results by.
     * @return A list (with a max size of 20, but can be 0) that contains the CreepScoreDeltas
     */
    public ArrayList<CreepsPerMinDeltas> GetMostRecent20MidGameCreepScoresForASummoner(long summonerId, String lane, String role) {

        return new ArrayList<>();
    }


    public ArrayList<CreepsPerMinDeltas> GetMostRecent20MidGameCreepScoresForASummoner(long summonerId, int championId, String lane, String role) {
        return new ArrayList<>();
    }

    public double GetMidGameMaxCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select max(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("max(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double GetMidGameMinCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select min(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("min(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for their last 20 games in that role.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetMidGameAverageForRecent20Matches(long summonerId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetMidGameSeasonAverage(long summonerId, String season, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param season The season to filter to.
     * @param champId The champ we want to filter to.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetMidGameSeasonAverage(long summonerId, String season, int champId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "and participantsummary.championId = "+champId + " " +
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for their last 20 games in that role.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetRecentMidGameAverageForRecent20Matches(long summonerId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.tenToTwenty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.tenToTwenty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    /**============================================
     * LATE GAME BELOW HERE
     *      |
     *      |
     *      V
     ================================================
     */

    /**
     * Get all the creepsPerMinDeltas for a season, for a specific summoner.
     * @param summonerId The summoner to get the creeps for.
     * @param season The season we are interested in.
     * @return The
     */
    public ArrayList<CreepsPerMinDeltas> GetAllLateGameCreepsPerMinForASeason(long summonerId, String season, String lane, String role) {


        return new ArrayList<>();
    }

    /**
     * Get the most recent 20 creepScoreDelta objects from the database matching the users given parameters
     * @param summonerId The summonerId of the summoner we are searching for.
     * @param lane the lane to filter the results by.
     * @param role The role to filter the results by.
     * @return A list (with a max size of 20, but can be 0) that contains the CreepScoreDeltas
     */
    public ArrayList<CreepsPerMinDeltas> GetMostRecent20LateGameCreepScoresForASummoner(long summonerId, String lane, String role) {

        return new ArrayList<>();
    }


    public ArrayList<CreepsPerMinDeltas> GetMostRecent20LateGameCreepScoresForASummoner(long summonerId, int championId, String lane, String role) {
        return new ArrayList<>();
    }

    public double GetLateGameMaxCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select max(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("max(creepspermindeltas.twentyToThirty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double GetLateGameMinCreepsForTheSeason(long summonerId, String season, String lane, String role)  {
        String queryString = "select min(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "'";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("min(creepspermindeltas.twentyToThirty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for their last 20 games in that role.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetLateGameAverageForRecent20Matches(long summonerId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.twentyToThirty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetLateGameSeasonAverage(long summonerId, String season, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.twentyToThirty)");
                return average;
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for the entire season.
     * @param summonerId The Summoner we want the average for.
     * @param season The season to filter to.
     * @param champId The champ we want to filter to.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetLateGameSeasonAverage(long summonerId, String season, int champId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "participantsummary.season = '"+season+"' and "+
                "role = '"+role+"' and lane = '"+lane + "' "+
                "and participantsummary.championId = "+champId + " " +
                "order by creepspermindeltas.id desc";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.twentyToThirty)");
                return average;
            }


        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Get the average for summoner in a certain position for their last 20 games in that role.
     * @param summonerId The Summoner we want the average for.
     * @param lane The lane we want to filter to.
     * @param role The role we want to filter to.
     * @return The average creeps per min.
     */
    public double GetRecentLateGameAverageForRecent20Matches(long summonerId, String lane, String role) {
        String queryString = "select avg(creepspermindeltas.twentyToThirty) " +
                "from participantSummary " +
                "join timeline on timeline.id = participantsummary.timelineId "+
                "join creepspermindeltas on creepspermindeltas.id = timeline.creepsPerMinDeltaId " +
                "where participantsummary.summonerId = "+summonerId+ " and " +
                "role = '"+role+"' and lane = '"+lane + "' "+
                "order by creepspermindeltas.id desc\n" +
                "         limit 20";

        try {
            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
            if (resultSet == null) {
                return 0;
            }
            if (resultSet.next()) {
                double average = resultSet.getDouble("avg(creepspermindeltas.twentyToThirty)");
                return average;
            }
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
