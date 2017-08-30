package com.example.bannerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strings = new ArrayList<String>();
        strings.add("http://img.juhe.cn/cookbook/s/1/45_c25e0cedd2012f45.jpg");
        strings.add("http://img.juhe.cn/cookbook/s/1/45_eb68327980f022dd.jpg");
        strings.add("http://img.juhe.cn/cookbook/s/1/45_8e0cf83cb7306281.jpg");

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        viewPager.setAdapter();


    }

    class Myadapter extends PagerAdapter{


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
//            super.destroyItem(container, position, object);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(MainActivity.this);
            container.addView(imageView);
            new Load(new );

            return imageView;
        }
    }



}
