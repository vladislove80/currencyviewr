package com.uvn.currencyviewr;

import android.app.Application;

import com.uvn.currencyviewr.di.AppComponent;
import com.uvn.network.DataSourceProvider;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class App extends Application {

    public static final String BASE_URL = "https://currate.ru/";
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = new AppComponent.Builder().application(this).provider(new DataSourceProvider(BASE_URL)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
