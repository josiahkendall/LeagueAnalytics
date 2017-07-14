package com.teamunemployment.lolanalytics.data.TestStats;

import com.teamunemployment.lolanalytics.data.Match;
import com.teamunemployment.lolanalytics.data.Role;
import com.teamunemployment.lolanalytics.models.*;
import com.teamunemployment.lolanalytics.models.Beans.*;
import com.teamunemployment.lolanalytics.models.Beans.Double;
import com.teamunemployment.lolanalytics.models.Beans.Long;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Josiah Kendall
 */
public class TestStats {
    public static final int TOP = 0;
    public static final int JUNGLE = 1;
    public static final int MID = 2;
    public static final int ADC = 3;
    public static final int SUPPORT = 4;
    private StatDefinition statDefinitions;

    public List<AdapterPojo> FetchTopStats() {
        AdapterPojo adapterPojo = new AdapterPojo(32, "Creep Score: First Ten Minutes", 65.75, 71.46, TOP, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(33, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, TOP, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(34, "Average Kills: First 10 Minutes", 1.94, 2.43, TOP, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(35, "Average Deaths : First 10 Minutes", 1.62, 2.31, TOP, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(36, "Average Assists : First 10 Minutes", 1.42, 1.43, TOP, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(37, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, TOP, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(38, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, TOP, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(39, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, TOP, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchMidStats() {
        AdapterPojo adapterPojo = new AdapterPojo(24, "Creep Score: First Ten Minutes", 65.75, 71.46, MID, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(52, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, MID, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(26, "Average Kills: First 10 Minutes", 1.94, 2.43, MID, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(27, "Average Deaths : First 10 Minutes", 1.62, 2.31, MID, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(28, "Average Assists : First 10 Minutes", 1.42, 1.43, MID, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(29, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, MID, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(30, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, MID, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(31, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, MID, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchAdcStats() {
        AdapterPojo adapterPojo = new AdapterPojo(1, "Creep Score: First Ten Minutes", 65.75, 71.46,ADC, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(2, "Creep Score: 10 - 20 Minutes", 71.65, 76.43, ADC, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(3, "Average Kills: First 10 Minutes", 1.94, 2.43, ADC, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(4, "Average Deaths : First 10 Minutes", 1.62, 2.31, ADC, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(5, "Average Assists : First 10 Minutes", 1.42, 1.43, ADC, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(6, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, ADC, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(7, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, ADC, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(8, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, ADC, -1);
        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchJungleStats() {
        AdapterPojo adapterPojo = new AdapterPojo(9, "Creep Score: First Ten Minutes", 46.25, 37.56, JUNGLE, -1);
        AdapterPojo adapterPojo1 = new AdapterPojo(10, "Enemy Jungle Minions Killed", 34.67, 46.34, JUNGLE, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(11, "Average Kills: First 10 Minutes", 2.34, 2.83, JUNGLE, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(12, "Average Deaths : First 10 Minutes", 1.62, 2.31, JUNGLE, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(13, "Average Assists : First 10 Minutes", 1.42, 1.43, JUNGLE, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(14, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, JUNGLE, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(15, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, JUNGLE, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(16, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, JUNGLE, -1);

        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo1);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public List<AdapterPojo> FetchSupportStats() {
        AdapterPojo adapterPojo = new AdapterPojo(17, "Crowd Control Dealt (Seconds) ", 3.41, 8.56, SUPPORT, -1);
        AdapterPojo adapterPojo2 = new AdapterPojo(18, "Average Kills: First 10 Minutes", 0.45, 0.83, SUPPORT, -1);
        AdapterPojo adapterPojo3 = new AdapterPojo(19, "Average Deaths : First 10 Minutes", 1.62, 2.31, SUPPORT, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(20, "Average Assists : First 10 Minutes", 1.42, 1.43, SUPPORT, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(21, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, SUPPORT, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(22, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, SUPPORT, -1);
        AdapterPojo adapterPojo7 = new AdapterPojo(23, "Average Assists : 10 - 20 Minutes", 1.42, 1.43, SUPPORT, -1);

        List<AdapterPojo> responseList = new ArrayList<>();
        responseList.add(adapterPojo);
        responseList.add(adapterPojo2);
        responseList.add(adapterPojo3);
        responseList.add(adapterPojo4);
        responseList.add(adapterPojo5);
        responseList.add(adapterPojo6);
        responseList.add(adapterPojo7);
        return responseList;
    }

    public com.teamunemployment.lolanalytics.models.Beans.Double FetchWinRate(String role) {
        switch (role) {
            case Role.TOP:
                return new Double(54.72);
            case Role.JUNGLE:
                return new Double(48.72);
            case Role.MID:
                return new Double(50.00);
            case Role.PLAYMAKER:
                return new Double(55.00);
            case Role.MARKSMAN:
                return new Double(45.00);
        }
        return new Double(0.0);
    }

    public List<MatchIdWrapper> GetMatchListForSummonerInRole(String role) {
        return GetTopList();
    }

    public PerformanceSummary getPerformanceSummaryForMatch() {
        AdapterPojo killsPojo = new AdapterPojo(18, "Average Kills: First 10 Minutes", 0.45, 0.83, SUPPORT, -1);
        AdapterPojo deathsPojo = new AdapterPojo(19, "Average Deaths : First 10 Minutes", 1.62, 2.31, SUPPORT, -1);
        AdapterPojo adapterPojo4 = new AdapterPojo(20, "Average Assists : First 10 Minutes", 1.42, 1.43, SUPPORT, -1);
        AdapterPojo adapterPojo5 = new AdapterPojo(21, "Average Kills: 10 - 20 Minutes", 1.94, 2.43, SUPPORT, -1);
        AdapterPojo adapterPojo6 = new AdapterPojo(22, "Average Deaths : 10 - 20 Minutes", 1.62, 2.31, SUPPORT, -1);

        PerformanceSummary performanceSummary = new PerformanceSummary(123, "Vi", killsPojo,deathsPojo ,adapterPojo4 , adapterPojo5, adapterPojo6);
        return performanceSummary;
    }

    public List<MatchIdWrapper> GetTopList() {
        ArrayList<MatchIdWrapper> beans = new ArrayList<>();
        MatchIdWrapper matchIdWrapper = new MatchIdWrapper();
        matchIdWrapper.setMatchId(12);
        matchIdWrapper.setRole(1);
        matchIdWrapper.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper2 = new MatchIdWrapper();
        matchIdWrapper2.setMatchId(12);
        matchIdWrapper2.setRole(1);
        matchIdWrapper2.setSummonerId(-1);

        MatchIdWrapper matchIdWrapper3 = new MatchIdWrapper();
        matchIdWrapper3.setMatchId(13);
        matchIdWrapper3.setRole(1);
        matchIdWrapper3.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper4 = new MatchIdWrapper();
        matchIdWrapper4.setMatchId(14);
        matchIdWrapper4.setRole(1);
        matchIdWrapper4.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper5 = new MatchIdWrapper();
        matchIdWrapper5.setMatchId(15);
        matchIdWrapper5.setRole(1);
        matchIdWrapper5.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper6 = new MatchIdWrapper();
        matchIdWrapper6.setMatchId(16);
        matchIdWrapper6.setRole(1);
        matchIdWrapper6.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper7 = new MatchIdWrapper();
        matchIdWrapper7.setMatchId(121);
        matchIdWrapper7.setRole(1);
        matchIdWrapper7.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper8 = new MatchIdWrapper();
        matchIdWrapper8.setMatchId(122);
        matchIdWrapper8.setRole(1);
        matchIdWrapper8.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper9 = new MatchIdWrapper();
        matchIdWrapper9.setMatchId(123);
        matchIdWrapper9.setRole(1);
        matchIdWrapper9.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper11= new MatchIdWrapper();
        matchIdWrapper11.setMatchId(124);
        matchIdWrapper11.setRole(1);
        matchIdWrapper11.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper12 = new MatchIdWrapper();
        matchIdWrapper12.setMatchId(125);
        matchIdWrapper12.setRole(1);
        matchIdWrapper12.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper13 = new MatchIdWrapper();
        matchIdWrapper13.setMatchId(126);
        matchIdWrapper13.setRole(1);
        matchIdWrapper13.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper14 = new MatchIdWrapper();
        matchIdWrapper14.setMatchId(127);
        matchIdWrapper14.setRole(1);
        matchIdWrapper14.setSummonerId(-1);
        MatchIdWrapper matchIdWrapper15 = new MatchIdWrapper();
        matchIdWrapper15.setMatchId(1278);
        matchIdWrapper15.setRole(1);
        matchIdWrapper15.setSummonerId(-1);

        beans.add(matchIdWrapper);
        beans.add(matchIdWrapper2);
        beans.add(matchIdWrapper3);
        beans.add(matchIdWrapper4);
        beans.add(matchIdWrapper5);
        beans.add(matchIdWrapper6);
        beans.add(matchIdWrapper7);
        beans.add(matchIdWrapper8);
        beans.add(matchIdWrapper9);
        beans.add(matchIdWrapper11);
        beans.add(matchIdWrapper12);
        beans.add(matchIdWrapper13);
        beans.add(matchIdWrapper14);
        beans.add(matchIdWrapper15);
        return beans;
    }

    public StatCollection getTestStatCollection(int id) {
        StatCollection statCollection = new StatCollection();

        StatSummary statSummary = new StatSummary();
        statSummary.setGoalvalue(12.3);
        statSummary.setHasGoal(false);
        statSummary.setStatName(getStatName(id));
        statSummary.setId(1);
        statCollection.setStatSummary(statSummary);

        ArrayList<StatPoint> statPoints = new ArrayList<>();
        StatPoint statPoint = new StatPoint();
        statPoint.setStatId(1);
        statPoint.setId(1);
        statPoint.setxValue(74.3);
        statPoint.setyValue(1);

        StatPoint statPoint2 = new StatPoint();
        statPoint.setStatId(2);
        statPoint.setId(2);
        statPoint.setxValue(71.5);
        statPoint.setyValue(2);

        StatPoint statPoint3 = new StatPoint();
        statPoint.setStatId(3);
        statPoint.setId(3);
        statPoint.setxValue(82.0);
        statPoint.setyValue(3);

        StatPoint statPoint4 = new StatPoint();
        statPoint.setStatId(4);
        statPoint.setId(4);
        statPoint.setxValue(74.3);
        statPoint.setyValue(4);

        StatPoint statPoint5 = new StatPoint();
        statPoint.setStatId(5);
        statPoint.setId(5);
        statPoint.setxValue(42.3);
        statPoint.setyValue(5);

        StatPoint statPoint6 = new StatPoint();
        statPoint.setStatId(6);
        statPoint.setId(6);
        statPoint.setxValue(73.3);
        statPoint.setyValue(6);

        StatPoint statPoint7 = new StatPoint();
        statPoint.setStatId(7);
        statPoint.setId(7);
        statPoint.setxValue(62.3);
        statPoint.setyValue(7);

        StatPoint statPoint8 = new StatPoint();
        statPoint.setStatId(8);
        statPoint.setId(8);
        statPoint.setxValue(67.3);
        statPoint.setyValue(8);

        StatPoint statPoint9 = new StatPoint();
        statPoint.setStatId(9);
        statPoint.setId(9);
        statPoint.setxValue(74.3);
        statPoint.setyValue(9);

        StatPoint statPoint0 = new StatPoint();
        statPoint.setStatId(10);
        statPoint.setId(10);
        statPoint.setxValue(67.3);
        statPoint.setyValue(10);

        StatPoint statPoint11 = new StatPoint();
        statPoint.setStatId(11);
        statPoint.setId(11);
        statPoint.setxValue(71.3);
        statPoint.setyValue(11);

        StatPoint statPoint12 = new StatPoint();
        statPoint.setStatId(12);
        statPoint.setId(12);
        statPoint.setxValue(78.3);
        statPoint.setyValue(12);

        statPoints.add(statPoint);
        statPoints.add(statPoint2);
        statPoints.add(statPoint3);
        statPoints.add(statPoint4);
        statPoints.add(statPoint5);
        statPoints.add(statPoint6);
        statPoints.add(statPoint7);
        statPoints.add(statPoint8);
        statPoints.add(statPoint9);
        statPoints.add(statPoint0);
        statPoints.add(statPoint11);
        statPoints.add(statPoint12);

        statCollection.setCollection(statPoints);

        return statCollection;
    }

    private String getStatName(int id) {
        switch (id) {
            case 0:
                return "Creep Score";
            case 1:
                return "Creep Score";
            case 2:
                return "Creep Score";
            case 3:
                return "Kills";
            case 4:
                return "Kills";
            case 5:
                return "Kills";
            case 6:
                return "Deaths";
            case 7:
                return "Deaths";
            case 8:
                return "Deaths";
            case 9:
                return "Damage Taken";
            case 10:
                return "Damage Taken";
            case 11:
                return "Damage Taken";
            case 12 :
                return "Damage Dealt";
            case 13:
                return "Damage Dealt";
            case 14:
                return "Damage Dealt";
        }
        return null;
    }

    private String getSubName(int id) {
        switch(id) {
            case 0 :
                return "Early Game";
            case 1 :
                return "Mid Game";
            case 2 :
                return "Late Game";
            case 3 :
                return "Early Game";
            case 4 :
                return "Mid Game";
            case 5 :
                return "Late Game";
            case 6 :
                return "Early Game";
            case 7 :
                return "Mid Game";
            case 8 :
                return "Late Game";
            case 9 :
                return "Early Game";
            case 10 :
                return "Mid Game";
            case 11 :
                return "Late Game";
        }
        return "Early Game";
    }

    public StatDefinitionWrapper getStatDefinitions() {
        StatDefinitionWrapper statDefinitionWrapper = new StatDefinitionWrapper();
        ArrayList<StatDefinition> statDefinitions1 = new ArrayList<>();

        StatDefinition statDefinition = new StatDefinition();
        StatDefinition statDefinition1 = new StatDefinition();
        StatDefinition statDefinition2 = new StatDefinition();
        StatDefinition statDefinition3 = new StatDefinition();
        StatDefinition statDefinition4 = new StatDefinition();
        StatDefinition statDefinition5 = new StatDefinition();
        StatDefinition statDefinition6 = new StatDefinition();
        StatDefinition statDefinition7 = new StatDefinition();
        StatDefinition statDefinition8 = new StatDefinition();
        StatDefinition statDefinition9 = new StatDefinition();
        StatDefinition statDefinition10 = new StatDefinition();
        StatDefinition statDefinition11 = new StatDefinition();
        StatDefinition statDefinition12 = new StatDefinition();

        statDefinition1.setStatName("CS first 10 min");
        statDefinition2.setStatName("CS Second 10 min");
        statDefinition3.setStatName("Deaths first 10");
        statDefinition4.setStatName("kills second 10");
        statDefinition5.setStatName("kills second 10");
        statDefinition6.setStatName("kills second 10");
        statDefinition7.setStatName("kills second 10");
        statDefinition8.setStatName("kills second 10");
        statDefinition9.setStatName("kills second 10");
        statDefinition10.setStatName("kills second 10");
        statDefinition11.setStatName("kills second 10");
        statDefinition12.setStatName("kills second 10");
        statDefinition.setStatName("Kills first 10");

        statDefinition.setStatId(0);
        statDefinition1.setStatId(1);
        statDefinition2.setStatId(2);
        statDefinition3.setStatId(3);
        statDefinition4.setStatId(4);
        statDefinition5.setStatId(5);
        statDefinition6.setStatId(6);
        statDefinition7.setStatId(7);
        statDefinition8.setStatId(8);
        statDefinition9.setStatId(9);
        statDefinition10.setStatId(10);
        statDefinition11.setStatId(11);
        statDefinition12.setStatId(12);

        statDefinitions1.add(statDefinition);
        statDefinitions1.add(statDefinition1);
        statDefinitions1.add(statDefinition2);
        statDefinitions1.add(statDefinition3);
        statDefinitions1.add(statDefinition4);
        statDefinitions1.add(statDefinition5);
        statDefinitions1.add(statDefinition6);
        statDefinitions1.add(statDefinition7);
        statDefinitions1.add(statDefinition8);
        statDefinitions1.add(statDefinition9);
        statDefinitions1.add(statDefinition10);
        statDefinitions1.add(statDefinition11);
        statDefinitions1.add(statDefinition12);

        statDefinitionWrapper.setDefinitions(statDefinitions1);
        return statDefinitionWrapper;
    }
}
