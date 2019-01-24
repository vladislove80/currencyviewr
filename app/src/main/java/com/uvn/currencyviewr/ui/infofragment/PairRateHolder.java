package com.uvn.currencyviewr.ui.infofragment;

import android.view.View;
import android.widget.TextView;

import com.uvn.currencyviewr.R;
import com.uvn.network.PairRate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
class PairRateHolder extends RecyclerView.ViewHolder {

    private TextView currencyPair;
    private TextView pairValue;

    PairRateHolder(@NonNull View itemView) {
        super(itemView);
        currencyPair = itemView.findViewById(R.id.pairCur);
        pairValue = itemView.findViewById(R.id.tvValue);
    }

    void bindItem(final PairRate item) {
        currencyPair.setText(item.getName());
        pairValue.setText(item.getValue());
    }
}
