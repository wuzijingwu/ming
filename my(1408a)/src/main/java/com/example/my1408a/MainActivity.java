package com.example.my1408a;

import android.graphics.Bitmap;
import android.os.AsyncTask;
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
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        String path1 = "http://v.juhe.cn/movie/img?5146";
        String path2 = "http://v.juhe.cn/movie/img?14923";
        String path3 = "http://v.juhe.cn/movie/img?15209";
        list = new ArrayList<>();
        list.add(path1);
        list.add(path2);
        list.add(path3);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                final ImageView imageView = new ImageView(MainActivity.this);
                new ImageLoad(new ImageLoad.Imagecallback() {
                    @Override
                    public void imagcall(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }).execute(list.get(position));
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });


    }


}
