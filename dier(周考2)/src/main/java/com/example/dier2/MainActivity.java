package com.example.dier2;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<String> list;
    private int index = 0;
    private static final int TAG = 12;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG:
                    int index = (int) msg.obj;
                    viewPager.setCurrentItem(index);

                    break;


            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add("http://l2.51fanli.net//tuan//images//1//5806eac956808.jpg");
        list.add("http://l2.51fanli.net//tuan//images//b//580991bb30560.jpg");
        list.add("http://l0.51fanli.net//tuan//images//b//58115f2593dc3.jpg");
        list.add("http://l2.51fanli.net//tuan//images//0//57923840b054d.jpg");
        list.add("http://l2.51fanli.net//tuan//images//e//58101e11ab164.jpg");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new Myadapter());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (index < list.size() - 1) {
                    index++;


                } else if (index == list.size() - 1) {

                    index = 0;

                }
                Message msg = new Message();
                msg.what = TAG;
                msg.obj = index;
                handler.sendMessage(msg);
            }
        }, 0, 2000);


    }

    class Myadapter extends PagerAdapter {


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
            imageView.setImageResource(R.mipmap.ic_launcher);
            new LoadImage(new LoadImage.ImageCallbackLister() {
                @Override
                public void imagecall(Bitmap bitmap) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                }
            }).execute(list.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);

        }
    }


}
