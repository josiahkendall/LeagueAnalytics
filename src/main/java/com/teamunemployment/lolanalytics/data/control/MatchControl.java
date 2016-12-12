/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.ParticipantIdentitySummary;
import com.teamunemployment.lolanalytics.models.ParticipantSummary;
import java.util.Iterator;
import java.util.List;

/**
 * @author Josiah kendall
 */
public class MatchControl {
    
    private DBHelper dbHelper;
    private ParticipantControl participantControl;
    
    /**
     * Default constructor.
     * @param dbHelper 
     */
    public MatchControl(DBHelper dbHelper, ParticipantControl participantControl) {
        this.participantControl = participantControl;
    }
    
    /**
     * Save a match.
     * @param match The Match we are saving. 
     * @return The  
     */
    public int SaveMatch(MatchDetailsModel match) {
        // Save sql script
        List<ParticipantSummary> participants = match.participants;
        List<ParticipantIdentitySummary> participantIdentities = match.participantIdentities;
         
        Iterator<ParticipantSummary> participantsIterator = participants.iterator();
        Iterator<ParticipantIdentitySummary> participantIdentitiesIterator = participantIdentities.iterator();
         
        while (participantsIterator.hasNext()) {
            ParticipantSummary summary = participantsIterator.next();
            int id = SaveMatchParticipantSummary(summary);
        }
         
        while (participantIdentitiesIterator.hasNext()) {
            ParticipantIdentitySummary identitySummary = participantIdentitiesIterator.next();
            int participantIdentitySummaryId = SaveMatchParticipantIdentitySummary(identitySummary);
        }
        return 0;
    }
    
    /**
     * save a match participant summary
     * @param ps The participant summary that we wabnt ot save
     * @return The row identifier.
     */
    public int SaveMatchParticipantSummary(ParticipantSummary ps) {
//        ParticipantControl participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
//        return participantControl.saveParticipant(ps);
        return 0;
    }
    
    /**
     * Save an identity summary.
     * @param pis The Identity summary that we are saving.
     * @return The row identitfier of the saved identity.
     */
    public int SaveMatchParticipantIdentitySummary(ParticipantIdentitySummary pis) {
        return 0;
    }
    
}
