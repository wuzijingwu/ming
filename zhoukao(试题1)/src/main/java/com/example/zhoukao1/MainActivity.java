package com.example.zhoukao1;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ArrayList<String> list;
    private int index = 0;
    private static final int TAG = 12;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG:

                    int a = (Integer) msg.obj;
                    viewpager.setCurrentItem(a);
                    break;

            }


        }
    };
    private ExpandableListView expandableListView;
    private Button butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        list.add("http://img.juhe.cn/cookbook/s/1/92_a377c03180cb6b26.jpg");
        list.add("http://tnfs.tngou.net/image/lore/150829/75ddc66c2c011fc5471bde4d218f12e5.jpg");
        list.add("http://tnfs.tngou.net/image/lore/150829/bfd758579579fee7cd0a3ba1231c1c2a.jpg");


        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new Myadapter());
//        autoPlay();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
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
        }, 0, 3000);
        expandableListView = (ExpandableListView) findViewById(R.id.ExpandableListView);
        butt = (Button) findViewById(R.id.Buttonss);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDatass("http://apis.juhe.cn/cook/query.php","秘制红烧肉","ff00d7339861c7fd7d5b54b16b76422a");


            }

            private void getDatass(String path, String menu, String key) {
                new AsyncTask<String, Void, String>() {

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(s!=null){
                            Gson gson = new Gson();
                            MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                            expandableListView.setAdapter(new MyExpandAdapter());
                        }



                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            String path = params[0];
                            String menu = params[1];
                            String key = params[2];
                            URL url = new URL(path);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("POST");
                            urlConnection.setConnectTimeout(5000);
                            urlConnection.setReadTimeout(5000);
                            OutputStream os = urlConnection.getOutputStream();
                            os.write(("menu=" + URLEncoder.encode(menu, "utf-8") + "&key=" + key).getBytes());
                            os.flush();
                            int code = urlConnection.getResponseCode();
                            if (code == 200) {
                                InputStream is = urlConnection.getInputStream();
                                return StreamTook.read(is);

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        return null;
                    }
                }.execute(path, menu, key);


            }
        });


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
            new LoadImage(new LoadImage.ImagecallbackLister() {
                @Override
                public void imagecallback(Bitmap bitmap) {
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
