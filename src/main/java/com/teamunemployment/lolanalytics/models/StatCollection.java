package com.teamunemployment.lolanalytics.models;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class StatCollection {

    private ArrayList<StatPoint> collection;
    private StatSummary statSummary;

    public void setCollection(ArrayList<StatPoint> collection) {
        this.collection = collection;
    }

    public ArrayList<StatPoint> getCollection() {
        return collection;
    }

    public StatSummary getStatSummary() {
        return statSummary;
    }

    public void setStatSummary(StatSummary statSummary) {
        this.statSummary = statSummary;
    }
}
