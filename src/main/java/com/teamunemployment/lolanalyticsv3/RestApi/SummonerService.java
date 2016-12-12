/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalyticsv3.RestApi;

import com.teamunemployment.lolanalytics.models.SummonerModel;
import java.util.ArrayList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * consumption of the summoner rest methods from the Riot api.
 * @author jek40
 */
public interface SummonerService {
    @GET("/summoner/by-name/{name}?api_key=18decaab-29c4-422f-9a84-19a53a4f3067")
    SummonerModel getSummonerByName(@Path("name") String name);
    
}
