package com.example.my;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.my.R.id.imageitem1;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private ViewPager viewPager;
    private ArrayList<String> list;
    private Timer timer;
    private int index = 0;
    private static final int TAG = 12;
    private int idecc = 1;
    private boolean flag;

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
    private XListView xListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);

        try {
            getDates("http://apis.juhe.cn/cook/query.php?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OutputStream outputStream = urlConnection.getOutputStream();
//        outputStream.write(("key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8")).getBytes());
//        outputStream.flush();


        viewPager = (ViewPager) findViewById(R.id.viewpage);
        String path1 = "http://img.juhe.cn/cookbook/s/1/45_0824e37faf00b71e.jpg";
        String path2 = "http://img.juhe.cn/cookbook/s/1/45_6ee9e8dab0516333.jpg";
        String path3 = "http://img.juhe.cn/cookbook/s/1/45_b9afd6d4dd81f55c.jpg";
        list = new ArrayList<String>();
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

                new LoadImage(new LoadImage.Imagrcallbac() {
                    @Override
                    public void imagecall(Bitmap bitmap) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
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


        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
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

    public void getDates(final String path) {

        new AsyncTask<String, Void, String>() {

            private Myadapter myadapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean.StepsBean> list1 = menuInfo.getResult().getData().get(0).getSteps();

                    if (myadapter == null) {
                        myadapter = new Myadapter(list1);
                        xListView.setAdapter(new Myadapter(list1));
                    } else {
                        myadapter.LoadMore(list1, flag);
                        myadapter.notifyDataSetChanged();

                    }


                }


            }


            class Myadapter extends BaseAdapter {

                List<MenuInfo.ResultBean.DataBean.StepsBean> list1;


                public Myadapter(List<MenuInfo.ResultBean.DataBean.StepsBean> list1) {

                    this.list1 = list1;

                }

                public void LoadMore(List<MenuInfo.ResultBean.DataBean.StepsBean> list1s, boolean flag) {
                    for (MenuInfo.ResultBean.DataBean.StepsBean baen : list1) {
                        if (flag) {

                            list1.add(0, baen);

                        } else {
                            list1.add(baen);

                        }


                    }


                }


                @Override
                public int getCount() {
                    return list1.size();
                }

                @Override
                public Object getItem(int position) {
                    return list1.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public int getItemViewType(int position) {
                    if (position % 2 == 0) {
                        return 0;//偶数


                    } else {
                        return 1;//奇数

                    }


                }

                @Override
                public int getViewTypeCount() {
                    return 2;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {


                    int type = getItemViewType(position);
                    switch (type) {
                        case 1: {

                            if (convertView == null) {

                                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                                final ImageView imageitem = (ImageView) convertView.findViewById(R.id.imageitem1);
                                TextView textitem1 = (TextView) convertView.findViewById(R.id.textitem1);
                                textitem1.setText(list1.get(position).getStep());
                                new LoadImage(new LoadImage.Imagrcallbac() {
                                    @Override
                                    public void imagecall(Bitmap bitmap) {

                                        if (bitmap != null) {

                                            imageitem.setImageBitmap(bitmap);

                                        }


                                    }
                                }).execute(list1.get(position).getImg());
                            }
                        }
                        break;

                        case 0: {
                            if (convertView == null) {
                                convertView = convertView.inflate(MainActivity.this, R.layout.item1, null);
                                final ImageView imageitem2 = (ImageView) convertView.findViewById(R.id.imageitem2);
                                TextView textitem2 = (TextView) convertView.findViewById(R.id.textitem2);
                                textitem2.setText(list1.get(position).getStep());
                                new LoadImage(new LoadImage.Imagrcallbac() {
                                    @Override
                                    public void imagecall(Bitmap bitmap) {
                                        if (bitmap != null) {

                                            imageitem2.setImageBitmap(bitmap);


                                        }


                                    }
                                }).execute(list1.get(position).getImg());


                            }

                        }
                        break;


                    }
                    return convertView;

                }


            }


            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(2000);
                    urlConnection.setReadTimeout(2000);


//&key=15023923d1fc4d0c8f5c538f49d0baab&menu="+ URLEncoder.encode("秘制红烧肉","utf-8")

                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        String json = StreamStook.read(is);
                        return json;

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);


    }

    @Override
    public void onRefresh() {
        try {
            ++idecc;
            getDates("http://apis.juhe.cn/cook/query.php?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
            flag = true;

            xListView.stopRefresh(true);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoadMore() {
        try {
            ++idecc;
            getDates("http://apis.juhe.cn/cook/query.php?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
            flag = false;
            xListView.stopLoadMore();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
