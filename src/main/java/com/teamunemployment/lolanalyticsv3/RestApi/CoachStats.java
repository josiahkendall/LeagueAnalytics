package com.teamunemployment.lolanalyticsv3.RestApi;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.gson.GsonBuilder;
import com.teamunemployment.lolanalytics.models.StringResponse;
import retrofit.RestAdapter;
import retrofit.appengine.UrlFetchClient;
import retrofit.converter.GsonConverter;

/**
 * @author Josiah Kendall
 */
public class CoachStats {

//    @ApiMethod(name = "RefreshUserData", httpMethod = ApiMethod.HttpMethod.GET, path = "Refresh/{SummonerId}")
//    public StringResponse RefreshUserData(@Named("SummonerId") long summonerId) {
//        init();
//
//        RestAdapter.Builder builder = new RestAdapter.Builder()
//                .setEndpoint("https://oce.api.pvp.net/api/lol/oce/v2.2")
//                .setConverter(new GsonConverter(new GsonBuilder().create()))
//                .setClient(new UrlFetchClient());
//
//        RestAdapter serviceAdapter = builder.build();

}
