/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import com.teamunemployment.lolanalytics.models.stats.XpPerMinDeltas;
import java.sql.SQLException;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author jek40
 */
public class XpPerMinDeltaControlTest {
    
//    @Test
//    public void TestThatWeCanSaveCPMD() throws SQLException {
//        DBHelper dbHelper = Mockito.mock(DBHelper.class);
//        XpPerMinDeltaControl xpPerMinDeltaControl = new XpPerMinDeltaControl(dbHelper);
//        
//        XpPerMinDeltas xpmd = new XpPerMinDeltas();
//        xpmd.setId(1);
//        xpmd.setZeroToTen(1.0);
//        xpmd.setTenToTwenty(2.0);
//        xpmd.setTwentyToThirty(3.0);
//        xpmd.setThirtyToEnd(4.0);
//
//        xpPerMinDeltaControl.saveXpPMD(xpmd);
//        String correctQuery = "Insert into xppermindeltas values (1.0, 2.0, 3.0, 4.0)";
//        Mockito.verify(dbHelper, Mockito.times(1)).ExecuteSqlScript(correctQuery);
//    }
}
