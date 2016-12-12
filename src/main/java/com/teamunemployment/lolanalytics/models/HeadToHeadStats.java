/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.models;

/**
 *  
 * @author Josiah Kendall
 */
public class HeadToHeadStats {
    public long matchId;
    
    public int csTenMe;
    public int csTwentyMe;
    public int csTotalMe;
    
    public int csTenThem;
    public int csTwentyThem;
    public int csTotalThem;
    public long summonerIdMe;
    public long summonerIDThem;
    
    public int killsMe;
    public int killsThem;
    public int deathsMe;
    public int deathsThem;
    public int assistsMe;
    public int assistsThem;
    
    public double goldFirst10;
    public double goldSecond10;
    public double goldFirst10Them;
    public double goldSecond10Them;
}
