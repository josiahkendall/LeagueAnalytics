/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatisticsAPI;

import com.teamunemployment.lolanalytics.data.ComparisonResult;
import com.teamunemployment.lolanalytics.data.control.MatchDetailsControl;
import com.teamunemployment.lolanalytics.data.control.MatchSummaryControl;
import com.teamunemployment.lolanalytics.data.control.Summoner.SummonerInfoControl;
import com.teamunemployment.lolanalytics.models.GeneralStats;
import com.teamunemployment.lolanalytics.models.HeadToHeadStats;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Josiah Kendall
 */
public class StatisticsApi {
    
    private final MatchDetailsControl matchDetailsControl;
    private final MatchSummaryControl matchSummaryControl;
    
    public StatisticsApi(MatchDetailsControl matchDetailsControl, 
            MatchSummaryControl matchSummaryControl) {
        
        this.matchDetailsControl = matchDetailsControl;
        this.matchSummaryControl = matchSummaryControl;
    }
    
    /**
     * Get the average creeps per min that a player gets in a certain role.
     * @param summonerId The summoner we are getting the info for.
     * @param role The summoner
     * @return The lane CreepScore Average for the first 10 minutes in a specified role. 
     */
    public Double GetCSFirst10MinAverageForRole(long summonerId, String role) {
        int count = 0;
        double total = 0;
        // fetch all the match summaries for our summoner that match that role.
        ArrayList<MatchSummary> matchSummaries = matchSummaryControl.GetAllMatchSummariesForSummonerMatchingSpecificRole(summonerId, role);
        // fetch all the matches for those match summaries.
        ArrayList<CreepsPerMinDeltas> cpmDeltas = new ArrayList<>();
        Iterator<MatchSummary> matchSummaryIterator = matchSummaries.iterator();
        while (matchSummaryIterator.hasNext()) {
            MatchSummary next = matchSummaryIterator.next();
            int arrayIndex = 0;
            // get match details.
            MatchDetailsModel matchDetailsModel = matchDetailsControl.GetMatchDetailsModel(next.matchId);
            List<ParticipantSummary> summaries = matchDetailsModel.participants;
            List<ParticipantIdentitySummary> identitySummaries = matchDetailsModel.participantIdentities;
            for(ParticipantIdentitySummary identitySummary : identitySummaries) {
                if (identitySummary.summonerId == summonerId) {
                    ParticipantSummary summary = summaries.get(arrayIndex);
                    // grab cs stats from it.
                    try {
                        
                        Double zeroToTen = summary.getTimeline().getCreepsPerMinDeltas().getZeroToTen();
                        total += zeroToTen;
                        count += 1;
                    } catch (NullPointerException ex) {
                        System.out.println("Error grabbing the creepscore delte for particpant with id: " + summary.getId());
                    }   
                }
                
                arrayIndex += 1;
            }
        }
        // go thjrough the matches, grab the timelines and calculate the averages.
        return total / count;
    }
    
    /**
     * Get the average cpm in the first 10 for a summoner in a role for a certain 
     * season. (2011 - current).
     * @param summonerId.
     * @param role The role we are limiting to.
     * @param season The season that we are limiting to.
     * @return The average.
     */
    public Double GetCSFirst10MinAverageForRole(long summonerId, String role, String season) {
        int count = 0;
        double total = 0;
        // fetch all the match summaries for our summoner that match that role.
        ArrayList<MatchSummary> matchSummaries = matchSummaryControl.GetAllMatchSummariesForSummonerMatchingSpecificRole(summonerId, role);
        // fetch all the matches for those match summaries.
        ArrayList<CreepsPerMinDeltas> cpmDeltas = new ArrayList<>();
        Iterator<MatchSummary> matchSummaryIterator = matchSummaries.iterator();
        while (matchSummaryIterator.hasNext()) {
            MatchSummary next = matchSummaryIterator.next();
            int arrayIndex = 0;
            // get match details.
            MatchDetailsModel matchDetailsModel = matchDetailsControl.GetMatchDetailsModel(next.matchId);
            List<ParticipantSummary> summaries = matchDetailsModel.participants;
            
            List<ParticipantIdentitySummary> identitySummaries = matchDetailsModel.participantIdentities;
            for(ParticipantIdentitySummary identitySummary : identitySummaries) {
                if (identitySummary.summonerId == summonerId) {
                    ParticipantSummary summary = summaries.get(arrayIndex);
                    summary.getTeamId();
                    // Get opponent summary
                    // calculate mine
                    if (summary.getSeason().equals(season)) {
                        // grab cs stats from it.
                        try {
                            Double zeroToTen = summary.getTimeline().getCreepsPerMinDeltas().getZeroToTen();
                            total += zeroToTen;
                            count += 1;
                        } catch (NullPointerException ex) {
                            System.out.println("Error grabbing the creepscore delte for particpant with id: " + summary.getId());
                        }   
                    }
                }
                
                arrayIndex += 1;
            }
        }
       
        
        
        // go thjrough the matches, grab the timelines and calculate the averages.
        return total / count;
    }
    
    
    /**
     * Get the average cpm in the first 10 for a summoner in a role for a certain season
     * with a certain champion.
     * @param summonerId The summoner we are fetching the info for.
     * @param role The role.
     * @param season The season.
     * @param champId The id of the champ we are filtering to.
     * @return The average creeps per min in the first 10 min.
     */
    public Double GetFirst10MinAverageForRole(long summonerId, int role, String season, int champId) {
        return 0.0;
    }
    
