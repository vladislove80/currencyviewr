package com.uvn.currencyviewr.ui.infofragment;

import android.util.Log;

import com.uvn.currencyviewr.di.ViewModelFactory;
import com.uvn.network.DataSource;
import com.uvn.network.DataSourceProvider;
import com.uvn.network.PairCurrency;
import com.uvn.network.PairRate;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class InfoViewModel extends ViewModel {
    private static final String TAG = "InfoViewModel";
    private final DataSource dataSource;
    MutableLiveData<List<PairRate>> pairRateLiveData = new MutableLiveData<>();
    private List<PairRate> data;

    public InfoViewModel(DataSourceProvider provider) {
        this.dataSource = provider.getDataSource();
    }

    public static InfoViewModel create() {
        return ViewModelFactory.getInstance().create(InfoViewModel.class);
    }

    public void getRates(List<PairCurrency> pairs) {
        if (data != null && !data.isEmpty()) {
            pairRateLiveData.postValue(data);
            return;
        }
        dataSource.getRatesForPairs(pairs, new DataSource.Callback<List<PairRate>>() {
            @Override
            public void onResult(List<PairRate> data) {
                Log.d(TAG, "onResult() called with: data = [" + data + "]");
                InfoViewModel.this.data = data;
                pairRateLiveData.postValue(data);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                pairRateLiveData.postValue(new ArrayList<>());
            }
        });
    }
}
