package com.example.wuzijing1506a20170825;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 2017/8/25.
 */

public class MyAdapters extends FragmentStatePagerAdapter {

    private String[] titles = {"推荐", "热点", "北京", "视频", "社会", "体育", "明星", "运动", "新闻", "爱奇艺", "头条", "聚美"};
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragment = new ArrayList<Fragment>();


    public MyAdapters(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    public MyAdapters(FragmentManager fm, ArrayList<Fragment> tempFragment) {
        super(fm);
        mFragmentManager = fm;
        mFragment = tempFragment;
    }

    @Override
    public Fragment getItem(int position) {
        MyFragmnet myFragmnet = new MyFragmnet();


        return myFragmnet;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
