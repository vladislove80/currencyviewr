package com.uvn.currencyviewr.ui.pairfragment;

import android.util.Log;

import com.uvn.currencyviewr.di.ViewModelFactory;
import com.uvn.network.DataSource;
import com.uvn.network.DataSourceProvider;
import com.uvn.network.model.PairCurrency;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairViewModel extends ViewModel {
    private static final String TAG = "PairViewModel";
    private DataSource dataSource;
    MutableLiveData<List<PairItem>> pairsLiveData = new MutableLiveData<>();

    public PairViewModel(DataSourceProvider provider) {
        Log.d(TAG, "PairViewModel() called with: provider = [" + provider + "]");
        dataSource = provider.getDataSource();
    }

    static PairViewModel get(Fragment fragment) {
        return ViewModelProviders.of(fragment, ViewModelFactory.getInstance()).get(PairViewModel.class);
    }

    void getPairs() {
        if (pairsLiveData.getValue() != null) {
            return;
        }
        dataSource.getPairCurrency(new DataSource.Callback<List<PairCurrency>>() {
            @Override
            public void onResult(List<PairCurrency> data) {
                List<PairItem> pairsList = new ArrayList<>();
                for (PairCurrency pairCurrency : data) {
                    pairsList.add(new PairItem(pairCurrency));
                }
                pairsLiveData.postValue(pairsList);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pairsLiveData.postValue(new ArrayList<>());
            }
        });
    }
}
