package com.uvn.currencyviewr.ui.pairfragment;

import android.util.Log;

import com.uvn.currencyviewr.di.ViewModelFactory;
import com.uvn.network.DataSource;
import com.uvn.network.DataSourceProvider;
import com.uvn.network.PairCurrency;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairViewModel extends ViewModel {
    private static final String TAG = "PairViewModel";
    private DataSource dataSource;
    MutableLiveData<List<PairItem>> pairsLiveData = new MutableLiveData<>();
    private List<PairItem> pairsList;

    public PairViewModel(DataSourceProvider provider) {
        Log.d(TAG, "PairViewModel() called with: provider = [" + provider + "]");
        dataSource = provider.getDataSource();
    }

    public static PairViewModel create() {
        return ViewModelFactory.getInstance().create(PairViewModel.class);
    }

    public void getPairs() {
        if (pairsList != null && !pairsList.isEmpty()) {
            pairsLiveData.postValue(pairsList);
            return;
        }
        dataSource.getPairCurrency(new DataSource.Callback<List<PairCurrency>>() {
            @Override
            public void onResult(List<PairCurrency> data) {
                Log.d(TAG, "onResult() called with: data = [" + data + "]");
                pairsList = new ArrayList<>();
                for (PairCurrency pairCurrency : data) {
                    pairsList.add(new PairItem(pairCurrency));
                }
                pairsLiveData.postValue(pairsList);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pairsLiveData.postValue(new ArrayList<PairItem>());
            }
        });
    }
}
