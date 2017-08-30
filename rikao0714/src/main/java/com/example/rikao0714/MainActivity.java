package com.example.rikao0714;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        try {
            getDates("http://apis.juhe.cn/cook/query.php?" + "&key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    public void getDates(String path) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {

                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean.StepsBean> steps = menuInfo.getResult().getData().get(0).getSteps();
                    listview.setAdapter(new Myadapter());

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

    class Myadapter extends BaseAdapter {
        private List<MenuInfo.ResultBean.DataBean.StepsBean> steps;
        private ImageView image;

        public Myadapter(List<MenuInfo.ResultBean.DataBean.StepsBean> steps) {
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
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);

            }
            image = (ImageView) convertView.findViewById(R.id.imageView);
            TextView text = (TextView) convertView.findViewById(R.id.text1);
            text.setText(steps.get(position).getStep());
            Loadimage(steps.get(position).getImg(),bitmap);


            return convertView;
        }

        public void Loadimage(String path, Bitmap bitmap) {
            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    if(bitmap!=null){

                        image.setImageBitmap(bitmap);

                    }
                    else{
                        image.setImageResource(R.mipmap.ic_launcher);


                    }


                }

                @Override
                protected Bitmap doInBackground(String... params) {
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
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            return bitmap;
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return null;
                }
            }.execute(path);


        }


    }


}
