package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall.
 */
public class StatPoint {

    private int id;
    private long statId;
    private double xValue;
    private double yValue; // not sure if this is needed. We could probably just sort by id.

    public void setId(int id) {
        this.id = id;
    }

    public void setStatId(long statId) {
        this.statId = statId;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public double getxValue() {
        return xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public int getId() {
        return id;
    }

    public long getStatId() {
        return statId;
    }
}
