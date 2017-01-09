/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalyticsv3.RestApi;

/**
 *
 * @author jek40
 */
public class Result {
    public String title;
    public double enemyStats;
    public double friendlyStats;
    
    public Result (String title, double enemyStats, double friendlyStats) {
        this.title = title;
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
    }
    /**
     * JsonString format of our result object.
     * @return 
     */
    public String toString() {
        return "{"
                + "\"title\" : \""+ title + "\","
                + "\"enemyStats\" : " + enemyStats + ","
                + "\"friendlyStats\" : " + friendlyStats + "}";
    }

}
