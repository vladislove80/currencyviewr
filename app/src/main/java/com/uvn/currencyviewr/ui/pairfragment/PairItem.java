package com.uvn.currencyviewr.ui.pairfragment;

import com.uvn.network.PairCurrency;

import java.io.Serializable;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairItem implements Serializable {

    private boolean isChecked;

    private final PairCurrency pair;

    public PairItem(PairCurrency pair) {
        this.pair = pair;
    }

    public String getName() {
        return pair.getName().substring(0, 3) + "/" + pair.getName().substring(3, 6);
    }

    public PairCurrency getPair() {
        return pair;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "PairItem{" +
                "isChecked=" + isChecked +
                ", pair=" + pair +
                '}';
    }
}
