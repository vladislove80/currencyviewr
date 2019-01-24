package com.uvn.currencyviewr.di;

import com.uvn.currencyviewr.App;
import com.uvn.network.DataSourceProvider;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class AppComponent {

    private App app;
    private DataSourceProvider provider;

    private AppComponent(App app, DataSourceProvider provider) {

        this.app = app;
        this.provider = provider;
    }

    public static class Builder {

        private App app;
        private DataSourceProvider provider;

        public Builder() {
        }

        public AppComponent.Builder application(App app) {
            this.app = app;
            return this;
        }

        public AppComponent.Builder provider(DataSourceProvider provider) {
            this.provider = provider;
            return this;
        }

        public AppComponent build() {
            return new AppComponent(app, provider);
        }
    }

    public App getApp() {
        return app;
    }

    DataSourceProvider getProvider() {
        return provider;
    }
}
