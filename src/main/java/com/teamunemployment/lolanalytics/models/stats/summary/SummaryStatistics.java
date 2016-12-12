/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.models.stats.summary;

/**
 *
 * @author Josiah Kendall.
 */
public class SummaryStatistics {
    private SummaryModel summaryModel;
    
    public SummaryStatistics(SummaryModel summaryModel) {
        this.summaryModel = summaryModel;
    }
    /**
     * Calculate the summoners win rate based on the given parameters.
     * @param summonerId The id of the summoner we are calculating the win rate for.
     * @param season The ranked season we are interested in.
     * @param champId The champion we want to calculate the win rate for.
     * @return The win rate, as a 2 decimal place number.
     */
    public Double CalculateWinRate(long summonerId, String season, int champId) {
        // Need to get all the number of wins where criteria applies.
        // Need to get all the number of losses where criteria applies.
        
        int wins = summaryModel.GetAllWins(summonerId, season, champId);
        int losses = summaryModel.GetAllLosses(summonerId, season, champId);
        
        Double winsDouble = new Double(wins);
        Double lossesDouble = new Double(losses);
        double result = winsDouble/ (winsDouble + lossesDouble);
        return result * 100;   
    }
}
