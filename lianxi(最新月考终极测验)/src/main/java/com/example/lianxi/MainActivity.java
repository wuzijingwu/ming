package com.example.lianxi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.limxing.xlistview.view.XListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    private ViewPager viewPager;
//    private XListView xListView;
    private ArrayList<Fragment> fragments;
    private Fragment1 fragment1;
    private ViewPager viewpage;
    private Fragmrent2 fragmrent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new ArrayList<>();
        fragment1 = new Fragment1();
        fragmrent2 = new Fragmrent2();
        fragments.add(fragment1);
        fragments.add(fragmrent2);
        viewpage = (ViewPager) findViewById(R.id.viewpger);
        viewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


    }
}
