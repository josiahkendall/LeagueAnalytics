/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamunemployment.lolanalytics.data.control.Summoner;

import com.google.gson.Gson;
import com.teamunemployment.lolanalytics.models.SummonerInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/**
 * 
 * @author jek40
 */
public class SummonerInfoControl {
 
    /**
     * Fetch the summoner id for a user.
     * @param summonerName The entered summoner name
     * @return The summoner id for the summoner name that we gave.
     */
    public SummonerInfo FetchSummonerInfo(String summonerName) throws IOException {
        // Todo Remove api key from strings
        String urlString = "https://oce.api.pvp.net/api/lol/oce/v1.4/summoner/by-name/"+summonerName + "?api_key=18decaab-29c4-422f-9a84-19a53a4f3067";
        URL url = new URL(urlString);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer json = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        // The object is listed under an object with the summoner name, so we need to fetch that object first;
        JSONObject jsonObject = new JSONObject(json);
        JSONObject objectOfInterest = jsonObject.getJSONObject(summonerName);
        SummonerInfo summonerInfo = gson.fromJson(objectOfInterest.toString(), SummonerInfo.class);
        return summonerInfo;
    }
}
