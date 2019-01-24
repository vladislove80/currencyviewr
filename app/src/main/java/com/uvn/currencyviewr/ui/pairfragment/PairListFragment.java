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
    private PairViewModel viewModel;
    private PiarListAdapter adapter;

    public static PairListFragment newInstance() {
        return new PairListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = PairViewModel.create();
        viewModel.pairsLiveData.observe(this, pairItems -> {
            Log.d(TAG, "onChanged() called with: pairItems = [" + pairItems + "]");
            adapter.addNewItems(pairItems);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pairlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated() called viewModel.getPairs() ");
        view.findViewById(R.id.btnGetInfo).setOnClickListener(v -> showInfoFragment());
        setAdapterToRecycler(view);
        viewModel.getPairs();
    }

    private void showInfoFragment() {
        ArrayList<PairItem> list = new ArrayList<>();
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

    private void setAdapterToRecycler(@NonNull View view) {
        adapter = new PiarListAdapter();
        final RecyclerView recycler = view.findViewById(R.id.currencyPairList);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }
}
