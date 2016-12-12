/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.CsDiffPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.GoldPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.Timeline;
import com.teamunemployment.lolanalytics.models.stats.XpDiffPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.XpPerMinDeltas;
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
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        boolean canConnect = dbHelper.Connect();
        CreepsPerMinDeltaControl cmpControl = new CreepsPerMinDeltaControl(dbHelper);
        CsDiffPerMinDeltasControl csdContorl = new CsDiffPerMinDeltasControl(dbHelper);
        
        GoldPerMinDeltasControl gpmControl = new GoldPerMinDeltasControl(dbHelper);
        XpPerMinDeltaControl xpPerMinDeltaControl = new XpPerMinDeltaControl(dbHelper);
                
        timelineControl = new TimelineControl(cmpControl, csdContorl, gpmControl, xpPerMinDeltaControl, dbHelper);
    }
    
    @Test
    public void TestThatWeCanSaveAndFetchTimelineAndAssociatedInfo() {
        init();
        Timeline timeline = new Timeline();
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltas.setId(444);
        creepsPerMinDeltas.setTenToTwenty(12.1);
        creepsPerMinDeltas.setThirtyToEnd(9.0);
        creepsPerMinDeltas.setTwentyToThirty(13.1);
        creepsPerMinDeltas.setZeroToTen(8.3);
        timeline.setCreepsPerMinDeltas(creepsPerMinDeltas);
        
        CsDiffPerMinDeltas csDiffPerMinDeltas = new CsDiffPerMinDeltas();
        csDiffPerMinDeltas.setId(444);
        csDiffPerMinDeltas.setTenToTwenty(12.1);
        csDiffPerMinDeltas.setThirtyToEnd(9.0);
        csDiffPerMinDeltas.setTwentyToThirty(13.1);
        csDiffPerMinDeltas.setZeroToTen(8.3);
        timeline.setCsDiffPerMinDeltas(csDiffPerMinDeltas);
        
        GoldPerMinDeltas goldPerMinDeltas = new GoldPerMinDeltas();
        goldPerMinDeltas.setId(444);
        goldPerMinDeltas.setTenToTwenty(12.1);
        goldPerMinDeltas.setThirtyToEnd(9.0);
        goldPerMinDeltas.setTwentyToThirty(13.1);
        goldPerMinDeltas.setZeroToTen(8.3);
        timeline.setGoldPerMinDeltas(goldPerMinDeltas);
        
        XpPerMinDeltas xpPerMinDeltas = new XpPerMinDeltas();
        xpPerMinDeltas.setId(444);
        xpPerMinDeltas.setTenToTwenty(12.1);
        xpPerMinDeltas.setThirtyToEnd(9.0);
        xpPerMinDeltas.setTwentyToThirty(13.1);
        xpPerMinDeltas.setZeroToTen(8.3);
        timeline.setXpPerMinDeltas(xpPerMinDeltas);
        
        XpDiffPerMinDeltas xpDiffPerMinDeltas = new XpDiffPerMinDeltas();
        xpDiffPerMinDeltas.setId(444);
        xpDiffPerMinDeltas.setTenToTwenty(12.1);
        xpDiffPerMinDeltas.setThirtyToEnd(9.0);
        xpDiffPerMinDeltas.setTwentyToThirty(13.1);
        xpDiffPerMinDeltas.setZeroToTen(8.3);
        timeline.setXpDiffPerMinDeltas(xpDiffPerMinDeltas);
        timeline.setRole("JUNGLE");
        
        int id = timelineControl.SaveTimeline(timeline);
        Timeline retrievedTimeline = timelineControl.getTimeline(id);
        Assert.assertTrue(retrievedTimeline.getCreepsPerMinDeltas().getTenToTwenty() == 12.1);
    }
}
