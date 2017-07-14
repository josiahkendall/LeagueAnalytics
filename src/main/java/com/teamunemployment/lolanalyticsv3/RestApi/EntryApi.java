/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalyticsv3.RestApi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.gson.GsonBuilder;
import com.teamunemployment.lolanalytics.data.SummonerTableAccessor;
import com.teamunemployment.lolanalytics.data.TestStats.TestStats;
import com.teamunemployment.lolanalytics.data.control.*;
import com.teamunemployment.lolanalytics.data.control.Summoner.SummonerInfoControl;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.models.*;

import java.io.IOException;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * This is the endpoint that we expose on the app engine. This is the client facing access
 * to the riot api servers.
 * @author jek40
 */

/** An endpoint class we are exposing */
@Api(name = "myApi",
    version = "v1",
    namespace = @ApiNamespace(ownerDomain = "lolanalytics.teamunemployment.com",
        ownerName = "lolanalytics.teamunemployment.com",
        packagePath = ""))

public class EntryApi {

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
    private SummonerInfoControl summonerInfoControl;
    private MatchParticipantSummaryJunctionControl matchParticipantSummaryJunctionControl;
    private SummonerTableAccessor summonerTableAccessor;
    
    private void init() {
        dbHelper = new DBHelper();
        // Create the connection to our database.
        dbHelper.Connect();
        RuneControl runeControl = new RuneControl();
        BaseDeltaControl baseDeltaControl = new BaseDeltaControl(dbHelper);
        DeltaControl deltaControl =new DeltaControl(baseDeltaControl);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(dbHelper,deltaControl);
        summonerTableAccessor = new SummonerTableAccessor(dbHelper);
        summonerInfoControl = new SummonerInfoControl();
        participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
        participantIdentityControl = new ParticipantIdentityControl(dbHelper);
        matchParticipantSummaryJunctionControl = new MatchParticipantSummaryJunctionControl(dbHelper);
        matchDetailsControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
        matchSummaryControl = new MatchSummaryControl(dbHelper);
    }

    @ApiMethod(name = "RefreshUserData", httpMethod = ApiMethod.HttpMethod.GET, path = "Refresh/{SummonerId}")
    public StringResponse RefreshUserData(@Named("SummonerId") long summonerId) {
        init();

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://oce.api.pvp.net/api/lol/oce/v2.2")
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setClient(new UrlFetchClient());

        RestAdapter serviceAdapter = builder.build();

        RiotMatchControl riotMatchControl = new RiotMatchControl(participantControl, participantIdentityControl, dbHelper,
                matchParticipantSummaryJunctionControl, serviceAdapter, matchSummaryControl, matchDetailsControl);
        try {
            riotMatchControl.UpdateMatchesForASummoner(summonerId);
        } catch (InterruptedException ex) {
            // TODO fix this.
        }

        // TODO and this
        return new StringResponse();
    }


    
    /**
     * Fetch the head to head stats for a user in a specified role.
     * @param summonerName The name of the user that we are interested in.
     * @param role The role that the user is interested in.
     * @return  The head to head statistics.
     */
    @ApiMethod(name = "FetchHeadToHeadStatsForAUser", httpMethod = ApiMethod.HttpMethod.GET, path = "SummonerInfo/HeadToHead/{summonerName}/{role}")
    public GeneralStats FetchHeadToHeadStatsForAUser(@Named("summonerName") String summonerName, @Named("role") String role) {
        
        // Not really sure if i should init this evertime.
        init();
        
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl, summonerInfoControl, summonerTableAccessor);
        GeneralStats generalStats = statisticsApi.FetchAndCalculateStatsForAUserAndRole(summonerName, role);
        
        dbHelper.Disconnect();
        
