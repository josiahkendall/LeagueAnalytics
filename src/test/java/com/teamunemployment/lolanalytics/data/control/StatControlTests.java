/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jek40
 */
public class StatControlTests {

    private DBHelper dbHelper;
    private StatControl statControl;
    
    /**
     * PRepare for our tests
     */
    public void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/lolanalytics", "root", "Idnw2bh2");
        boolean canConnect = dbHelper.Connect();
        statControl = new StatControl(dbHelper);
    }
    
    @Test
    public void TestThatWeCanSaveStats() {
        init();
        Stats stats = new Stats();
        stats.setAssists(1);
        stats.setChampLevel(2);
        stats.setCombatPlayerScore(3);
        stats.setDeaths(4);
        stats.setDoubleKills(5);
        stats.setFirstBloodAssist(true);
        stats.setFirstBloodKill(false);
        stats.setFirstInhibitorAssist(true);
        stats.setFirstInhibitorKill(false);
        stats.setGoldEarned(43);
        stats.setGoldSpent(43);
        stats.setId(43);
        stats.setInhibitorKills(43);
        stats.setItem0(1);
        stats.setItem1(2);
        stats.setItem2(3);
        stats.setItem3(3);
        stats.setItem4(3);
        stats.setItem5(3);
        stats.setItem6(3);
        stats.setKillingSprees(3);
        stats.setKills(3);
        stats.setLargestCriticalStrike(3);
        stats.setLargestKillingSpree(3);
        stats.setLargestMultiKill(3);
        stats.setMagicDamageDealt(3);
        stats.setMagicDamageDealtToChampions(3);
        stats.setMagicDamageTaken(4);
        stats.setMinionsKilled(4);
        stats.setNeutralMinionsKilled(4);
        stats.setNeutralMinionsKilledEnemyJungle(4);
        stats.setNeutralMinionsKilledTeamJungle(3);
        stats.setObjectivePlayerScore(4);
        stats.setPentaKills(3);
        stats.setPhysicalDamageDealt(3);
        stats.setPhysicalDamageDealtToChampions(3);
        stats.setPhysicalDamageTaken(3);
        stats.setQuadraKills(3);
        stats.setSightWardsBoughtInGame(3);
        stats.setTotalDamageDealt(3);
        stats.setTotalDamageDealtToChampions(3);
        stats.setTotalDamageTaken(3);
        stats.setTotalHeal(3);
        stats.setTotalPlayerScore(3);
        stats.setTotalScoreRank(4);
        stats.setTotalTimeCrowdControlDealt(4);
        stats.setTotalUnitsHealed(4);
        stats.setTowerKills(5);
        stats.setTripleKills(5);
        stats.setTrueDamageDealt(5);
        stats.setTrueDamageDealtToChampions(5);
        stats.setTrueDamageTaken(5);
        stats.setUnrealKills(5);
        stats.setVisionWardsBoughtInGame(5);
        stats.setWardsKilled(5);
        stats.setWardsPlaced(5);
        stats.setWinner(true);
        
        int id = statControl.saveStats(stats);
        
        Stats stat2 = statControl.getStats(id);
        Assert.assertTrue(stat2.getWardsPlaced() == 5);
    }
}