    public Double GetWinRatio(long summonerId, int role, String season) {
        return 0.0;
    }
    /**
     * get a ComparisonResult object
     * @param summonerId
     * @param role
     * @param season
     * @return 
     */
    public ComparisonResult GetCreepScoreFirst10MinComparison(long summonerId, String role, String season ) {
        int count = 0;
        double total = 0;
        double myTotal = 0;
        double theirTotal = 0;
        // fetch all the match summaries for our summoner that match that role.
        ArrayList<MatchSummary> matchSummaries = matchSummaryControl.GetAllMatchSummariesForSummonerMatchingSpecificRole(summonerId, role);
        // fetch all the matches for those match summaries.
        ArrayList<CreepsPerMinDeltas> cpmDeltas = new ArrayList<>();
        Iterator<MatchSummary> matchSummaryIterator = matchSummaries.iterator();
        while (matchSummaryIterator.hasNext()) {
            MatchSummary next = matchSummaryIterator.next();
            int arrayIndex = 0;
            // get match details.
            MatchDetailsModel matchDetailsModel = matchDetailsControl.GetMatchDetailsModel(next.matchId);
            List<ParticipantSummary> summaries = matchDetailsModel.participants;
            
            List<ParticipantIdentitySummary> identitySummaries = matchDetailsModel.participantIdentities;
            for(ParticipantIdentitySummary identitySummary : identitySummaries) {
                if (identitySummary.summonerId == summonerId) {
                    ParticipantSummary summary = summaries.get(arrayIndex);
                    int teamId = summary.getTeamId();
                    
                    // Get opponent summary
                    ParticipantSummary opponentSummary = getOpponentSummary(teamId, role, summaries);
                    if (opponentSummary != null && opponentSummary.getSeason().equals(season)) {
                        try {
                            Double zeroToTen = summary.getTimeline().getCreepsPerMinDeltas().getZeroToTen();
                            theirTotal += zeroToTen;
                            count += 1;
                        } catch (NullPointerException ex) {
                            System.out.println("Error grabbing the creepscore delta for particpant with id: " + summary.getId());
                        }    
                    }
                    
                    // calculate mine
                    if (summary.getSeason().equals(season)) {
                        // grab cs stats from it.
                        try {
                            Double zeroToTen = summary.getTimeline().getCreepsPerMinDeltas().getZeroToTen();
                            myTotal += zeroToTen;
                            count += 1;
                        } catch (NullPointerException ex) {
                            System.out.println("Error grabbing the creepscore delte for particpant with id: " + summary.getId());
                        }   
                    }
                    
                    // calculate opponent
                }
                
                arrayIndex += 1;
            }
        }
        
        ComparisonResult result = new ComparisonResult();
        result.theirScore = theirTotal / count;
        result.myScore = myTotal / count;
        return result;
    }

    private ParticipantSummary getOpponentSummary(int teamId, String role, List<ParticipantSummary> summaries) {
        for (ParticipantSummary summary : summaries) {
            if (summary.getTeamId() != teamId && summary.getTimeline().getLane().equals(role)) {
                return summary;
            }
        }
        
        return null;
    }
    
