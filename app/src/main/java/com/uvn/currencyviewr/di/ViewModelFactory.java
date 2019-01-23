package com.uvn.currencyviewr.di;

import com.uvn.currencyviewr.App;
import com.uvn.currencyviewr.ui.infofragment.InfoViewModel;
import com.uvn.currencyviewr.ui.pairfragment.PairViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory INSTANCE = null;
    private final AppComponent appComponent;

    private ViewModelFactory() {
        this.appComponent = App.getAppComponent();
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewModelFactory();
        }
        return(INSTANCE);
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PairViewModel.class)) {
            return (T) new PairViewModel(appComponent.getProvider());
        }
        if (modelClass.isAssignableFrom(InfoViewModel.class)) {
            return (T) new InfoViewModel(appComponent.getProvider());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
