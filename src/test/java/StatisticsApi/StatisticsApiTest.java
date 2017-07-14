/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatisticsApi;

import com.teamunemployment.lolanalytics.data.control.*;
import com.teamunemployment.lolanalytics.data.database.DBHelper;

/**
 *
 * @author Josiah Kendall
 */
public class StatisticsApiTest {
    
    private DBHelper dbHelper;
    private TimelineControl timelineControl;
    private ParticipantControl participantSummary;
    private RuneControl runeControl;
    private StatControl statControl;
    private MasteriesControl masteriesControl;
    private ParticipantControl participantControl;
    private ParticipantIdentityControl participantIdentityControl;
    private MatchDetailsControl matchDetailsControl;
    private MatchSummaryControl matchSummaryControl;
    private MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl;
    
    private void init() {
        dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        dbHelper.Connect();
        RuneControl runeControl = new RuneControl();
        BaseDeltaControl baseDeltaControl = new BaseDeltaControl(dbHelper);
        DeltaControl deltaControl =new DeltaControl(baseDeltaControl);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(dbHelper, deltaControl);

        participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
        participantIdentityControl = new ParticipantIdentityControl(dbHelper);
        matchDetailsControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
        matchSummaryControl = new MatchSummaryControl(dbHelper);
    }
    
//    @Test
//    public void TestThatWeCanGetCPMAverageForASummoner() {
//        // get cpm from db. Requires rows to exist
//        init();
//        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
//        double result = statisticsApi.GetCSFirst10MinAverageForRole(1542360, Role.MID);
//        Assert.assertTrue(result > 0);
//    }
//    
//    @Test
//    public void TestThatWeCanGetWinRatio() {
//        init();
//        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
//    }
//    
//    @Test
//    public void TestThatWeCanGetComparisonResult() {
//        init();
//        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
//        ComparisonResult result =  statisticsApi.GetCreepScoreFirst10MinComparison(7311653, "TOP", "PRESEASON2017");
//        System.out.println("My score is: " + result.myScore + "  Their score is: " + result.theirScore);
//        Assert.assertTrue(result.myScore > 0);
//    }
//    
//    @Test
//    public void TestThatWeCanGetHEadToHeadStats() {
//        init();
//        long matchId = 148317945;
//        String role = "TOP";
//        long summonerId = 7640794;
//        HeadToHeadStats stats = matchDetailsControl.fetchHeadToHeadStatsForMatch(matchId, role, summonerId);
//        System.out.println("Expected : 2455. Result: " + (stats.goldFirst10 *10));
//        Assert.assertTrue(stats.goldFirst10 == 2455);
//    }
//    
//    @Test
//    public void TestThatWeCalculateAveragesCorrectly() {
//        init();
//        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
//        GeneralStats generalStats = statisticsApi.FetchAndCalculateStatsForAUserAndRole("kloin", "Mid");
//        System.out.println("KDA: " + generalStats.KDA);
//        System.out.println("Kills: " + generalStats.averageKills);
//        System.out.println("deaths: " + generalStats.averageDeaths);
//        System.out.println("Enemy kills: " + generalStats.averageKillsEnemy);
//        System.out.println("Enemy Deaths: " + generalStats.averageDeathsEnemy);
//        System.out.println("My cs at 10: " + generalStats.averageCsEarlyGame);
//        System.out.println("Their cs at 10: " + generalStats.averageCsEarlyGameEnemy);
//
//
//    }
}
