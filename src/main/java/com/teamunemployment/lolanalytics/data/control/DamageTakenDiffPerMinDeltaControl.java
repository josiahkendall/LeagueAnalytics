/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control;

import com.teamunemployment.lolanalytics.models.stats.BaseDeltas;

import java.sql.ResultSet;

/**
 * 
 * @author jek40
 */
public class DamageTakenDiffPerMinDeltaControl {


//    public BaseDeltas getGPMD(int csDPMDId) {
//        try {
//            String query = "SELECT * from  WHERE Id = " + csDPMDId;
//            ResultSet resultSet = dbhelper.ExecuteSqlQuery(query);
//            if (resultSet.next()) {
//                CsDiffPerMinDeltas csdpmd = new CsDiffPerMinDeltas();
//                csdpmd.setId(csDPMDId);
//                csdpmd.setZeroToTen(resultSet.getDouble("ZeroToTen"));
//                csdpmd.setZeroToTen(resultSet.getDouble("TenToTwenty"));
//                csdpmd.setZeroToTen(resultSet.getDouble("TwentyToThirty"));
//                csdpmd.setZeroToTen(resultSet.getDouble("ThirtyToEnd"));
//
//                return csdpmd;
//            }
//        } catch (SQLException | IllegalStateException ex) {
//            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }

//    /**
//     * Update a CPMD object that already exists.
//     * @param cpmd the CreepsPerMinDelta object to update.
//     * @return The result integer.
//     */
//    public int updateGPMD(CsDiffPerMinDeltas csdpm) {
//
//        String queryString = String.format(
//                "UPDATE csdiffpermindeltas set "
//                        + "ZeroToTen = %d, "
//                        + "TenToTwenty = %d, "
//                        + "TwentyToThirty = %d "
//                        + "WHERE Id = %d",
//                csdpm.getZeroToTen(),
//                csdpm.getTenToTwenty(),
//                csdpm.getTwentyToThirty(),
//                csdpm.getThirtyToEnd(),
//                csdpm.getId()
//        );
//        try {
//            int result = dbhelper.ExecuteSqlScript(queryString);
//            return result;
//        } catch (SQLException | IllegalStateException ex) {
//            Logger.getLogger(SummonerTableAccessor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return 0;
//    }
//
//    /**
//     * Fetch the difference (in creeps per minutes) between the selected summoner and the enemy. If the result is negative,
//     * the enemy scored better.
//     * @return The difference
//     */
//    public double LoadDeltaAverage(long summonerId, String season, String role, String lane, int gameStage) {
//
//        String gameStageString = "zeroToTen";
//        switch(gameStage) {
//            case 1:
//                gameStageString = "tenToTwenty";
//                break;
//            case 2:
//                gameStageString = "twentyToThirty";
//        }
//        String queryString = "select avg(csdiffpermindeltas."+gameStageString+") " +
//                "from participantSummary " +
//                "join timeline on timeline.id = participantsummary.timelineId " +
//                "join csdiffpermindeltas on csdiffpermindeltas.id = timeline.csDiffPerMinDeltaId " +
//                "where participantsummary.summonerId = "+summonerId+ " and " +
//                "role = '"+role+"' and lane = '"+lane + "' and season = '" + season + " '"+
//                "order by csdiffpermindeltas.id desc\n" +
//                "         limit 20";
//
//        try {
//            ResultSet resultSet = dbhelper.ExecuteSqlQuery(queryString);
//            if (resultSet == null) {
//                throw new IllegalStateException("Database Error: Query returned no data.");
//            }
//            if (resultSet.next()) {
//                double average = resultSet.getDouble("avg(csdiffpermindeltas."+gameStageString+")");
//                return average;
//            }
//        } catch (SQLException | IllegalStateException ex) {
//            throw new IllegalStateException(ex.getLocalizedMessage());
//        }
//        return 0;
//    }
}
