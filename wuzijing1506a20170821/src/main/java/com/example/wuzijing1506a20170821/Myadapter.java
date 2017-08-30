package com.example.wuzijing1506a20170821;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


/**
 * Created by dell on 2017/8/21.
 */

public class Myadapter extends FragmentStatePagerAdapter {

    private String[] titles = {"推荐", "热点", "视屏", "时尚", "科技", "体育", "财经", "爱奇艺", "腾讯"};
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragment = new ArrayList<Fragment>();


    public Myadapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;

    }

    public Myadapter(FragmentManager fm, ArrayList<Fragment> mtempFragment) {
        super(fm);
        mFragmentManager = fm;
        mFragment =  mtempFragment;
    }

    @Override
    public Fragment getItem(int position) {
        MyFragment myFragment = new MyFragment();
        return myFragment;
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
