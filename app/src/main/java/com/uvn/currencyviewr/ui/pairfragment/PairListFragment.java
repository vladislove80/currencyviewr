package com.uvn.currencyviewr.ui.pairfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uvn.currencyviewr.R;
import com.uvn.currencyviewr.ui.infofragment.InfoFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairListFragment extends Fragment {
    private static final String TAG = "PairListFragment";

    public static PairListFragment newInstance() {
        return new PairListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pairlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Pair");
        Log.d(TAG, "onViewCreated() called PairViewModel.get(this).getPairs() ");
        view.findViewById(R.id.btnGetInfo).setOnClickListener(v -> showInfoFragment());
        setAdapterToRecycler(view);
        PairViewModel.get(this).getPairs();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PairViewModel.get(this).pairsLiveData.observe(this, pairItems -> {
            Log.d(TAG, "onChanged() called with: pairItems = [" + pairItems + "]");
            PiarListAdapter adapter = getAdapter();
            if (adapter != null) {
                adapter.addNewItems(pairItems);
                showContent();
            }
        });
    }

    private void showContent() {
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.pb).setVisibility(View.GONE);
            view.findViewById(R.id.rvCurrencyPairs).setVisibility(View.VISIBLE);
            view.findViewById(R.id.btnGetInfo).setVisibility(View.VISIBLE);
        }
    }

    private void showInfoFragment() {
        ArrayList<PairItem> list = new ArrayList<>();
        PiarListAdapter adapter = getAdapter();
        if (adapter != null) {
            for (PairItem item : adapter.getList()) {
                if (item.isChecked()) {
                    list.add(item);
                }
            }
        }
        if (list.isEmpty())
            Toast.makeText(getContext(), "Select at least one currency!", Toast.LENGTH_LONG).show();
        else {
            String tag = InfoFragment.class.getSimpleName();
            if (getFragmentManager() != null) getFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, InfoFragment.newInstance(list), tag).addToBackStack(tag)
                    .commitAllowingStateLoss();
        }
    }

    @Nullable
    private PiarListAdapter getAdapter() {
        View view = getView();
        if (view != null) {
            final RecyclerView recycler = view.findViewById(R.id.rvCurrencyPairs);
            return (PiarListAdapter) recycler.getAdapter();
        }
        return null;
    }

    private void setAdapterToRecycler(@NonNull View view) {
        final RecyclerView recycler = view.findViewById(R.id.rvCurrencyPairs);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(new PiarListAdapter());
    }
}
