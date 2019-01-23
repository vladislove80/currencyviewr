package com.uvn.network;

import com.google.gson.internal.LinkedHashTreeMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public interface DataSource {
    void getPairCurrency(Callback<List<PairCurrency>> callback);

    void getRatesForPairs(List<PairCurrency> pairs, Callback<List<PairRate>> callback);

    interface Callback<T> {
        void onResult(T data);

        void onFailure(Throwable t);
    }
}