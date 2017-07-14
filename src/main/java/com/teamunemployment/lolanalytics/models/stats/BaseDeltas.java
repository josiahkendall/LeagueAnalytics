package com.teamunemployment.lolanalytics.models.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Josiah Kendall
 */
public class BaseDeltas {

    private int id;

    @SerializedName("zeroToTen")
    @Expose
    private Double zeroToTen;
    @SerializedName("tenToTwenty")
    @Expose
    private Double tenToTwenty;
    @SerializedName("twentyToThirty")
    @Expose
    private Double twentyToThirty;
    @SerializedName("thirtyToEnd")
    @Expose
    private Double thirtyToEnd;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setZeroToTen(Double zeroToTen) {
        this.zeroToTen = zeroToTen;
    }

    public void setTenToTwenty(Double tenToTwenty) {
        this.tenToTwenty = tenToTwenty;
    }

    public void setTwentyToThirty(Double twentyToThirty) {
        this.twentyToThirty = twentyToThirty;
    }

    public void setThirtyToEnd(Double thirtyToEnd) {
        this.thirtyToEnd = thirtyToEnd;
    }

    public Double getZeroToTen() {
        return zeroToTen;
    }

    public Double getTenToTwenty() {
        return tenToTwenty;
    }

    public Double getTwentyToThirty() {
        return twentyToThirty;
    }

    public Double getThirtyToEnd() {
        return thirtyToEnd;
    }
}
