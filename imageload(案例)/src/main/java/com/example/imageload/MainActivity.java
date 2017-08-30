package com.example.imageload;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imageload.bean.HealthInfo;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        LoadData("http://op.juhe.cn/yi18/news/list?key=6c0d8a8f3f374893d6e2cad284c1dbc2");


    }


    private void LoadData(String path) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                HealthInfo healthInfo = gson.fromJson(s, HealthInfo.class);
                List<HealthInfo.ResultBean.ListBean> list1 = healthInfo.getResult().getList();
                list.setAdapter(new MyAdapter(list1));

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

    class MyAdapter extends BaseAdapter {

        private List<HealthInfo.ResultBean.ListBean> list1;
        private  DisplayImageOptions options;

        public MyAdapter(List<HealthInfo.ResultBean.ListBean> list1) {

            this.list1 = list1;
            options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true).build();


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
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.ivLcon);
            TextView textView = (android.widget.TextView) convertView.findViewById(R.id.tvContent);
            textView.setText(list1.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list1.get(position).getImg(),imageView,options);
            return convertView;
        }
    }

}
