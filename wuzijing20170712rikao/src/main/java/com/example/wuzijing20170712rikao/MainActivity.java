package com.example.wuzijing20170712rikao;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        try {
            getDatass("http://apis.juhe.cn/cook/query.php?" + "&key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getDatass(String path) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
//                    result > ResultBean > DataBean
                    List<MenuInfo.ResultBean.DataBean> data = menuInfo.getResult().getData();
                    listView.setAdapter(new Myadapter(data));

                }


            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream is = connection.getInputStream();
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


    class Myadapter extends BaseAdapter {
        private List<MenuInfo.ResultBean.DataBean> data;

        public Myadapter(List<MenuInfo.ResultBean.DataBean> data) {
            this.data = data;
        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                TextView text = (TextView) convertView.findViewById(R.id.test);
                text.setText("Title" + data.get(position).getTitle() + "\nTags" + data.get(position).getTags());

            }
            return convertView;
        }
    }


}
