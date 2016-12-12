/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.models;

/**
 *
 * @author jek40
 */
public class GeneralStats {
    
    // Creeps
    public double averageCsEarlyGame;
    public double averageCsEarlyGameEnemy;
    public double averageCsMidGame;    
    public double averageCsMidGameEnemy;
    public double averageTotalCs;
    public double averageTotalCsEnemy;
    
    // Damage dealt
    public int damageDealtEarlyGame;
    public int damageDealtEarlyGameEnemy;

    public int damageDealtMidGame;
    public int damageDealtMidGameEnemy;

    public double totalDamageDealt;
    public double totalDamageDealtEnemy;
    
    // Damage taken;
    public double damageTakenEarlyGame;
    public double damageTakenMidGame;
    public double damagetakenTotal;
    
    // KDA
    public double KDA;
    public double averageKills;
    public double averageKillsEnemy;
    public double averageDeaths;
    public double averageDeathsEnemy;
    public double averageAssits;
    public double averageAssitsEnemy;
}
