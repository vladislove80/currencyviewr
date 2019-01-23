package com.uvn.network;

import com.google.gson.internal.LinkedHashTreeMap;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
interface ApiService {
    @GET("/api/")
    Call<CurrencyResponse<List<String>>> getCurrencyList(@QueryMap Map<String, String> queryMap);

    @GET("/api/")
    Call<CurrencyResponse<LinkedHashTreeMap<String, String>>> getCurrencyRates(@QueryMap Map<String, String> queryMap);
}
