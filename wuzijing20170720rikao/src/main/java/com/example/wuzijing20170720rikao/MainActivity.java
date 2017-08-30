package com.example.wuzijing20170720rikao;

import android.database.sqlite.SQLiteDatabase;
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

    private ListView listview;
    private SQLiteDatabase db;
    private String json;
    private  List<MenInfo.ResultBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getWritableDatabase();



        listview = (ListView) findViewById(R.id.list);
        try {
            getDase("http://apis.juhe.cn/cook/query.php?&key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getDase(String path) {
        new AsyncTask<String, Void, String>() {



            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenInfo menInfo = gson.fromJson(s, MenInfo.class);
                    List<MenInfo.ResultBean.DataBean> list = menInfo.getResult().getData();
                    listview.setAdapter(new Myadapter(list));

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
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = urlConnection.getInputStream();
                        json = StreamTook.read(is);
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
        List<MenInfo.ResultBean.DataBean> list;

        public Myadapter(List<MenInfo.ResultBean.DataBean> list) {
            this.list = list;


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
            TextView texst = (TextView) convertView.findViewById(R.id.test);
            texst.setText(list.get(position).getTitle());
            db.insert(list.get(position).getTitle(), "menfo", null);
            return convertView;
        }
    }

}
