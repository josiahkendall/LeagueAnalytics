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
import com.google.appengine.api.utils.SystemProperty;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.teamunemployment.lolanalytics.data.control.CreepsPerMinDeltaControl;
import com.teamunemployment.lolanalytics.data.control.CsDiffPerMinDeltasControl;
import com.teamunemployment.lolanalytics.data.control.GoldPerMinDeltasControl;
import com.teamunemployment.lolanalytics.data.control.MasteriesControl;
import com.teamunemployment.lolanalytics.data.control.MatchDetailsControl;
import com.teamunemployment.lolanalytics.data.control.MatchParticipantSummaryJunctionControl;
import com.teamunemployment.lolanalytics.data.control.MatchSummaryControl;
import com.teamunemployment.lolanalytics.data.control.ParticipantControl;
import com.teamunemployment.lolanalytics.data.control.ParticipantIdentityControl;
import com.teamunemployment.lolanalytics.data.control.RuneControl;
import com.teamunemployment.lolanalytics.data.control.StatControl;
import com.teamunemployment.lolanalytics.data.control.TimelineControl;
import com.teamunemployment.lolanalytics.data.control.XpPerMinDeltaControl;
import com.teamunemployment.lolanalytics.data.database.DBHelper;
import com.teamunemployment.lolanalytics.data.statics;
import com.teamunemployment.lolanalytics.models.GeneralStats;
import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchList;
import com.teamunemployment.lolanalytics.models.MatchSummary;
import com.teamunemployment.lolanalytics.models.StringResponse;
import com.teamunemployment.lolanalytics.models.SummonerModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import javax.servlet.ServletException;


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

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi",httpMethod = ApiMethod.HttpMethod.GET,path = "test/{hi}" )
    public StringResponse sayHi(@Named("hi") String hi) throws ServletException, ClassNotFoundException {
        final String createTableSql = "CREATE TABLE IF NOT EXISTS visits ( visit_id INT NOT NULL "
                + "AUTO_INCREMENT, user_ip VARCHAR(46) NOT NULL, timestamp DATETIME NOT NULL, "
                + "PRIMARY KEY (visit_id) )";
        final String createVisitSql = "INSERT INTO visits (user_ip, timestamp) VALUES (?, ?)";
        final String selectSql = "SELECT user_ip, timestamp FROM visits ORDER BY timestamp DESC "
                + "LIMIT 10";

        String  url = System.getProperty("ae-cloudsql.cloudsql-database-url");
        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Load the class that provides the new "jdbc:google:mysql://" prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");
           // url = "jdbc:google:mysql://your-project-id:your-instance-name/guestbook?user=root";
        } else {
            // Local MySQL instance to use during development.
            Class.forName("com.mysql.jdbc.Driver");
            url = System.getProperty("ae-cloudsql.local-database-url");
            //url = "jdbc:mysql://127.0.0.1:3306/guestbook?user=root";
        }

        // TODO REMOVE THIS WHHEN WE DEPLOY TO APP ENGINE. Really need a solution.
        String host = "jdbc:mysql://localhost:3306/local_lolanlaytics";
        String user = "root";
        String password =  "Idnw2bh2";

       //try (Connection conn = DriverManager.getConnection(url);
       try (Connection conn = DriverManager.getConnection(host, user, password);
            PreparedStatement statementCreateVisit = conn.prepareStatement(createVisitSql)) {
            conn.createStatement().executeUpdate(createTableSql);
            String userIp = "123.456.78.9";
            statementCreateVisit.setString(1, userIp);
            statementCreateVisit.setTimestamp(2, new Timestamp(new Date().getTime()));
            statementCreateVisit.executeUpdate();
            String testResult = "";
            try (ResultSet rs = conn.prepareStatement(selectSql).executeQuery()) {
                while (rs.next()) {
                    String savedIp = rs.getString("user_ip");
                    String timeStamp = rs.getString("timestamp");
                    testResult += "Time: " + timeStamp + " Addr: " + savedIp + "\n";
                }
                StringResponse firstResponse = new StringResponse();
                firstResponse.setData(testResult);
                return firstResponse;
            }

        } catch (SQLException e) {
            throw new ServletException("SQL error", e);
        }
    }
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
        if (dbHelper == null) {
            dbHelper = new DBHelper("jdbc:mysql://localhost:3306/local_lolanlaytics", "root", "Idnw2bh2");
        }
        // Create the connection to our database.
        dbHelper.Connect();
        RuneControl runeControl = new RuneControl();
        CreepsPerMinDeltaControl cpmControl = new CreepsPerMinDeltaControl(dbHelper);
        CsDiffPerMinDeltasControl csdControl = new CsDiffPerMinDeltasControl(dbHelper);
        GoldPerMinDeltasControl gpmControl = new GoldPerMinDeltasControl(dbHelper);
        XpPerMinDeltaControl xpPerMinDeltaControl = new XpPerMinDeltaControl(dbHelper);
        StatControl statControl = new StatControl(dbHelper);
        MasteriesControl masteriesControl = new MasteriesControl();
        TimelineControl timelineControl = new TimelineControl(cpmControl, csdControl, gpmControl, xpPerMinDeltaControl, dbHelper);

        participantControl = new ParticipantControl(dbHelper, runeControl, timelineControl, statControl, masteriesControl);
        participantIdentityControl = new ParticipantIdentityControl(dbHelper);
        matchDetailsControl = new MatchDetailsControl(participantControl, participantIdentityControl, dbHelper, matchParticipantSummaryJunctionControl);
        matchSummaryControl = new MatchSummaryControl(dbHelper);
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
        
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
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
    public SummonerModel FetchSummonerInfoByName(@Named("summonerName") String summonerName) throws IOException {
        
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://oce.api.pvp.net/api/lol/oce/v1.4")
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setClient(new UrlFetchClient());
      
        RestAdapter serviceAdapter = builder.build();
       
        SummonerService service = serviceAdapter.create(SummonerService.class);
        SummonerModel summonerModel = service.getSummonerByName(summonerName);
        System.out.println(summonerModel.kloin.id);
        return summonerModel;
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
                .setClient(new UrlFetchClient()); //UrlFetchClient
                  
        RestAdapter serviceAdapter = builder.build();
        MatchService service = serviceAdapter.create(MatchService.class);
        MatchDetailsModel match = service.getMatchSummary(matchId);
        
        return match;
    } 
    
    @ApiMethod(name = "FetchTopStats", httpMethod=ApiMethod.HttpMethod.GET, path = "FetchTopStats/{userId}")
    public StringResponse FetchTopStats(@Named("userId") long userId) {
        init();
        StatisticsAPI.StatisticsApi statisticsApi = new StatisticsAPI.StatisticsApi(matchDetailsControl, matchSummaryControl);
        GeneralStats stats = statisticsApi.FetchAndCalculateStatsForAUserAndRole(userId, "TOP");
        
        Result creepsFirst10 = new Result("Creep Score First Ten Minutes", stats.averageCsEarlyGameEnemy, stats.averageCsEarlyGame);
        Result killsFirst10 = new Result("Kills First Ten Minutes", stats.averageKillsEnemy, stats.averageKills);
        Result deathsFirst10 = new Result("Deaths First Ten Minutes", stats.averageDeathsEnemy, stats.averageDeaths);
        String result = "["
                + creepsFirst10.toString() + ","
                + killsFirst10.toString() + ","
                + deathsFirst10.toString() + "]";
        StringResponse stringResponse = new StringResponse();
        stringResponse.setData(result);
        return stringResponse;
    } 
   
    
    
    
}
