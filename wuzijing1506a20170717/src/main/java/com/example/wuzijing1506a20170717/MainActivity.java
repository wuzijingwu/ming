package com.example.wuzijing1506a20170717;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import static android.R.attr.path;

/**
 * 姓名：吴子敬
 * 时间：2017，07，17
 * 作用：MainActivity为主类加载图片和文字
 *
 *
 *
 */



public class MainActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        try {
            getDates("http://qhb.2dyt.com/Bwei/news?");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getDates(String path) {

        //同步衣部交互
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ListBean> list = menuInfo.getList();
                    listview.setAdapter(new Myadapter(list));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                            startActivity(intent);
                        }
                    });

                }


            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    OutputStream outputStream = urlConnection.getOutputStream();
//                    +""+ URLEncoder.encode("","utf-8")
                    outputStream.write("page=1&type=7&postkey=ad1AK".getBytes());
                    outputStream.flush();
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

    //继承与BaseAdapter类
    class Myadapter extends BaseAdapter {

        private List<MenuInfo.ListBean> list;

        public Myadapter(List<MenuInfo.ListBean> list) {
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
            ImageView imageitem = (ImageView) convertView.findViewById(R.id.imageitem);
            TextView textviewitem = (TextView) convertView.findViewById(R.id.textitem);
            textviewitem.setText("title:" + list.get(position).getTitle() + "\ndata:" + list.get(position).getDate());
            Loadimage(list.get(position).getPic(), imageitem);
            return convertView;
        }

        private void Loadimage(String path, final ImageView imageitem) {

            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);

                    if (bitmap != null) {
                        imageitem.setImageBitmap(bitmap);


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
//                        OutputStream outputStream = urlConnection.getOutputStream();
//                        outputStream.write("page=1&type=7&postkey=ad1AK".getBytes());
//                        outputStream.flush();


                        int code = urlConnection.getResponseCode();
                        if (code == 200) {
                            InputStream inputStream = urlConnection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
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
