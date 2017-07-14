package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchCreepsDeltaJunctionModel;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class MatchCreepsDeltaJunctionControlTests {

    private DBHelper dbHelper;
    private MatchCreepsDeltaJunctionControl matchCreepsDeltaJunctionControl;
    public void init() {
        dbHelper = new DBHelper();
        dbHelper.Connect();
        matchCreepsDeltaJunctionControl = new MatchCreepsDeltaJunctionControl(dbHelper);
    }


    @Test
    public void TestThatMatchCreepsDeltaJunctionSaves() {

        init();
        MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel = new MatchCreepsDeltaJunctionModel();
        matchCreepsDeltaJunctionModel.setCreepScoreDeltaId(1234);
        matchCreepsDeltaJunctionModel.setSummonerId(1);
        matchCreepsDeltaJunctionModel.setMatchId(12);

        int matchDeltaModelId = matchCreepsDeltaJunctionControl.SaveMatchCreepsDeltaJunctionControl(matchCreepsDeltaJunctionModel);
        MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel1 = matchCreepsDeltaJunctionControl.GetMatchCreepsDeltaJunctionModel(matchDeltaModelId);
        Assert.assertTrue(matchCreepsDeltaJunctionModel1.getCreepScoreDeltaId() == 1234);
    }

    @Test
    public void FetchAllMatchCreepsDelta_WorksForMatchId() {
        init();
        MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel = new MatchCreepsDeltaJunctionModel();
        matchCreepsDeltaJunctionModel.setCreepScoreDeltaId(1234);
        matchCreepsDeltaJunctionModel.setSummonerId(1);
        matchCreepsDeltaJunctionModel.setMatchId(44);
        MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel2 = new MatchCreepsDeltaJunctionModel();
        matchCreepsDeltaJunctionModel2.setCreepScoreDeltaId(1235);
        matchCreepsDeltaJunctionModel2.setSummonerId(1);
        matchCreepsDeltaJunctionModel2.setMatchId(44);

        MatchCreepsDeltaJunctionModel matchCreepsDeltaJunctionModel3 = new MatchCreepsDeltaJunctionModel();
        matchCreepsDeltaJunctionModel3.setCreepScoreDeltaId(1236);
        matchCreepsDeltaJunctionModel3.setSummonerId(1);
        matchCreepsDeltaJunctionModel3.setMatchId(44);


        int matchDeltaModelId = matchCreepsDeltaJunctionControl.SaveMatchCreepsDeltaJunctionControl(matchCreepsDeltaJunctionModel);
        int matchDeltaModelId2 = matchCreepsDeltaJunctionControl.SaveMatchCreepsDeltaJunctionControl(matchCreepsDeltaJunctionModel2);
        int matchDeltaModelId3 = matchCreepsDeltaJunctionControl.SaveMatchCreepsDeltaJunctionControl(matchCreepsDeltaJunctionModel3);
        ArrayList<MatchCreepsDeltaJunctionModel> matchCreepsDeltaJunctions = matchCreepsDeltaJunctionControl.getAllMatchCreepsDeltaJunctionModelsForAMatch(44);
        Assert.assertEquals(3, matchCreepsDeltaJunctions.size());
    }
}
