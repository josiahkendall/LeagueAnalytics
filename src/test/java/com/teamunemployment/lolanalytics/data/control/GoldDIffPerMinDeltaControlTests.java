package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.data.statics;
import com.teamunemployment.lolanalytics.models.stats.BaseDeltas;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Josiah Kendall
 */
public class GoldDIffPerMinDeltaControlTests {

    @Test
    public void SaveGoldDiffPerMin_Works() {

        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        GoldDiffPerMinDeltasControl goldDiffPerMinControl = new GoldDiffPerMinDeltasControl(dbHelper);

        BaseDeltas deltas = new BaseDeltas();
        deltas.setZeroToTen(54.3);
        deltas.setTenToTwenty(55.3);
        deltas.setTwentyToThirty(56.3);
        deltas.setThirtyToEnd(57.3);
        int id = goldDiffPerMinControl.saveGPMD(deltas);
        BaseDeltas deltas1 = goldDiffPerMinControl.getGPMD(id);
        Assert.assertTrue(deltas1.getZeroToTen() == 54.3);
        Assert.assertTrue(deltas1.getTenToTwenty() == 55.3);
        Assert.assertTrue(deltas1.getTwentyToThirty() == 56.3);
        Assert.assertTrue(deltas1.getThirtyToEnd() == 57.3);
    }

    @Test
    public void Load_EarlyGame_GoldDifference_RecentTwentyGameAverage() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        GoldDiffPerMinDeltasControl goldDiffPerMinDeltasControl = new GoldDiffPerMinDeltasControl(dbHelper);

        double result = goldDiffPerMinDeltasControl.LoadAverageGoldDifferenceBetweenEnemyAndSummoner(971951, "PRESEASON2017", "TOP", "SOLO", statics.EARLY_GAME);
        Assert.assertTrue(result == 5.199999809265137);
    }

    @Test
    public void Load_EarlyGame_GoldDifference_RecentTwentyGameAverage_WorksWithGenereicStrategy() {

    }
}
