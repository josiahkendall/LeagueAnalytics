/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jek40
 */
public class CreepsPerMinDeltaControlTest {
    
    @Test
    public void TestThatWeCanSaveCPMD() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);
        
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setZeroToTen(54.3);
        creepsPerMinDeltas.setTenToTwenty(55.3);
        creepsPerMinDeltas.setTwentyToThirty(56.3);
        creepsPerMinDeltas.setThirtyToEnd(57.3);
        creepsPerMinDeltaControl.saveCPMD(creepsPerMinDeltas);
    }

    @Test
    public void SaveCPMD_SavesAndFetchesCorrectValues() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();

        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setZeroToTen(54.3);
        creepsPerMinDeltas.setTenToTwenty(55.3);
        creepsPerMinDeltas.setTwentyToThirty(56.3);
        creepsPerMinDeltas.setThirtyToEnd(57.3);
        int id = creepsPerMinDeltaControl.saveCPMD(creepsPerMinDeltas);
        CreepsPerMinDeltas deltas = creepsPerMinDeltaControl.getCPMD(id);
    }

    /**
     * This test relies on the current database state. If it fails, run the query against the db and update the value
     */
    @Test
    public void LoadMaxEarlyGameCreepsPerMin_LoadsMax() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetEarlyGameMaxCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 7.800000190734863);
    }

    @Test
    public void LoadMInEarlyGameCreepsPerMin_LoadsMin() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetEarlyGameMinCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 5.199999809265137);
    }

    @Test
    public void LoadAvgEarlyGameCreepsPerMinForRecent20Games_LoadsAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetRecentEarlyGameAverageForRecent20Matches(971951, "TOP", "SOLO");
        Assert.assertTrue(result == 6.499999904632569);
    }

    @Test
    public void LoadAvgEarlyGameCreepsPerMinForAllGamesInASeason_LoadsCorrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetEarlyGameSeasonAverage(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 6.499999904632569);
    }

    @Test
    public void LoadAvgEarlyGameCreepsPerMInForASummonerWIthASPecificChampINASeason_LoadsCOrrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetEarlyGameSeasonAverage(971951, "PRESEASON2017", 69, "MIDDLE", "SOLO");
        Assert.assertTrue(result == 7.253846168518066);
    }

    /**
     * This test relies on the current database state. If it fails, run the query against the db and update the value
     */
    @Test
    public void LoadMaxMidGameCreepsPerMin_LoadsMax() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetMidGameMaxCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 9.600000381469727);
    }

    @Test
    public void LoadMinMidGameCreepsPerMin_LoadsMin() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetMidGameMinCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 5.900000095367432);
    }

    @Test
    public void LoadAvgMidGameCreepsPerMinForRecent20Games_LoadsAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetRecentMidGameAverageForRecent20Matches(971951, "TOP", "SOLO");
        Assert.assertTrue(result == 7.400000095367432);
    }

    @Test
    public void LoadAvgMidGameCreepsPerMinForAllGamesInASeason_LoadsCorrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetMidGameSeasonAverage(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 7.400000095367432);
    }

    @Test
    public void LoadAvgMidGameCreepsPerMInForASummonerWIthASPecificChampINASeason_LoadsCOrrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetMidGameSeasonAverage(971951, "PRESEASON2017", 69, "MIDDLE", "SOLO");
        Assert.assertTrue(result == 7.084615303919866);
    }

    /**
     * Late Game tests
     */
    /**
     * This test relies on the current database state. If it fails, run the query against the db and update the value
     */
    @Test
    public void LoadMaxLateGameCreepsPerMin_LoadsMax() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetLateGameMaxCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 10.5);
    }

    @Test
    public void LoadMinLateGameCreepsPerMin_LoadsMin() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetLateGameMinCreepsForTheSeason(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 5.900000095367432);
    }

    @Test
    public void LoadLateMidGameCreepsPerMinForRecent20Games_LoadsAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetRecentLateGameAverageForRecent20Matches(971951, "TOP", "SOLO");
        Assert.assertTrue(result == 8.200000047683716);
    }

    @Test
    public void LoadAvgLateGameCreepsPerMinForAllGamesInASeason_LoadsCorrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetLateGameSeasonAverage(971951, "PRESEASON2017", "TOP", "SOLO");
        Assert.assertTrue(result == 8.200000047683716);
    }

    @Test
    public void LoadAvgLateGameCreepsPerMInForASummonerWIthASPecificChampINASeason_LoadsCOrrectAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);

        double result = creepsPerMinDeltaControl.GetLateGameSeasonAverage(971951, "PRESEASON2017", 69, "MIDDLE", "SOLO");
        Assert.assertTrue(result == 5.7200000286102295);
    }
}
