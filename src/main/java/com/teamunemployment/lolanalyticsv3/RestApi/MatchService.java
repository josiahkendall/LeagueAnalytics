package com.teamunemployment.lolanalyticsv3.RestApi;


import com.teamunemployment.lolanalytics.models.MatchDetailsModel;
import com.teamunemployment.lolanalytics.models.MatchList;
import com.teamunemployment.lolanalytics.models.SummonerModel;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Riot api consumed here using retrofit 1.9.
 * @author Josiah Kendall
 */
public interface MatchService {
    
    @GET("/matchlist/by-summoner/{summonerId}?api_key=18decaab-29c4-422f-9a84-19a53a4f3067")
    MatchList getMatchListForSummoner(@Path("summonerId") int summonerId);
    
    @GET("/match/{matchId}?api_key=18decaab-29c4-422f-9a84-19a53a4f3067")
    MatchDetailsModel getMatchSummary(@Path("matchId") long matchId);
    
    @GET("/summoner/by-name/{name}?api_key=18decaab-29c4-422f-9a84-19a53a4f3067")
    SummonerModel getSummonerByName(@Path("name") String name);
    
}
