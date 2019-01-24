package com.uvn.currencyviewr.ui.infofragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uvn.currencyviewr.R;
import com.uvn.network.PairRate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
class InfoListAdapter extends RecyclerView.Adapter<PairRateHolder> {
    private List<PairRate> list = new ArrayList<>();

    @NonNull
    @Override
    public PairRateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pair_rate_item, parent, false);
        return new PairRateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PairRateHolder holder, int position) {
        holder.bindItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void addNewItems(List<PairRate> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
