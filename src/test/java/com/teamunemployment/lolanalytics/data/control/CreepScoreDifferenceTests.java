package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CsDiffPerMinDeltas;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Josiah Kendall
 */
public class CreepScoreDifferenceTests {

    @Test
    public void Load_EarlyGame_EnemyCreepScore_Difference_Recent20Games() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CsDiffPerMinDeltasControl creepsPerMinDeltaControl = new CsDiffPerMinDeltasControl(dbHelper);

        double result = creepsPerMinDeltaControl.LoadAverageDifferenceBetweenEnemyAndSummonerScore(971951, "PRESEASON2017", "SOLO", "TOP", 0);
        Assert.assertTrue(result == 0.20000000510896956);
    }

    @Test
    public void Load_MidGame_EnemyCreepScore_Difference_Recent20Games() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CsDiffPerMinDeltasControl creepsPerMinDeltaControl = new CsDiffPerMinDeltasControl(dbHelper);

        double result = creepsPerMinDeltaControl.LoadAverageDifferenceBetweenEnemyAndSummonerScore(971951, "PRESEASON2017", "SOLO", "TOP", CsDiffPerMinDeltasControl.MID_GAME);
        Assert.assertTrue(result == 0.971428605062621);
    }

    @Test
    public void Load_LateGame_EnemyCreepScore_Difference_Recent20Games() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.Connect();
        CsDiffPerMinDeltasControl creepsPerMinDeltaControl = new CsDiffPerMinDeltasControl(dbHelper);

        double result = creepsPerMinDeltaControl.LoadAverageDifferenceBetweenEnemyAndSummonerScore(971951, "PRESEASON2017", "SOLO", "TOP", CsDiffPerMinDeltasControl.LATE_GAME);
        Assert.assertTrue(result == 3.399999976158142);
    }
}
