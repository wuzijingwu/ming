package com.example.my407f;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dell on 2017/7/25.
 */

public class SecondActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        listView = (ListView) findViewById(R.id.listview);
        getDates("http://www.hengboit.com/json/data/data.json");


    }

    public void getDates(String path) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ArrayBean> array = menuInfo.getArray();
                    listView.setAdapter(new MyAdapter(array));


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

    class MyAdapter extends BaseAdapter {
        List<MenuInfo.ArrayBean> array;

        public MyAdapter(List<MenuInfo.ArrayBean> array) {

            this.array = array;

        }

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return array.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = convertView.inflate(SecondActivity.this, R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.tetitem = (TextView) convertView.findViewById(R.id.textitem);
                viewHolder.imageitem = (ImageView) convertView.findViewById(R.id.imagetitem);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tetitem.setText(array.get(position).getSource() + "\n" + array.get(position).getTitle() + "\n" + array.get(position).getContent());
            getimage(array.get(position).getPic(), viewHolder.imageitem);

            return convertView;
        }

        class ViewHolder {

            TextView tetitem;
            ImageView imageitem;
        }
    }

    public void getimage(String path, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageView, options);
    }


}
