package com.example.lianxi;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell on 2017/7/26.
 */

public class Fragment1 extends Fragment implements XListView.IXListViewListener {

    private View view;
    private ViewPager viewPager;
    private XListView xListView;
    private ArrayList<String> list;
    private Timer timer;
    private int index = 0;
    private static final int TAG = 12;
    private int indexp = 1;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        xListView = (XListView) view.findViewById(R.id.xlistviews);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        String path1 = "http://zxpic.gtimg.com/infonew/0/wechat_pics_-32763036.jpg/640";
        String path2 = "http://i1.s1.dpfile.com/pc/f59ce7b879eea202f36692aa9ead9dac(249x249)/thumb.jpg";
        String path4 = "http://i3.s2.dpfile.com/2010-12-20/6201691_b.jpg(249x249)/thumb.jpg";
        try {
            getDatess("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("经典红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        list = new ArrayList<>();
        list.add(path1);
        list.add(path2);
        list.add(path4);
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
                final ImageView imageView = new ImageView(getActivity());
                new ImageLoad(new ImageLoad.Imagecallback() {
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

    public void getDatess(String path) {
        new AsyncTask<String, Void, String>() {

            private MyAdapter myAdapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean.StepsBean> steps = menuInfo.getResult().getData().get(0).getSteps();

                    if (myAdapter == null) {

                        myAdapter = new MyAdapter(steps);
                        xListView.setAdapter(new MyAdapter(steps));
                    } else {

                        myAdapter.LoadMore(steps, flag);
                        myAdapter.notifyDataSetChanged();
                    }


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
                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        String json = StreamTook.read(is);
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
        ++indexp;

        try {
            getDatess("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("经典红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        flag = true;
        xListView.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++indexp;

        try {
            getDatess("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("经典红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        flag = false;
        xListView.stopLoadMore();

    }

    class MyAdapter extends BaseAdapter {

        List<MenuInfo.ResultBean.DataBean.StepsBean> steps;

        public MyAdapter(List<MenuInfo.ResultBean.DataBean.StepsBean> steps) {

            this.steps = steps;

        }

        public void LoadMore(List<MenuInfo.ResultBean.DataBean.StepsBean> step, boolean flag) {
            for (MenuInfo.ResultBean.DataBean.StepsBean bean : steps) {
                if (flag) {

                    steps.add(0, bean);

                } else {
                    steps.add(bean);

                }


            }


        }


        @Override
        public int getCount() {
            return steps.size();
        }

        @Override
        public Object getItem(int position) {
            return steps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                convertView = convertView.inflate(getActivity(), R.layout.itemfrg1, null);


            }
            ImageView imageitem1 = (ImageView) convertView.findViewById(R.id.imageitem1);
            TextView textitem1 = (TextView) convertView.findViewById(R.id.textitem1);
            textitem1.setText(steps.get(position).getStep());
            getImage(steps.get(position).getImg(), imageitem1);
            return convertView;
        }


    }

    private void getImage(String path, ImageView imageitem1) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageitem1, options);
    }


}
