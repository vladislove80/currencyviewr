package com.uvn.currencyviewr.ui.infofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uvn.currencyviewr.R;
import com.uvn.currencyviewr.ui.pairfragment.PairItem;
import com.uvn.network.PairCurrency;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class InfoFragment extends Fragment {

    private static final String KEY = "list key";
    private InfoViewModel viewModel;
    private InfoListAdapter adapter;

    public static InfoFragment newInstance(ArrayList<PairItem> list) {
        Bundle args = new Bundle();
        args.putSerializable(KEY, list);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = InfoViewModel.create();
        viewModel.pairRateLiveData.observe(this, pairRates -> adapter.addNewItems(pairRates));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapterToRecycler(view);
        List<PairItem> list = (ArrayList<PairItem>) getArguments().getSerializable(KEY);
        List<PairCurrency> currencies = new ArrayList<>();
        for (PairItem item : list) {
            currencies.add(item.getPair());
        }
        viewModel.getRates(currencies);
    }

    private void setAdapterToRecycler(@NonNull View view) {
        adapter = new InfoListAdapter();
        final RecyclerView recycler = view.findViewById(R.id.rvRates);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }
}
