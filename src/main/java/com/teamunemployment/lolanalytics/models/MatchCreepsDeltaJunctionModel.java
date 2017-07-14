package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall
 *
 * This is a pojo for the sql table with the same name. The purpose of it is to link the CreepsPerMinDeltas with the
 * match details. This allows us ti do joins and get the appropriate deltas for a match/sumoner.
 */
public class MatchCreepsDeltaJunctionModel {

    private int id;
    private long matchId;
    private long summonerId;
    private int creepScoreDeltaId;

    public int getCreepScoreDeltaId() {
        return creepScoreDeltaId;
    }

    public int getId() {
        return id;
    }

    public long getMatchId() {
        return matchId;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setCreepScoreDeltaId(int creepScoreDeltaId) {
        this.creepScoreDeltaId = creepScoreDeltaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
}
