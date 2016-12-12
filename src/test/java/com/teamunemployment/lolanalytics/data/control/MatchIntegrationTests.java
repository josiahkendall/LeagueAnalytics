/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import com.teamunemployment.lolanalyticsv3.RestApi.EntryApi;
import crawler.crawler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 *
 * @author jek40
 */
public class MatchIntegrationTests {
    
    private DBHelper dbHelper;
    private ParticipantControl participantControl;
    private ParticipantIdentityControl participantIdentityControl;
    private MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl;
    
    
    private void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        dbHelper.Connect();
        RuneControl runeControl = new RuneControl();
        CreepsPerMinDeltaControl cpmControl = new CreepsPerMinDeltaControl(dbHelper);
        CsDiffPerMinDeltasControl csdControl = new CsDiffPerMinDeltasControl(dbHelper);
        GoldPerMinDeltasControl gpmControl = new GoldPerMinDeltasControl(dbHelper);
        XpPerMinDeltaControl xpPerMinDeltaControl = new XpPerMinDeltaControl(dbHelper);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(cpmControl, csdControl, gpmControl, xpPerMinDeltaControl, dbHelper);

        participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
        participantIdentityControl = new ParticipantIdentityControl(dbHelper);
        matchParticipantSummaryJunctionControl = new MatchParticipantSummaryJunctionControl(dbHelper);
    }
    
    @Test
    public void TestThatWeCanLoadAllMatchesForAUser() {
        System.out.println("start");
        EntryApi api = new EntryApi();
        List<MatchSummary> matchSummaries = api.FetchMatchList(276353);
        init();
        MatchSummaryControl matchSummaryControl = new MatchSummaryControl(dbHelper);
        Iterator<MatchSummary> matchSummariesIterator = matchSummaries.iterator();
        System.out.println("starting loop");
        try {
            System.out.println("Sleeping");
            Thread.sleep(7000);
        } catch (InterruptedException ex) {
            System.out.println("Error sleeping: " + ex.getMessage());
        }
        while (matchSummariesIterator.hasNext()) {
            MatchSummary next = matchSummariesIterator.next();
            System.out.println("loop running");
            matchSummaryControl.saveMatchSummary(next, 276353);
            try {
                MatchDetailsModel match = api.FetchMatchDetails(next.matchId);
                
                MatchDetailsControl matchControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
                matchControl.SaveMatch(match, 1542360);
                try {
                    System.out.println("Sleeping");
                    Thread.sleep(7000);
                } catch (InterruptedException ex) {
                    System.out.println("Error sleeping: " + ex.getMessage());
                }
                // load match
                
            } catch(RetrofitError ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        
        
        
    }
    
    @Test
    public void TestFullLoad() {
        EntryApi api = new EntryApi();
        MatchDetailsModel mdm = api.FetchMatchDetails(148566213);
        Assert.assertTrue(mdm.participants.get(0).getChampionId() == 254);
    }
    
    @Test
    public void TestFullLoadAndSave() {
        EntryApi api = new EntryApi();
        MatchDetailsModel mdm = api.FetchMatchDetails(148566213);
        Assert.assertTrue(mdm.participants.get(0).getChampionId() == 254);
        
        init();
        MatchDetailsControl matchControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
        int id = matchControl.SaveMatch(mdm, 1);
        MatchDetailsModel mdm2 = matchControl.GetMatchDetailsModel(148566213);
        Assert.assertTrue(mdm2.participants.get(0).getChampionId() == 254);
    }
    
    @Test
    public void TestThatWeCanDownloadAllMatchSummary() {
        
// 
        System.out.println("start");

        EntryApi api = new EntryApi();
        List<MatchSummary> matchSummaries = api.FetchMatchList(1542360);
        init();
        MatchSummaryControl matchSummaryControl = new MatchSummaryControl(dbHelper);
        Iterator<MatchSummary> matchSummariesIterator = matchSummaries.iterator();
        System.out.println("starting loop");
         try {
                System.out.println("Sleeping");
                Thread.sleep(7000);
            } catch (InterruptedException ex) {
                System.out.println("Error sleeping: " + ex.getMessage());
            }
        while (matchSummariesIterator.hasNext()) {
            MatchSummary next = matchSummariesIterator.next();
            System.out.println("loop running");
            matchSummaryControl.saveMatchSummary(next, 1542360);
            try {
                MatchDetailsModel match = api.FetchMatchDetails(next.matchId);
                MatchDetailsControl matchControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
                matchControl.SaveMatch(match, 1542360);
                try {
                System.out.println("Sleeping");
                Thread.sleep(7000);
            } catch (InterruptedException ex) {
                System.out.println("Error sleeping: " + ex.getMessage());
            }
                // load match
                
            } catch(RetrofitError ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    
    @Test
    public void TestThatWeCanGetSummonerId() {
        
    }
    
    @Test
    public void TestThatWeCanFetchAllOurSummaries() {
        
    }
    
    
//    @Test
//    public void TestThatWeCanSaveAndFetchMatch() {
//        
//        init();
//        MatchDetailsControl matchControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper);
//        MatchDetailsModel matchDetails = new MatchDetailsModel();
//        matchDetails.mapId = 3;
//        matchDetails.matchMode = "test";
//        matchDetails.matchVersion = "test";
//        List<ParticipantSummary> participants = getParticipantList();
//        List<ParticipantIdentitySummary> participantIdentities;
//
//        //matchDetails.participantIdentities
//        int id = matchControl.SaveMatch(matchDetails);
//        MatchDetailsModel matchDetails2 = matchControl.GetMatchDetailsModel(id);
//    }
//    
//    private List<ParticipantSummary> getParticipantList() {
//        List<ParticipantSummary> participants = new ArrayList<>();
//        ParticipantSummary participantSummary = null;
//        int count = 0;
//        while (count < 9) {
//            participantSummary = new ParticipantSummary();
//            participantSummary.setChampionId(1);
//            participantSummary.setHighestAchievedSeasonTier("bronze");
//            Timeline timeline = new Timeline();
//            participantSummary.setTimeline(timeline);
//        }
//        return participants;
//    }
    
    public void TestThatWeCanFetchUserDetailsUsingName() {
        SummonerInfoControl summonerControl;

    }
}
