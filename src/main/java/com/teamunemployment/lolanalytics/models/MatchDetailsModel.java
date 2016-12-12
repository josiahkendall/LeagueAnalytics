/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.models;

import java.util.List;

/**
 * Data pojo for the match details.
 * @author Josiah Kendall.
 */
public class MatchDetailsModel {
    public long matchId;
    public String region;
    public String platformId;
    public String matchMode;
    public String matchType;
    public long matchCreation;
    public long matchDuration;
    public String queueType;
    public int mapId;
    public String season;
    public String matchVersion;
    public List<ParticipantSummary> participants;
    public List<ParticipantIdentitySummary> participantIdentities;
    
    /**
     * Getter. Used for mock/testing.
     * @return 
     */
    public List<ParticipantSummary> getParticipants() {
        return participants;
    }
}
