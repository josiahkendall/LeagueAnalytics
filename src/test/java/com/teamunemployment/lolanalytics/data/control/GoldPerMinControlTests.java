package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.GoldPerMinDeltas;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Josiah Kendall
 */
public class GoldPerMinControlTests {

    @Test
    public void CanSaveGPM() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        GoldPerMinDeltasControl goldPerMinDeltasControl = new GoldPerMinDeltasControl(dbHelper);
        GoldPerMinDeltas goldPerMinDeltas = new GoldPerMinDeltas();
        goldPerMinDeltas.setZeroToTen(12.3);
        goldPerMinDeltas.setThirtyToEnd(30.2);
        goldPerMinDeltas.setTenToTwenty(25.3);
        goldPerMinDeltas.setTwentyToThirty(23.4);

        int id = goldPerMinDeltasControl.saveGPMD(goldPerMinDeltas);
        GoldPerMinDeltas goldPerMinDeltas1 = goldPerMinDeltasControl.getGPMD(id);
        Assert.assertTrue(goldPerMinDeltas1.getZeroToTen() == 12.3);
        Assert.assertTrue(goldPerMinDeltas1.getTenToTwenty() == 25.3);
        Assert.assertTrue(goldPerMinDeltas1.getTwentyToThirty() == 23.4);
        Assert.assertTrue(goldPerMinDeltas1.getThirtyToEnd() == 30.2);
    }
}
