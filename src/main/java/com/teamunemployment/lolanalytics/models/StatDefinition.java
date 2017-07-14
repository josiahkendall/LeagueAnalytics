package com.teamunemployment.lolanalytics.models;

/**
 * Created by jek40 on 16/02/2017.
 */
public class StatDefinition {
    private String statName;
    private int statId;

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public int getStatId() {
        return statId;
    }

    public String getStatName() {
        return statName;
    }
}
