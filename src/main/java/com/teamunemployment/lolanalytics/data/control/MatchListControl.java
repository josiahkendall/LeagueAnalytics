/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchList;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to load and save the matchList for a user.
 * @author jek40
 */
public class MatchListControl {
    
    private MatchSummaryControl matchSummaryControl;
    private DBHelper dbHelper;
    
    public MatchListControl(MatchSummaryControl matchSummaryControl, DBHelper dbHelper) {
        this.matchSummaryControl = matchSummaryControl;
        this.dbHelper = dbHelper;
    }
    
    /**
     * Save an array of match summaries.
     * @param matchList The match
     */
    public void saveMatches(ArrayList<MatchSummary> matchList, long summonerId) {
        // iterate over each item and save them all one by one.
        Iterator<MatchSummary> matchListIterator = matchList.iterator();
        
        // Match lists can be 1000's of matches. If we put this matchlist inside
        // the loop we will consume a lot of memory and force the gc to run too frequently.
        MatchSummary match = null;
        while (matchListIterator.hasNext()) {
            match = matchListIterator.next();
            if (!matchSummaryControl.hasMatchSummary(match.matchId)) {
                matchSummaryControl.saveMatchSummary(match, summonerId);
            }
        }
    }
    
    /**
     * Get a list of all the match summaries for a summoner.
     * @param summonerId
     * @return 
     */
    public ArrayList<MatchSummary> getMatchSummariesForSummoner(long summonerId) {
        ArrayList<MatchSummary> resultArray = new ArrayList();
        boolean isLastItem = false;
        try {
            String query = "SELECT * from matchsummary WHERE SummonerId = " + summonerId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            
            while (!isLastItem) {
                resultSet.next();
                isLastItem = resultSet.isLast();
                // Why is this needed
                if(!isLastItem) {
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
                
                    resultArray.add(matchSummary);
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultArray;
    }
    
    public ArrayList<MatchSummary> getMatchSummariesSummonerOfACertainChampion(long summonerId, int championId) {
        return null;
    }
}
