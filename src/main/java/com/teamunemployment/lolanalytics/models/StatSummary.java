package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall.
 */
public class StatSummary {
    private long id;
    private boolean hasGoal;
    private double goalvalue;
    private String statName;
    private double improvementValue;
    private String subName;

    public void setId(long id) {
        this.id = id;
    }

    public void setImprovementValue(double improvementValue) {
        this.improvementValue = improvementValue;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubName() {
        return subName;
    }

    public long getId() {
        return id;
    }

    public double getImprovementValue() {
        return improvementValue;
    }

    public String getStatName() {
        return statName;
    }

    public void setGoalvalue(double goalvalue) {
        this.goalvalue = goalvalue;
    }

    public void setHasGoal(boolean hasGoal) {
        this.hasGoal = hasGoal;
    }

    public double getGoalvalue() {
        return goalvalue;
    }

    public boolean getHasGoal() {
        return hasGoal;
    }
}
