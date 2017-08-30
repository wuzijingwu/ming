package com.example.scolerday1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/8/2.
 */

class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] title = {"推荐", "热点", "北京", "视频", "军事娱乐", "热点", "北京", "视频", "军事娱乐"};

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentManager mFragmentManager;



    public MyPagerAdapter(FragmentManager fm, List<Fragment> tempFragments) {
        super(fm);
        mFragmentManager = fm;
        mFragments = tempFragments;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;

    }


    @Override
    public Fragment getItem(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", title[position]);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
