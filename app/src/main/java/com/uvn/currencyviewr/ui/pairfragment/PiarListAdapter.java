package com.uvn.currencyviewr.ui.pairfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uvn.currencyviewr.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PiarListAdapter extends RecyclerView.Adapter<PairHolder> {

    private List<PairItem> list = new ArrayList<>();

    @NonNull
    @Override
    public PairHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pair_item, parent, false);
        return new PairHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PairHolder holder, int position) {
        holder.bindItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    List<PairItem> getList() {
        return list;
    }

    void addNewItems(List<PairItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
