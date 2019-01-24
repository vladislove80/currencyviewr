package com.uvn.currencyviewr.ui.infofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uvn.currencyviewr.R;
import com.uvn.currencyviewr.ui.pairfragment.PairItem;
import com.uvn.network.PairCurrency;
import com.uvn.network.PairRate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class InfoFragment extends Fragment {

    private static final String KEY = "list key";

    public static InfoFragment newInstance(List<PairItem> list) {
        Bundle args = new Bundle();
        args.putSerializable(KEY, new ArrayList<>(list));
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void applyItems(List<PairRate> pairRates) {
        InfoListAdapter adapter = getAdapter();
        if (adapter != null) adapter.addNewItems(pairRates);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle("Info");
        }
        setAdapterToRecycler(view);
        List<PairItem> list = (ArrayList<PairItem>) getArguments().getSerializable(KEY);
        if (list == null) return;
        List<PairCurrency> currencies = new ArrayList<>();
        for (PairItem item : list) {
            currencies.add(item.getPair());
        }
        InfoViewModel.get(this).getRates(currencies);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InfoViewModel.get(this).pairRateLiveData.observe(this, this::applyItems);
    }

    private void setAdapterToRecycler(@NonNull View view) {
        final RecyclerView recycler = view.findViewById(R.id.rvRates);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(new InfoListAdapter());
    }

    @Nullable
    private InfoListAdapter getAdapter() {
        View view = getView();
        if (view != null) {
            final RecyclerView recycler = view.findViewById(R.id.rvRates);
            return (InfoListAdapter) recycler.getAdapter();
        }
        return null;
    }
}
