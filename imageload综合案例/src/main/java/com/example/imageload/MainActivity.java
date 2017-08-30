package com.example.imageload;

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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        listView = (ListView) findViewById(R.id.lv);
        try {
            getMeuInfo("http://apis.juhe.cn/cook/query.php?" +
                    "key=ff00d7339861c7fd7d5b54b16b76422a&menu=" +
                    URLEncoder.encode("秘制红烧肉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getMeuInfo(String path) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean.StepsBean> data = menuInfo.getResult().getData().get(0).getSteps();
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
                        String json = SekTook.makeRead(is);
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

        private List<MenuInfo.ResultBean.DataBean.StepsBean> data;

        public Myadapter(List<MenuInfo.ResultBean.DataBean.StepsBean> data) {
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
                convertView = convertView.inflate(MainActivity.this, R.layout.imagelayout, null);

            }
            ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
            TextView text = (TextView) convertView.findViewById(R.id.text);
            text.setText(data.get(position).getStep());
            loadimage(data.get(position).getImg(), image);

            return convertView;
        }

        public void loadimage(String path, final ImageView image) {

            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);

                    if (bitmap != null) {
                        image.setImageBitmap(bitmap);


                    } else {
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







