/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control.sync;

/**
 * The sync control. 
 * @author Josiah Kendall.
 */
public class Control {
    
    /**
     * Fetch all the match details for a user, and save them to our database.
     * If a sync has already been performed for a user, this method will update
     * the match details database with any new matches.
     * @param userId The riot id for a summoner.
     */
    public void SyncMatchDetailsForUser(long userId) {
        // Fetch summaries.
        //
    }
    
    
}