    public long FetchSummonerId(String summonerName) throws IOException {
        SummonerInfoControl summonerInfoControl = new SummonerInfoControl();
        SummonerInfo summonerInfo = summonerInfoControl.FetchSummonerInfo(summonerName);
        return summonerInfo.id;
    }
    
    
    public GeneralStats FetchAndCalculateStatsForAUserAndRole(String userName, String role) {
        try {
            long summonerId = FetchSummonerId(userName);
            GeneralStats stats = fetchAndCalculateStatsForAUserAndRoleUsingSummonerId(summonerId, role);
            return stats;
        } catch (IOException ex) {
            System.out.println("An eror occurred loading summonerId.");
            // Debug. Should be logged better than this.
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Fetch the head to head stats for our user.
     * @param summonerId
     * @param role
     * @return 
     */
    private GeneralStats fetchAndCalculateStatsForAUserAndRoleUsingSummonerId(long summonerId, String role) {
        ArrayList<Long> ids = matchDetailsControl.GetTwentyRecentMatchIdsForSpecifiedRole(summonerId, role);
        GeneralStats generalStats = new GeneralStats();
        int count = 0;
        
        long matchId;
    
        int csTenMe = 0;
        int csTwentyMe = 0;
        int csTotalMe = 0;
    
        int csTenThem = 0;
        int csTwentyThem = 0;
        int csTotalThem = 0;
        
        long summonerIdMe = 0;
        long summonerIdThem = 0;
    
        int killsMe = 0;
        int killsThem = 0;
        int deathsMe = 0;
        int deathsThem = 0;
        int assistsMe = 0;
        int assistsThem = 0;
    
        double goldFirst10 = 0;
        double goldSecond10 = 0;
        double goldFirst10Them = 0;
        double goldSecond10Them = 0;
        
        Iterator<Long> idsIterator = ids.iterator();
        while (idsIterator.hasNext()) {
            long id = idsIterator.next();
            
            // Fetch the match head to head details.
            HeadToHeadStats stats = matchDetailsControl.fetchHeadToHeadStatsForMatch(id, role, summonerId);
            csTenMe += stats.csTenMe;
            csTotalMe += stats.csTotalMe;
            csTwentyMe += stats.csTwentyMe;
            
            csTenThem += stats.csTenThem;
            csTwentyThem += stats.csTwentyThem;
            csTotalThem += stats.csTotalThem;
            
            killsMe += stats.killsMe;
            killsThem += stats.killsThem;
            
            deathsMe += stats.deathsMe;
            deathsThem += stats.deathsThem;
            
            assistsMe += stats.assistsMe;
            assistsThem += stats.assistsThem;
            
            goldFirst10 += stats.goldFirst10;
            goldSecond10 += stats.goldSecond10;
            goldFirst10Them += stats.goldFirst10Them;
            goldSecond10Them += stats.goldSecond10Them;
            
            count +=1;
        }
        // if numbers are 0, this will crash.
        //generalStats.KDA = (killsMe + assistsMe) / deathsMe;
        generalStats.averageAssits = new Double(new Double(assistsMe) / new Double(count));
        generalStats.averageCsEarlyGame = new Double(new Double(csTenMe) / new Double(count));
        generalStats.averageCsMidGame = new Double(new Double(csTwentyMe) / new Double(count));
        generalStats.averageDeaths = new Double(new Double(deathsMe) / new Double(count));
        generalStats.averageKills = new Double(new Double(killsMe) / new Double(count));
        generalStats.averageTotalCs = new Double(new Double(csTotalMe) / new Double(count));
        generalStats.averageAssitsEnemy = new Double(new Double(assistsThem) / new Double(count));
        generalStats.averageCsEarlyGameEnemy = new Double(new Double(csTenThem) / new Double(count));
        generalStats.averageCsMidGameEnemy = new Double(new Double(csTwentyThem) / new Double(count));
        generalStats.averageDeathsEnemy = new Double(new Double(deathsThem)/new Double(count));
        generalStats.averageKillsEnemy = new Double(new Double(killsThem)/ new Double(count));
        generalStats.averageTotalCsEnemy = new Double(new Double(csTotalThem) / new Double(count));
        
        return generalStats;
   }
    
}
