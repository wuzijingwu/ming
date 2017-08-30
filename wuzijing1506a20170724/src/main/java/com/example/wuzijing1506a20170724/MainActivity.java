package com.example.wuzijing1506a20170724;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * 姓名：吴子敬
 * 时间：20170724
 * 作用：MainActivity加载条目
 *
 *
 *
 *
 */



public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xListView;
    private int indexp = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);


        getDatae("http://qhb.2dyt.com/Bwei/news?");


    }

    public void getDatae(String path) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenInfo menInfo = gson.fromJson(s, MenInfo.class);
                    List<String> listViewPager = menInfo.getListViewPager();
                    xListView.setAdapter(new MyAdapter(listViewPager));

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
                    outputStream.write("type=5&postkey=ad1AK".getBytes());
                    outputStream.flush();

                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                    }
                    InputStream is = urlConnection.getInputStream();
                    String json = StreamTook.read(is);
                    return json;
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
        getDatae("http://qhb.2dyt.com/Bwei/news?");


        xListView.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++indexp;
        getDatae("http://qhb.2dyt.com/Bwei/news?");


        xListView.stopLoadMore();
    }

    class MyAdapter extends BaseAdapter {
        List<String> listViewPager ;

        public MyAdapter( List<String> listViewPager ) {
            this.listViewPager = listViewPager;

        }

        @Override
        public int getCount() {
            return listViewPager.size();
        }

        @Override
        public Object getItem(int position) {
            return listViewPager.get(position);
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
                case 0:
                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item2, null);
                    }
                     ImageView image1item2 = (android.widget.ImageView) convertView.findViewById(R.id.image1item2);
                     ImageView image2item2 = (android.widget.ImageView) convertView.findViewById(R.id.image2item2);
                     ImageView image3item2 = (android.widget.ImageView) convertView.findViewById(R.id.image3item2);
                     ImageView image4item2 = (android.widget.ImageView) convertView.findViewById(R.id.image4item2);
                    getImage(listViewPager.get(position),image1item2);
                    getImage(listViewPager.get(position),image2item2);
                    getImage(listViewPager.get(position),image3item2);
                    getImage(listViewPager.get(position),image4item2);

                    break;

                case 1:

                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item1, null);

                    }
                    ImageView image1 = (android.widget.ImageView) convertView.findViewById(R.id.image1);
                    ImageView image2 = (android.widget.ImageView) convertView.findViewById(R.id.image2);
                    getImage(listViewPager.get(position),image1);
                    getImage(listViewPager.get(position),image2);



                    break;


            }


            return convertView;
        }
    }


    private void getImage(String path, ImageView imageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoader.getInstance().displayImage(path,imageView,options);
    }


}
