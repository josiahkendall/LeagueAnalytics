package com.teamunemployment.lolanalytics.models;

/**
 * @author Josiah Kendall.
 *
 * An adapter pojo to be used on the recycler view on the client.
 */
public class AdapterPojo {

    public int id;
    public String title;
    public double enemyStats;
    public double friendlyStats;
    public int role;
    public long summonerId;

    public AdapterPojo(int id, String title, double enemyStats, double friendlyStats, int role, long summonerId) {
        this.title = title;
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
        this.id = id;
        this.role = role;
        this.summonerId = summonerId;
    }
}
