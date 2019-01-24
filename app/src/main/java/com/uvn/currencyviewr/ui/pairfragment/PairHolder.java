package com.uvn.currencyviewr.ui.pairfragment;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.uvn.currencyviewr.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
class PairHolder extends RecyclerView.ViewHolder {

    private TextView currencyPair;
    private Switch cSwitch;

    PairHolder(@NonNull View itemView) {
        super(itemView);

        currencyPair = itemView.findViewById(R.id.pairCur);
        cSwitch = itemView.findViewById(R.id.switchPair);
    }

    void bindItem(final PairItem item) {
        currencyPair.setText(item.getName());
        cSwitch.setChecked(item.isChecked());
        cSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> item.setChecked(isChecked));
    }
}
