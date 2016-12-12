/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Josiah Kendall.
 */
public class MatchSummaryIntegrationTests {
    
    private DBHelper dbHelper;
    private MatchSummaryControl matchSummaryControl;
    
    /**
     * PRepare for our tests
     */
    public void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        boolean canConnect = dbHelper.Connect();
        matchSummaryControl = new MatchSummaryControl(dbHelper);
    }
    
    @Test
    public void TestSaveMatchSummmaryIntegration() {
        init();
        MatchSummary matchSummary = new MatchSummary();
        matchSummary.champion = 1;
        matchSummary.lane = "TOP";
        matchSummary.matchId = 1;
        matchSummary.platformId = "OCE";
        matchSummary.queue = "RANKED";
        matchSummary.region = "OCE";
        matchSummary.role = "SOLO";
        matchSummary.season = "6";
        matchSummary.timestamp = 1123;
        int resultInt = matchSummaryControl.saveMatchSummary(matchSummary, 123);
       
       Assert.assertTrue(resultInt > 0);
       MatchSummary matchSummary2 = matchSummaryControl.getMatchSummary(1);
       Assert.assertTrue(matchSummary.champion == matchSummary2.champion);  
    }
    
    @Test
    public void TestThatWeCanSaveMatchList() {
        init();
        MatchListControl matchListControl = new MatchListControl(matchSummaryControl, dbHelper);
        MatchSummary matchSummary1 = new MatchSummary();
        matchSummary1.champion = 1;
        matchSummary1.lane = "TOP";
        matchSummary1.matchId = 1;
        matchSummary1.platformId = "OCE";
        matchSummary1.queue = "RANKED";
        matchSummary1.region = "OCE";
        matchSummary1.role = "SOLO";
        matchSummary1.season = "6";
        matchSummary1.timestamp = 1123;
                
        MatchSummary matchSummary2 = new MatchSummary();
        matchSummary2.champion = 1;
        matchSummary2.lane = "TOP";
        matchSummary2.matchId = 1;
        matchSummary2.platformId = "OCE";
        matchSummary2.queue = "RANKED";
        matchSummary2.region = "OCE";
        matchSummary2.role = "SOLO";
        matchSummary2.season = "6";
        matchSummary2.timestamp = 1123;

        MatchSummary matchSummary3 = new MatchSummary();
        matchSummary3.champion = 1;
        matchSummary3.lane = "TOP";
        matchSummary3.matchId = 1;
        matchSummary3.platformId = "OCE";
        matchSummary3.queue = "RANKED";
        matchSummary3.region = "OCE";
        matchSummary3.role = "SOLO";
        matchSummary3.season = "6";
        matchSummary3.timestamp = 1123;

        MatchSummary matchSummary4 = new MatchSummary();
        matchSummary4.champion = 1;
        matchSummary4.lane = "TOP";
        matchSummary4.matchId = 1;
        matchSummary4.platformId = "OCE";
        matchSummary4.queue = "RANKED";
        matchSummary4.region = "OCE";
        matchSummary4.role = "SOLO";
        matchSummary4.season = "6";
        matchSummary4.timestamp = 1123;
        
        ArrayList<MatchSummary> matchSummaryArray = new ArrayList<>();
        matchSummaryArray.add(matchSummary1);
        matchSummaryArray.add(matchSummary2);
        matchSummaryArray.add(matchSummary3);
        matchSummaryArray.add(matchSummary4);

        matchListControl.saveMatches(matchSummaryArray, 1);
        
        ArrayList<MatchSummary> matches = matchListControl.getMatchSummariesForSummoner(1);
        Assert.assertTrue(matches.size() == matchSummaryArray.size());
    }
    
    @Test
    public void TestThatWeCanLoadMatchList() {
        
    }
}
