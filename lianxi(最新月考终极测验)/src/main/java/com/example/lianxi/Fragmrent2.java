package com.example.lianxi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.List;

/**
 * Created by dell on 2017/7/26.
 */

public class Fragmrent2 extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView xListView;
    private int indeoo=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xListView = (XListView) view.findViewById(R.id.listfrg2);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        try {
            getDater("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getDater(String path) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {

                    Gson gson = new Gson();
                    YingyinInfo yingyinInfo = gson.fromJson(s, YingyinInfo.class);
                    List<YingyinInfo.ResultBean.DataBean.StepsBean> steps = yingyinInfo.getResult().getData().get(0).getSteps();
                    xListView.setAdapter(new MyAdapters(steps));

                }


            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
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
        ++indeoo;
        try {
            getDater("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        xListView.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++indeoo;
        try {
            getDater("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        xListView.stopLoadMore();
    }

    class MyAdapters extends BaseAdapter {
        List<YingyinInfo.ResultBean.DataBean.StepsBean> steps;

        public MyAdapters(List<YingyinInfo.ResultBean.DataBean.StepsBean> steps) {
            this.steps = steps;
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
        public int getItemViewType(int position) {
            if (position % 2 == 0) {

                return 0;


            } else {
                return 1;
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
                case 0: {
                    if (convertView == null) {
                        convertView = convertView.inflate(getActivity(), R.layout.item2frg2, null);

                    }
                    TextView text2frg2 = (TextView) convertView.findViewById(R.id.text2frg2);
                    ImageView imag2frg2 = (ImageView) convertView.findViewById(R.id.imag2frg2);
                    text2frg2.setText(steps.get(position).getStep());
                    getImage(steps.get(position).getImg(), imag2frg2);
                }

                break;

                case 1: {
                    if (convertView == null) {
                        convertView = convertView.inflate(getActivity(), R.layout.item1frg2, null);

                    }
                    TextView textfrg2 = (TextView) convertView.findViewById(R.id.textfrg2);
                    ImageView imagfrg2 = (ImageView) convertView.findViewById(R.id.imagfrg2);
                    textfrg2.setText(steps.get(position).getStep());
                    getImage(steps.get(position).getImg(), imagfrg2);


                }

                break;


            }


            return convertView;
        }
    }

    private void getImage(String path, ImageView imageitem) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageitem, options);
    }


}
