package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall. An adapter pojo to be used on the recycler view on the client.
 */
public class AdapterPojo {
    public String title;
    public double enemyStats;
    public double friendlyStats;

    public AdapterPojo(String title, double enemyStats, double friendlyStats) {
        this.title = title;
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
    }
}
