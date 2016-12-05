package com.dmikhov.fuzzynumberslab6.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dmikhov.fuzzynumberslab6.R;
import com.dmikhov.fuzzynumberslab6.ui.adapters.TitledFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class ResultActivity extends AppCompatActivity {


    @Bind(R.id.tabs) TabLayout tabs;
    @Bind(R.id.viewPager) ViewPager viewPager;
    TitledFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        initPager();
    }

    private void initPager() {
        Fragment fragmentGraph = new GraphFragment();
        Fragment fragmentTable = new TableFragment();
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(fragmentGraph, fragmentTable));
        ArrayList<String> titles = new ArrayList<>(Arrays.asList(getString(R.string.graph), getString(R.string.table)));
        adapter = new TitledFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }


}
