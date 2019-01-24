package com.uvn.network;

import com.google.gson.internal.LinkedHashTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
final class NetworkDataSource implements DataSource {

    private final ApiService apiService;

    NetworkDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getPairCurrency(final Callback<List<PairCurrency>> callback) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("get", "currency_list");
        queryMap.put("key", "dbc0a80dc45a23d1a68ecce161d99fe8");
        apiService.getCurrencyList(queryMap).enqueue(new retrofit2.Callback<CurrencyResponse<List<String>>>() {
            @Override
            public void onResponse(Call<CurrencyResponse<List<String>>> call, Response<CurrencyResponse<List<String>>> response) {
                if (response.isSuccessful()) {
                    CurrencyResponse<List<String>> body = response.body();
                    if (body == null) {
                        callback.onFailure(new NullPointerException("Body is null"));
                        return;
                    }
                    List<String> data = body.data;
                    List<PairCurrency> currencies = mapCurrencies(data);
                    callback.onResult(currencies);
                } else {
                    callback.onFailure(new IllegalArgumentException("Bad Request"));
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse<List<String>>> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    @Override
    public void getRatesForPairs(final List<PairCurrency> pairs, final Callback<List<PairRate>> callback) {
        String currencies = getPairs(pairs);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("get", "rates");
        queryMap.put("key", "dbc0a80dc45a23d1a68ecce161d99fe8");
        queryMap.put("pairs", currencies);
        apiService.getCurrencyRates(queryMap).enqueue(new retrofit2.Callback<CurrencyResponse<LinkedHashTreeMap<String, String>>>() {
            @Override
            public void onResponse(Call<CurrencyResponse<LinkedHashTreeMap<String, String>>> call, Response<CurrencyResponse<LinkedHashTreeMap<String, String>>> response) {
                if (response.isSuccessful()) {
                    CurrencyResponse<LinkedHashTreeMap<String, String>> body = response.body();
                    if (body == null) {
                        callback.onFailure(new NullPointerException("Body is null"));
                        return;
                    }
                    List<PairRate> pairRates = mapPairRates(body, pairs);
                    callback.onResult(pairRates);
                } else {
                    callback.onFailure(new IllegalArgumentException("Bad Request"));
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse<LinkedHashTreeMap<String, String>>> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    private List<PairRate> mapPairRates(CurrencyResponse<LinkedHashTreeMap<String, String>> body, List<PairCurrency> pairs) {
        LinkedHashTreeMap<String, String> treeMap = body.data;
        List<PairRate> pairRates = new ArrayList<>();
        for (PairCurrency pair : pairs) {
            String value = treeMap.get(pair.getName());
            if (value != null) {
                pairRates.add(new PairRate(pair, value));
            }
        }
        return pairRates;
    }

    private String getPairs(List<PairCurrency> pairs) {
        StringBuilder currenciesBld = new StringBuilder();
        for (PairCurrency pair : pairs) {
            currenciesBld.append(pair.getName()).append(',');
        }
        String currencies = currenciesBld.toString();
        currencies = currencies.substring(0, currencies.length() - 2);
        return currencies;
    }

    private List<PairCurrency> mapCurrencies(List<String> data) {
        List<PairCurrency> currencies = new ArrayList<>();
        for (String currency : data) {
            currencies.add(new PairCurrency(currency));
        }
        return currencies;
    }
}
