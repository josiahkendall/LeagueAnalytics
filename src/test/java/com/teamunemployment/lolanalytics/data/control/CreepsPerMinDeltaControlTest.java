/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.CreepsPerMinDeltas;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 *
 * @author jek40
 */
public class CreepsPerMinDeltaControlTest {
    
    @Test
    public void TestThatWeCanSaveCPMD() {
        DBHelper dbHelper = Mockito.mock(DBHelper.class);
        CreepsPerMinDeltaControl creepsPerMinDeltaControl = new CreepsPerMinDeltaControl(dbHelper);
        
        CreepsPerMinDeltas creepsPerMinDeltas = new CreepsPerMinDeltas();
        creepsPerMinDeltaControl.saveCPMD(creepsPerMinDeltas);
        
    }
}