        return generalStats;
    }

    /**
     * Fetch summoner info based on the name of the summoner that we are looking for.
     * @param summonerName The summoner for whom we are looking for data.
     * @return
     * @throws IOException 
     */
    @ApiMethod(name = "SummonerInfo",httpMethod = ApiMethod.HttpMethod.GET,path = "SummonerInfo/{summonerName}" )
    public SummonerInfo FetchSummonerInfoByName(@Named("summonerName") String summonerName) throws IOException {
        summonerInfoControl = new SummonerInfoControl();
        SummonerInfo info = summonerInfoControl.FetchSummonerInfo(summonerName);
        return info;
    }
    
    @ApiMethod(name = "FetchMatchList", httpMethod = ApiMethod.HttpMethod.GET, path = "FetchMatchList/{summonerId}")
    public List<MatchSummary> FetchMatchList(@Named("summonerId") int summonerId) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://oce.api.pvp.net/api/lol/oce/v2.2")
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setClient(new OkClient());
                  
        RestAdapter serviceAdapter = builder.build();

        MatchService service = serviceAdapter.create(MatchService.class);
        MatchList matchlist = service.getMatchListForSummoner(summonerId);
        
        return matchlist.matches;
    }
    
    @ApiMethod(name = "FetchMatchDetails", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchMatchDetails/{matchId}")
    public MatchDetailsModel FetchMatchDetails(@Named("matchId") long matchId) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://oce.api.pvp.net/api/lol/oce/v2.2")
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setClient(new OkClient()); //UrlFetchClient
                  
        RestAdapter serviceAdapter = builder.build();
        MatchService service = serviceAdapter.create(MatchService.class);
        MatchDetailsModel match = service.getMatchSummary(matchId);
        
        return match;
    } 
    
    @ApiMethod(name = "FetchTopStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchTopStats/{summonerId}")
    public List<AdapterPojo> FetchTopStats(@Named("summonerId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.FetchTopStats();
        }

        init();
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl, summonerInfoControl, summonerTableAccessor);
        GeneralStats stats = statisticsApi.FetchAndCalculateStatsForAUserAndLane(userId, "TOP");
        
        Result creepsFirst10 = new Result("Creep Score First Ten Minutes", stats.averageCsEarlyGameEnemy, stats.averageCsEarlyGame);
        Result killsFirst10 = new Result("Kills First Ten Minutes", stats.averageKillsEnemy, stats.averageKills);
        Result deathsFirst10 = new Result("Deaths First Ten Minutes", stats.averageDeathsEnemy, stats.averageDeaths);
        String result = "["
                + creepsFirst10.toString() + ","
                + killsFirst10.toString() + ","
                + deathsFirst10.toString() + "]";
        StringResponse stringResponse = new StringResponse();
        stringResponse.setData(result);
        return null;
    }

    @ApiMethod(name = "FetchMidStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchMidStats/{userId}")
    public List<AdapterPojo> FetchMidStats(@Named("userId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.FetchMidStats();
        }
        
        init();
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl, summonerInfoControl, summonerTableAccessor);
        GeneralStats stats = statisticsApi.FetchAndCalculateStatsForAUserAndLane(userId, "MIDDLE");

        Result creepsFirst10 = new Result("Creep Score First Ten Minutes", stats.averageCsEarlyGameEnemy, stats.averageCsEarlyGame);
        Result killsFirst10 = new Result("Kills First Ten Minutes", stats.averageKillsEnemy, stats.averageKills);
        Result deathsFirst10 = new Result("Deaths First Ten Minutes", stats.averageDeathsEnemy, stats.averageDeaths);
        String result = "["
                + creepsFirst10.toString() + ","
                + killsFirst10.toString() + ","
                + deathsFirst10.toString() + "]";
        StringResponse stringResponse = new StringResponse();
        stringResponse.setData(result);
        return null;
    }

    @ApiMethod(name = "FetchAdcStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchAdcStats/{userId}")
    public List<AdapterPojo> FetchADCStats(@Named("userId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.FetchAdcStats();
        }
        init();
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl, summonerInfoControl, summonerTableAccessor);
        GeneralStats stats = statisticsApi.FetchAndCalculateStatsForAUserAndLaneAndRole(userId, "BOTTOM", "DUO_CARRY");

        Result creepsFirst10 = new Result("Creep Score First Ten Minutes", stats.averageCsEarlyGameEnemy, stats.averageCsEarlyGame);
        Result killsFirst10 = new Result("Kills First Ten Minutes", stats.averageKillsEnemy, stats.averageKills);
        Result deathsFirst10 = new Result("Deaths First Ten Minutes", stats.averageDeathsEnemy, stats.averageDeaths);
        String result = "["
                + creepsFirst10.toString() + ","
                + killsFirst10.toString() + ","
                + deathsFirst10.toString() + "]";
        StringResponse stringResponse = new StringResponse();
        stringResponse.setData(result);
        return null;
    }

    @ApiMethod(name = "FetchJungleStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchJungleStats/{UserId}")
    public List<AdapterPojo> FetchJungleStats(@Named("UserId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.FetchJungleStats();
        }
        return null;
    }

    @ApiMethod(name = "FetchSupportStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchSupportStats/{UserId}")
    public List<AdapterPojo> FetchSupportStats(@Named("UserId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.FetchSupportStats();
        }
        //
        return null;
    }

    @ApiMethod(name = "FetchWinRateForSummonnerInRole", httpMethod = ApiMethod.HttpMethod.GET, path = "FetchWinRate/{UserId}/{Role}")
    public com.teamunemployment.lolanalytics.models.Beans.Double FetchWinRateForSummonerInRole(@Named("UserId") long userId, @Named("Role") String role) {
        TestStats testStats = new TestStats();
        return testStats.FetchWinRate(role);
    }

    @ApiMethod(name = "FetchMatchListForSummonerInRole", httpMethod = ApiMethod.HttpMethod.GET, path = "FetchMatchList/{UserId}/{Role}")
    public List<MatchIdWrapper> FetchMatchListForSummonerInRole(@Named("UserId") long userId, @Named("Role") String role) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.GetMatchListForSummonerInRole(role);
        }
        return null;
    }

    @ApiMethod(name = "FetchPerformanceForUserInASpecificRoleInASpecificMatch", httpMethod = ApiMethod.HttpMethod.GET, path = "FetchPerformance/{MatchId}/{UserId}")
    public PerformanceSummary FetchPerformanceSummary(@Named("MatchId") long matchId, @Named("UserId") long userId) {
        if (userId == -1) {
            TestStats testStats = new TestStats();
            return testStats.getPerformanceSummaryForMatch();
        }

        return null;
    }

    @ApiMethod(name = "GetStatHistory", httpMethod = ApiMethod.HttpMethod.GET, path = "GetStatHistory/{Role}/{SummonerId}/{StatId}")
    public StatCollection GetStatHistory(@Named("Role") int role, @Named("SummonerId") long summonerId, @Named("StatId") int statId) {
        if (summonerId == -1) {
            TestStats testStats = new TestStats();
            return testStats.getTestStatCollection(statId);
        }

        return null;
    }

    @ApiMethod(name ="FetchStatDefinitions", httpMethod = ApiMethod.HttpMethod.GET, path = "FetchStatDefinitions/{SummonerId}/{Role}")
    public StatDefinitionWrapper FetchStatDefinitions(@Named("SummonerId") long summonerId, @Named("Role") int role) {
        if (summonerId == -1) {
            TestStats testStats = new TestStats();
            return  testStats.getStatDefinitions();
        }
        return null;
    }
}
