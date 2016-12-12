/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.stats.Stats;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Josiah Kendall
 */
public class StatControl {
    
    private DBHelper dbHelper;
    
    public StatControl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public int saveStats(Stats stats) {
        try {
        String queryString = String.format(
         "Insert into stats (Winner," +
"    ChampLevel," +
"    Item0," +
"    Item1," +
"    Item2," +
"    Item3," +
"    Item4," +
"    Item5," +
"    Item6," +
"    Kills," +
"    DoubleKills," +
"    TripleKills," +
"    QuadraKills," +
"    PentaKills," +
"    UnrealKills," +
"    LargestKillingSpree," +
"    Deaths," +
"    Assists," +
"    TotalDamageDealt," +
"    TotalDamageDealtToChampions," +
"    TotalDamageTaken," +
"    LargestCriticalStrike," +
"    TotalHeal," +
"    MinionsKilled," +
"    NeutralMinionsKilled," +
"    NeutralMinionsKilledTeamJungle," +
"    NeutralMinionsKilledEnemyJungle," +
"    GoldEarned," +
"    GoldSpent," +
"    CombatPlayerScore," +
"    ObjectivePlayerScore," +
"    TotalPlayerScore," +
"    TotalScoreRank," +
"    MagicDamageDealtToChampions," +
"    PhysicalDamageDealtToChampions," +
"    TrueDamageDealtToChampions," +
"    VisionWardsBoughtInGame," +
"    SightWardsBoughtInGame," +
"    MagicDamageDealt," +
"    PhysicalDamageDealt," +
"    TrueDamageDealt," +
"    MagicDamageTaken," +
"    PhysicalDamageTaken," +
"    TrueDamageTaken," +
"    FirstBloodKill," +
"    FirstBloodAssist," +
"    FirstTowerKill," +
"    FirstTowerAssist," +
"    FirstInhibitorKill," +
"    FirstInhibitorAssist," +
"    InhibitorKills," +
"    TowerKills," +
"    WardsPlaced," +
"    WardsKilled," +
"    LargestMultiKill," +
"    KillingSprees," +
"    TotalUnitsHealed," +
"    TotalTimeCrowdControlDealt) values ("
                 //+ "%b,"
                 + "%b,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%b,"
                 + "%b,"
                 + "%b,"
                 + "%b,"
                 + "%b,"
                 + "%b,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d,"
                 + "%d)",
         stats.getWinner(),
         stats.getChampLevel(),
         stats.getItem0(),
         stats.getItem1(),
         stats.getItem2(),
         stats.getItem3(),
         stats.getItem4(),
         stats.getItem5(),
         stats.getItem6(),
         stats.getKills(),
         stats.getDoubleKills(),
         stats.getTripleKills(),
         stats.getQuadraKills(),
         stats.getPentaKills(),
         stats.getUnrealKills(),
         stats.getLargestKillingSpree(),
         stats.getDeaths(),
         stats.getAssists(),
         stats.getTotalDamageDealt(),
         stats.getTotalDamageDealtToChampions(),
         stats.getTotalDamageTaken(),
         stats.getLargestCriticalStrike(),
         stats.getTotalHeal(),
         stats.getMinionsKilled(),
         stats.getNeutralMinionsKilled(),
         stats.getNeutralMinionsKilledTeamJungle(),
         stats.getNeutralMinionsKilledEnemyJungle(),
         stats.getGoldEarned(),
         stats.getGoldSpent(),
         stats.getCombatPlayerScore(),
         stats.getObjectivePlayerScore(),
         stats.getTotalPlayerScore(),
         stats.getTotalScoreRank(),
         stats.getMagicDamageDealtToChampions(),
         stats.getPhysicalDamageDealtToChampions(),
         stats.getTrueDamageDealtToChampions(),
         stats.getVisionWardsBoughtInGame(),
         stats.getSightWardsBoughtInGame(),
         stats.getMagicDamageDealt(),
         stats.getPhysicalDamageDealt(),
         stats.getTrueDamageDealt(),
         stats.getMagicDamageTaken(),
         stats.getPhysicalDamageTaken(),
         stats.getTrueDamageTaken(),
         stats.getFirstBloodKill(),
         stats.getFirstBloodAssist(),
         stats.getFirstTowerKill(),
         stats.getFirstTowerAssist(),
         stats.getFirstInhibitorKill(),
         stats.getFirstInhibitorAssist(),
         stats.getInhibitorKills(),
         stats.getTowerKills(),
         stats.getWardsPlaced(),
         stats.getWardsKilled(),
         stats.getLargestMultiKill(),
         stats.getKillingSprees(),
         stats.getTotalUnitsHealed(),
         stats.getTotalTimeCrowdControlDealt());
            int result = dbHelper.ExecuteSqlScript(queryString);
            return result;
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        
        return -1;
    }
    
    public Stats getStats(int statId) {
         try {
            String query = "SELECT * from stats WHERE Id = " + statId;
            ResultSet resultSet = dbHelper.ExecuteSqlQuery(query);
            if (resultSet.next()) {
                Stats stats  = new Stats();
                stats.setAssists(resultSet.getInt("Assists"));
                stats.setChampLevel(resultSet.getInt("ChampLevel"));
                stats.setCombatPlayerScore(resultSet.getInt("CombatPlayerScore"));
                stats.setDeaths(resultSet.getInt("Deaths"));
                stats.setDoubleKills(resultSet.getInt("DoubleKills"));
                stats.setFirstBloodAssist(resultSet.getBoolean("FirstBloodAssist"));
                stats.setFirstBloodKill(resultSet.getBoolean("FirstBloodKill"));
                stats.setFirstInhibitorAssist(resultSet.getBoolean("FirstInhibitorAssist"));
                stats.setFirstInhibitorKill(resultSet.getBoolean("FirstInhibitorKill"));
                stats.setGoldEarned(resultSet.getInt("GoldEarned"));
                stats.setGoldSpent(resultSet.getInt("GoldSpent"));
                stats.setId(resultSet.getInt("Id"));
                stats.setInhibitorKills(resultSet.getInt("InhibitorKills"));
                stats.setItem0(resultSet.getInt("Item0"));
                stats.setItem1(resultSet.getInt("Item1"));
                stats.setItem2(resultSet.getInt("Item2"));
                stats.setItem3(resultSet.getInt("Item3"));
                stats.setItem4(resultSet.getInt("Item4"));
                stats.setItem5(resultSet.getInt("Item5"));
                stats.setItem6(resultSet.getInt("Item6"));
                stats.setKillingSprees(resultSet.getInt("KillingSprees"));
                stats.setKills(resultSet.getInt("Kills"));
                stats.setLargestCriticalStrike(resultSet.getInt("LargestCriticalStrike"));
                stats.setLargestKillingSpree(resultSet.getInt("LargestKillingSpree"));
                stats.setLargestMultiKill(resultSet.getInt("LargestMultiKill"));
                stats.setMagicDamageDealt(resultSet.getInt("MagicDamageDealt"));
                stats.setMagicDamageDealtToChampions(resultSet.getInt("MagicDamageDealtToChampions"));
                stats.setMagicDamageTaken(resultSet.getInt("MagicDamageTaken"));
                stats.setMinionsKilled(resultSet.getInt("MinionsKilled"));
                stats.setNeutralMinionsKilled(resultSet.getInt("NeutralMinionsKilled"));
                stats.setNeutralMinionsKilledEnemyJungle(resultSet.getInt("NeutralMinionsKilledEnemyJungle"));
                stats.setNeutralMinionsKilledTeamJungle(resultSet.getInt("NeutralMinionsKilledTeamJungle"));
                stats.setObjectivePlayerScore(resultSet.getInt("ObjectivePlayerScore"));
                stats.setPentaKills(resultSet.getInt("PentaKills"));
                stats.setPhysicalDamageDealt(resultSet.getInt("PhysicalDamageDealt"));
                stats.setPhysicalDamageDealtToChampions(resultSet.getInt("PhysicalDamageDealtToChampions"));
                stats.setPhysicalDamageTaken(resultSet.getInt("PhysicalDamageTaken"));
                stats.setQuadraKills(resultSet.getInt("QuadraKills"));
                stats.setSightWardsBoughtInGame(resultSet.getInt("SightWardsBoughtInGame"));
                stats.setTotalDamageDealt(resultSet.getInt("TotalDamageDealt"));
                stats.setTotalDamageDealtToChampions(resultSet.getInt("TotalDamageDealtToChampions"));
                stats.setTotalDamageTaken(resultSet.getInt("TotalDamageTaken"));
                stats.setTotalHeal(resultSet.getInt("TotalHeal"));
                stats.setTotalPlayerScore(resultSet.getInt("TotalPlayerScore"));
                stats.setTotalScoreRank(resultSet.getInt("TotalScoreRank"));
                stats.setTotalTimeCrowdControlDealt(resultSet.getInt("TotalTimeCrowdControlDealt"));
                stats.setTotalUnitsHealed(resultSet.getInt("TotalUnitsHealed"));
                stats.setTowerKills(resultSet.getInt("TowerKills"));
                stats.setTripleKills(resultSet.getInt("TripleKills"));
                stats.setTrueDamageDealt(resultSet.getInt("TrueDamageDealt"));
                stats.setTrueDamageDealtToChampions(resultSet.getInt("TrueDamageDealtToChampions"));
                stats.setTrueDamageTaken(resultSet.getInt("TrueDamageTaken"));
                stats.setUnrealKills(resultSet.getInt("UnrealKills"));
                stats.setVisionWardsBoughtInGame(resultSet.getInt("VisionWardsBoughtInGame"));
                stats.setWardsKilled(resultSet.getInt("WardsKilled"));
                stats.setWardsPlaced(resultSet.getInt("WardsPlaced"));
                stats.setWinner(resultSet.getBoolean("Winner"));
                return stats;
            }
        } catch (SQLException | IllegalStateException ex) {
            System.out.println("An Error occued parsing the result set. Stack trace follows\n " + ex.getMessage());
        }
        
        return null;
    }
}
