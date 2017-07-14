package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.data.statics;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Josiah Kendall
 *
 * Test the {@link com.teamunemployment.lolanalytics.data.control.BaseDeltaControl} class
 */
public class BaseDeltaTests {

    @Test
    public void Load_EarlyGame_EnemyCreepScore_Difference_Recent20Games() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        BaseDeltaControl base = new BaseDeltaControl(dbHelper);

        double result = base.LoadDeltaAverage(971951, "PRESEASON2017", "SOLO", "TOP", statics.EARLY_GAME,
                "csDiffPerMinDeltaId", 20, "csdiffpermindeltas");
        Assert.assertTrue(result == 0.20000000510896956);

        /**
         * double result = creepsPerMinDeltaControl.LoadDeltaAverage(971951, "PRESEASON2017", "SOLO", "TOP", 0);
         Assert.assertTrue(result == 0.20000000510896956);
         */
    }
}
