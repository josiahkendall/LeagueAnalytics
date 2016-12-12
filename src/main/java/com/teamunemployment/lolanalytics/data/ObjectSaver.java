/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jek40
 */
public class ObjectSaver {
    
    private SummonerTableAccessor summonerTableAccessor;
    
    public ObjectSaver(SummonerTableAccessor summonerTableAccessor) {
        this.summonerTableAccessor = summonerTableAccessor;
    }
    
    /**
     * Save a summoner to the database.
     * @param summoner
     * @return 
     */
    public int SaveSummonerInfo(SummonerInfo summoner) {
        
        SummonerInfo previouslyExistingSummonerInfo = summonerTableAccessor.getSummoner(summoner.id);
        if (previouslyExistingSummonerInfo != null) {
            // update
        } else {
            return summonerTableAccessor.saveSummoner(summoner);
        }
//        try {
//            
//            return result;
//        } catch (SQLException ex) {
//            Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalStateException ex) {
//            Logger.getLogger(ObjectSaver.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        return -1;
    }
    
    public int SaveMatchDetails(MatchDetailsModel match) {
        return 0;
    } 
    
    //public int 

    private void assertTrue(boolean canConnect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
