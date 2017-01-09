/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.database;

import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.control.CreepsPerMinDeltaControl;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import java.sql.SQLException;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author jek40
 */
public class TestCreepsPerMinDelta {
//    @Test
//    public void TestThatWeCanSaveCorrectly() throws SQLException {
//        DBHelper dbHelper = Mockito.mock(DBHelper.class);
//        CreepsPerMinDeltaControl cpmControl = new CreepsPerMinDeltaControl(dbHelper);
//        
//        CreepsPerMinDeltas cpmd = new CreepsPerMinDeltas();
//        cpmd.setId(1);
//        cpmd.setZeroToTen(1.0);
//        cpmd.setTenToTwenty(2.0);
//        cpmd.setTwentyToThirty(3.0);
//        cpmd.setThirtyToEnd(4.0);
//
//        cpmControl.saveCPMD(cpmd);
//        String correctQuery = "Insert into creepspermindeltas values (1.0, 2.0, 3.0, 4.0)";
//        Mockito.verify(dbHelper, Mockito.times(1)).ExecuteSqlScript(correctQuery);
//    }
}
