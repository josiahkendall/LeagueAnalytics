package com.teamunemployment.lolanalytics.data.control.EarlyGame;

import com.teamunemployment.lolanalytics.data.control.CreepsPerMinDeltaControl;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Josiah Kendall
 */
public class CreepsPerMinCalculator {

    private CreepsPerMinDeltaControl creepsPerMinDeltaControl;

    public CreepsPerMinCalculator(CreepsPerMinDeltaControl creepsPerMinDeltaControl) {
        this.creepsPerMinDeltaControl = creepsPerMinDeltaControl;
    }
    /**
     * Get the season average of creeps per min inf the first 10 mins for a summoner
     * @param summonerId The summoner that we want the info for
     * @param season The season to be jolly tralalalalalalalala
     * @return The average, to 2 decimal places
     */
    public double GetSeasonAverage(long summonerId, String season, String lane, String role) {
        ArrayList<CreepsPerMinDeltas> creepScoresForTheSeason = creepsPerMinDeltaControl.GetAllEarlyGameCreepsPerMinForASeason(summonerId, season, lane, role);
        double average = calcuateEarlyGameCreepScoreAverage(creepScoresForTheSeason);
        return average;
    }

    private double calcuateEarlyGameCreepScoreAverage(ArrayList<CreepsPerMinDeltas> creepScoresForTheSeason) {
        double total = 0;
        Iterator<CreepsPerMinDeltas> creepsPerMinDeltasIterator = creepScoresForTheSeason.iterator();

        CreepsPerMinDeltas currentCreepsPerMinDeltas;
        while (creepsPerMinDeltasIterator.hasNext()) {
            currentCreepsPerMinDeltas = creepsPerMinDeltasIterator.next();
            total += currentCreepsPerMinDeltas.getZeroToTen();
        }
        double average = total/creepScoresForTheSeason.size();
        return average;
    }

    /**
     * Get the recent 20 game average for a summoner.
     * @param summonerId The id of the summoner that we are interested in.
     * @param lane the lane to filter the results by.
     * @param role The role to filter the results by.
     * @return The recent 20 game average, to 2 decimal places.
     */
    public double GetRecentAverage(long summonerId, String lane, String role) {
//        ArrayList<CreepsPerMinDeltas> creepScoresForTheSeason = creepsPerMinDeltaControl.GetMostRecent20EarlyGameCreepScoresForASummoner(summonerId, lane, role);
//        double average = calcuateEarlyGameCreepScoreAverage(creepScoresForTheSeason);
        double average = creepsPerMinDeltaControl.GetEarlyGameSeasonAverage(summonerId, "SEASON2017", lane, role);
        return average;
    }

    /**
     * Get the recent 20 game average for a summoner.
     * @param summonerId The id of the summoner that we are interested in.
     * @param championId theid of the champ we are filtering by.
     * @param lane the lane to filter the results by.
     * @param role The role to filter the results by.
     * @return The recent 20 game average, to 2 decimal places.
     */
    public double GetRecentAverage(long summonerId, int championId, String lane, String role) {
//        ArrayList<CreepsPerMinDeltas> creepScoresForTheSeason = creepsPerMinDeltaControl.GetMostRecent20EarlyGameCreepScoresForASummoner(summonerId, championId, lane, role);
//        double average = calcuateEarlyGameCreepScoreAverage(creepScoresForTheSeason);
        double average = creepsPerMinDeltaControl.GetEarlyGameSeasonAverage(summonerId, "SEASON2017", championId, lane, role);
        return average;
    }


    /**
     * Get the best creep score a summoner has got this season.
     * @param summonerId The id of the summoner that we are interested in.
     * @return The best score.
     */
    public int GetSeasonBest(long summonerId) {
        return 0;
    }

    /**
     * Get the worst creep score that a summoner has got in the early game this season.
     * @param summonerId The id of the summoner that we want the stat for.
     * @return The worst score.
     */
    public int GetSeasonWorst(long summonerId) {
        return 0;
    }

    /**
     * Get an array list of the most recent five game average for a summoner
     * @param summonerId The id of the summoner that we want the stats for
     * @return The summonerId.
     */
    public ArrayList<Double> GetFloatingSeasonAverage(long summonerId) {
        return new ArrayList<>();
    }
}
