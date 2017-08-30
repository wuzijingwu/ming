package com.example.wuzijing20170719rikao;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        try {
            getDatase("http://apis.juhe.cn/cook/query.php?key=15023923d1fc4d0c8f5c538f49d0baab");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getDatase(String path) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean> list = menuInfo.getResult().getData();
                    listView.setAdapter(new Myadapter(list));
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
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {

                            InputStream is = urlConnection.getInputStream();
                            String json = StreamStook.read(is);
                            return json;
                    }

                } catch (Exception e) {


                }

                return null;
            }
        }.execute(path);


    }

    class Myadapter extends BaseAdapter {
        List<MenuInfo.ResultBean.DataBean> list;
        private final DisplayImageOptions options;

        public Myadapter(List<MenuInfo.ResultBean.DataBean> list) {
            this.list = list;
            options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();


        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
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

            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView txt = (TextView) convertView.findViewById(R.id.text);

            txt.setText(list.get(position).getTitle());


            return convertView;
        }
    }


}
