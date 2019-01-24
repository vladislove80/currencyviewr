package com.uvn.currencyviewr.ui.pairfragment;

import com.uvn.network.model.PairCurrency;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairItem implements Serializable {

    private boolean isChecked;

    private final PairCurrency pair;

    PairItem(PairCurrency pair) {
        this.pair = pair;
    }

    String getName() {
        return pair.getName().substring(0, 3) + "/" + pair.getName().substring(3, 6);
    }

    public PairCurrency getPair() {
        return pair;
    }

    boolean isChecked() {
        return isChecked;
    }

    void setChecked(boolean checked) {
        isChecked = checked;
    }

    @NonNull
    @Override
    public String toString() {
        return "PairItem{" +
                "isChecked=" + isChecked +
                ", pair=" + pair +
                '}';
    }
}
