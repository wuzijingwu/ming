package com.example.wuzijing20170725rikao;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xListView;
    private int index = 1;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.XListView);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        try {
            getDates("http://v.juhe.cn/movie/index?key=392ad16f3aaca029ee5e55764f8f883f&title=" + URLEncoder.encode("哥斯拉", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getDates(String path) {

        new AsyncTask<String, Void, String>() {

            private MyAdapter myAdapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean> list = menuInfo.getResult();


                    if (myAdapter == null) {
                        myAdapter = new MyAdapter(list);

                        xListView.setAdapter(new MyAdapter(list));

                    } else {
                        myAdapter.LoadMore(list, flag);

                        myAdapter.notifyDataSetChanged();
                    }


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
        try {
            ++index;
            getDates("http://v.juhe.cn/movie/index?key=392ad16f3aaca029ee5e55764f8f883f&title=" + URLEncoder.encode("哥斯拉", "utf-8"));
            flag = true;

            xListView.stopRefresh(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoadMore() {
        try {
            ++index;
            getDates("http://v.juhe.cn/movie/index?key=392ad16f3aaca029ee5e55764f8f883f&title=" + URLEncoder.encode("哥斯拉", "utf-8"));
            flag = false;
            xListView.stopLoadMore();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter {
        List<MenuInfo.ResultBean> list;

        public MyAdapter(List<MenuInfo.ResultBean> list) {

            this.list = list;


        }

        public void LoadMore(List<MenuInfo.ResultBean> lists, boolean flag) {

            for (MenuInfo.ResultBean bean : list) {

                if (flag) {

                    list.add(0, bean);


                } else {

                    list.add(bean);
                }


            }


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
                        convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                    }
                    TextView textView = (TextView) convertView.findViewById(R.id.textitem1);
                    ImageView imageVie = (ImageView) convertView.findViewById(R.id.imageitem1);
                    textView.setText(list.get(position).getTitle());
                    getimage(list.get(position).getPoster(), imageVie);

                }


                break;

                case 1: {

                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item2, null);
                    }
                    ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imageitem2);
                    TextView textView2 = (TextView) convertView.findViewById(R.id.textitem2);
                    getimage(list.get(position).getPoster(), imageView2);
                    textView2.setText(list.get(position).getLanguage());


                }


                break;


            }


            return convertView;
        }
    }

    public void getimage(String path, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoader.getInstance().displayImage(path, imageView, options);


    }


}
