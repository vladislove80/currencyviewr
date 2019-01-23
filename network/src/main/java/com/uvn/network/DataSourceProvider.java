package com.uvn.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSourceProvider {
    private final String baseUrl;

    public DataSourceProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public DataSource getDataSource(){
        return new NetworkDataSource(createService());
    }

    private ApiService createService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
