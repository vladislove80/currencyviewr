package com.uvn.currencyviewr.ui.infofragment;

import android.util.Log;

import com.uvn.currencyviewr.di.ViewModelFactory;
import com.uvn.network.DataSource;
import com.uvn.network.DataSourceProvider;
import com.uvn.network.model.PairCurrency;
import com.uvn.network.model.PairRate;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class InfoViewModel extends ViewModel {
    private final DataSource dataSource;
    MutableLiveData<List<PairRate>> pairRateLiveData = new MutableLiveData<>();

    public InfoViewModel(DataSourceProvider provider) {
        this.dataSource = provider.getDataSource();
    }

    static InfoViewModel get(Fragment fragment) {
        return ViewModelProviders.of(fragment, ViewModelFactory.getInstance()).get(InfoViewModel.class);
    }

    void getRates(List<PairCurrency> pairs) {
        if (pairRateLiveData.getValue() != null) {
            return;
        }
        dataSource.getRatesForPairs(pairs, new DataSource.Callback<List<PairRate>>() {
            @Override
            public void onResult(List<PairRate> data) {
                pairRateLiveData.postValue(data);
            }

            @Override
            public void onFailure(Throwable t) {
                pairRateLiveData.postValue(new ArrayList<>());
            }
        });
    }
}
