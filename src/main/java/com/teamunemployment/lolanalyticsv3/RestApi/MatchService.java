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
    
    @GET("/matchlist/by-summoner/{summonerId}?api_key={api_key}")
    MatchList getMatchListForSummoner(@Path("summonerId") int summonerId, @Path("api_key") String api_key);
    
    @GET("/match/{matchId}?api_key={api_key}")
    MatchDetailsModel getMatchSummary(@Path("matchId") long matchId, @Path("api_key") String api_key);
    
    @GET("/summoner/by-name/{name}?api_key={api_key}")
    SummonerModel getSummonerByName(@Path("name") String name, @Path("api_key") String api_key);
    
}
