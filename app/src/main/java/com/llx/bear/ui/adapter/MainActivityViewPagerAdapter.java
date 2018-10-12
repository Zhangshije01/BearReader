package com.llx.bear.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/10/10 18:00
 */

public class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> myData;

    public MainActivityViewPagerAdapter(FragmentManager fm, List<Fragment> myData) {
        super(fm);
        this.myData = myData;
    }

    @Override
    public Fragment getItem(int position) {
        return myData.get(position);
    }

    @Override
    public int getCount() {
        return myData != null ? myData.size() : 0;
    }
}
