/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.PlayerParticipant;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jek40
 */
public class ParticipantIdentitySummaryTests {
    
    private DBHelper dbHelper;
    
    /**
     * PRepare for our tests
     */
    public void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/lolanalytics", "root", "Idnw2bh2");
        boolean canConnect = dbHelper.Connect();
    }
    
    @Test
    public void TestThatWeCanSaveParticipantIdentitySummaries() {
        
        init();
        ParticipantIdentityControl participantIdentityControl = new ParticipantIdentityControl(dbHelper);

        PlayerParticipant playerParticipant = new PlayerParticipant();
        playerParticipant.summonerId = 1553;
        playerParticipant.summonerName = "test";
        ParticipantIdentitySummary participantIdentitySummary = new ParticipantIdentitySummary();
        participantIdentitySummary.matchId = 123456;
        participantIdentitySummary.participantId = 1;
        participantIdentitySummary.player = playerParticipant;
        participantIdentitySummary.summonerId = 1553;
        
        ParticipantIdentitySummary participantIdentitySummary2 = new ParticipantIdentitySummary();
        participantIdentitySummary2.matchId = 1234568;
        participantIdentitySummary2.participantId = 1;
        participantIdentitySummary2.player = playerParticipant;
        participantIdentitySummary2.summonerId = 1553;
        
        ParticipantIdentitySummary participantIdentitySummary3 = new ParticipantIdentitySummary();
        participantIdentitySummary3.matchId = 1234567;
        participantIdentitySummary3.participantId = 1;
        participantIdentitySummary3.player = playerParticipant;
        participantIdentitySummary3.summonerId = 1553;
        
        participantIdentityControl.SaveParticipantIdentity(participantIdentitySummary);
        participantIdentityControl.SaveParticipantIdentity(participantIdentitySummary2);
        participantIdentityControl.SaveParticipantIdentity(participantIdentitySummary3);
        
        ArrayList<ParticipantIdentitySummary> participantIdentitySummaries = participantIdentityControl.GetAllParticipantIdentitiesForChampion(1553);
        Assert.assertTrue(participantIdentitySummaries.size() == 3);
    }
}
