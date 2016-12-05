package com.dmikhov.fuzzynumberslab6.ui.main;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabs) TabLayout tabs;
    @Bind(R.id.viewPager) ViewPager viewPager;
    TitledFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPager();
    }

    private void initPager() {
        Fragment cubicFragment = new InputCubicFragment();
        Fragment gaussFragment = new InputGaussFragment();
        Fragment triangleFragment = new InputTriangleFragment();
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(triangleFragment, gaussFragment, cubicFragment));
        ArrayList<String> titles = new ArrayList<>(Arrays.asList("Triangle", "GaussNumber", "Cubic"));
        adapter = new TitledFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

}
