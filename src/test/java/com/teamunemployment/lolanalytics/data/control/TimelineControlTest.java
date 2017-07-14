/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.*;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jek40
 */
public class TimelineControlTest {
    
    private DBHelper dbHelper;
    private TimelineControl timelineControl;
    
    /**
     * Prepare for our tests
     */
    public void init() {
        dbHelper = new DBHelper();
        boolean canConnect = dbHelper.Connect();
        BaseDeltaControl baseDeltaControl = new BaseDeltaControl(dbHelper);
        DeltaControl deltaControl =new DeltaControl(baseDeltaControl);
        timelineControl = new TimelineControl(dbHelper, deltaControl);
    }
    
    @Test
    public void TestThatWeCanSaveAndFetchTimelineAndAssociatedInfo() {
        init();
        Timeline timeline = new Timeline();
        BaseDeltas creepsPerMinDeltas = new BaseDeltas();
        creepsPerMinDeltas.setId(444);
        creepsPerMinDeltas.setTenToTwenty(12.1);
        creepsPerMinDeltas.setThirtyToEnd(9.0);
        creepsPerMinDeltas.setTwentyToThirty(13.1);
        creepsPerMinDeltas.setZeroToTen(8.3);
        timeline.setCreepsPerMinDeltas(creepsPerMinDeltas);

        BaseDeltas csDiffPerMinDeltas = new BaseDeltas();
        csDiffPerMinDeltas.setId(444);
        csDiffPerMinDeltas.setTenToTwenty(12.1);
        csDiffPerMinDeltas.setThirtyToEnd(9.0);
        csDiffPerMinDeltas.setTwentyToThirty(13.1);
        csDiffPerMinDeltas.setZeroToTen(8.3);
        timeline.setCsDiffPerMinDeltas(csDiffPerMinDeltas);

        BaseDeltas goldPerMinDeltas = new BaseDeltas();
        goldPerMinDeltas.setId(444);
        goldPerMinDeltas.setTenToTwenty(12.1);
        goldPerMinDeltas.setThirtyToEnd(9.0);
        goldPerMinDeltas.setTwentyToThirty(13.1);
        goldPerMinDeltas.setZeroToTen(8.3);
        timeline.setGoldPerMinDeltas(goldPerMinDeltas);

        BaseDeltas xpPerMinDeltas = new BaseDeltas();
        xpPerMinDeltas.setId(444);
        xpPerMinDeltas.setTenToTwenty(12.1);
        xpPerMinDeltas.setThirtyToEnd(9.0);
        xpPerMinDeltas.setTwentyToThirty(13.1);
        xpPerMinDeltas.setZeroToTen(8.3);
        timeline.setXpPerMinDeltas(xpPerMinDeltas);

        BaseDeltas xpDiffPerMinDeltas = new BaseDeltas();
        xpDiffPerMinDeltas.setId(444);
        xpDiffPerMinDeltas.setTenToTwenty(12.1);
        xpDiffPerMinDeltas.setThirtyToEnd(9.0);
        xpDiffPerMinDeltas.setTwentyToThirty(13.1);
        xpDiffPerMinDeltas.setZeroToTen(8.3);
        timeline.setXpDiffPerMinDeltas(xpDiffPerMinDeltas);
        timeline.setRole("JUNGLE");

        BaseDeltas damageTakenPerMinDeltas = new BaseDeltas();
        xpDiffPerMinDeltas.setId(444);
        xpDiffPerMinDeltas.setTenToTwenty(12.1);
        xpDiffPerMinDeltas.setThirtyToEnd(9.0);
        xpDiffPerMinDeltas.setTwentyToThirty(13.1);
        xpDiffPerMinDeltas.setZeroToTen(8.3);
        timeline.setDamageTakenPerMinDeltas(damageTakenPerMinDeltas);

        timeline.setDamageTakenDiffPerMinDeltas(damageTakenPerMinDeltas);

        int id = timelineControl.SaveTimeline(timeline);
        Timeline retrievedTimeline = timelineControl.getTimeline(id);
        Assert.assertTrue(retrievedTimeline.getCreepsPerMinDeltas().getTenToTwenty() == 12.1);
    }
}
