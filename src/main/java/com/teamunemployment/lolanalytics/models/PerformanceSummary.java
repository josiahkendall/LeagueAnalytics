package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall
 */
public class PerformanceSummary {

    private long champId;
    private String champName;
    private AdapterPojo kills;
    private AdapterPojo deaths;
    private AdapterPojo csFirstTen;
    private AdapterPojo csSecondTen;
    private AdapterPojo csTotal;

    // Default
    public PerformanceSummary() {}

    //
    public PerformanceSummary(long champId, String champName, AdapterPojo kills, AdapterPojo deaths, AdapterPojo csFirstTen, AdapterPojo csSecondTen, AdapterPojo csTotal) {
        this.champId = champId;
        this.champName = champName;
        this.kills = kills;
        this.csFirstTen = csFirstTen;
        this.csSecondTen = csSecondTen;
        this.csTotal = csTotal;
    }

    public void setChampId(long champId) {
        this.champId = champId;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public void setCsFirstTen(AdapterPojo csFirstTen) {
        this.csFirstTen = csFirstTen;
    }

    public void setCsSecondTen(AdapterPojo csSecondTen) {
        this.csSecondTen = csSecondTen;
    }

    public void setCsTotal(AdapterPojo csTotal) {
        this.csTotal = csTotal;
    }

    public void setDeaths(AdapterPojo deaths) {
        this.deaths = deaths;
    }

    public void setKills(AdapterPojo kills) {
        this.kills = kills;
    }

    public AdapterPojo getCsFirstTen() {
        return csFirstTen;
    }

    public AdapterPojo getCsSecondTen() {
        return csSecondTen;
    }

    public AdapterPojo getCsTotal() {
        return csTotal;
    }

    public AdapterPojo getDeaths() {
        return deaths;
    }

    public AdapterPojo getKills() {
        return kills;
    }

    public long getChampId() {
        return champId;
    }

    public String getChampName() {
        return champName;
    }
}
