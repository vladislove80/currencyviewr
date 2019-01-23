package com.uvn.currencyviewr.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.uvn.currencyviewr.R;
import com.uvn.currencyviewr.ui.pairfragment.PairListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(PairListFragment.newInstance());
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, fragment, fragment.getClass().getSimpleName()).commitNowAllowingStateLoss();
    }
}
