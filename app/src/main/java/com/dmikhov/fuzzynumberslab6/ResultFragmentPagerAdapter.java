package com.dmikhov.fuzzynumberslab6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dmikhov on 01.12.2016.
 */
public class ResultFragmentPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = null;
    ArrayList<String> titles = null;

    public ResultFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments != null ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null ? titles.get(position) : null;
    }
}
