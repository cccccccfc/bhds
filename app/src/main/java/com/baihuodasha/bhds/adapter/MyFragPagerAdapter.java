package com.baihuodasha.bhds.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class MyFragPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MyFragPagerAdapter(FragmentManager childFragmentManager, List<Fragment> fragments) {
        super(childFragmentManager);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
