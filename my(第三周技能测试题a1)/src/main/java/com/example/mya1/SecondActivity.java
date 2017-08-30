package com.example.mya1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by dell on 2017/7/24.
 */

public class SecondActivity extends Activity implements XListView.IXListViewListener {


    private XListView listView;
    private int index;
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        listView = (XListView) findViewById(R.id.listview);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SecondActivity.this, ThreeActivity.class);
                startActivity(intent);


            }
        });
        getDatedd("http://ic.snssdk.com/2/article/v25/stream/?");


    }

    public void getDatedd(String path) {

        new AsyncTask<String, Void, String>() {

            private MYAdapter myAdapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {

                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.DataBean> data = menuInfo.getData();
                    if (myAdapter == null) {
                        myAdapter = new MYAdapter(data);
                        listView.setAdapter(new MYAdapter(data));
                    } else {
                        myAdapter.LoadMore(data, flag);
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
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write("category=news_tech&count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457695555&loc_mode=5&lac=4527&cid=28883&iid=3835029558&device_id=12211880440&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284041361761&openudid=84c1c7b192991cc6".getBytes());
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

    @Override
    public void onRefresh() {
        ++index;
        getDatedd("http://ic.snssdk.com/2/article/v25/stream/?");
        flag = true;
        listView.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++index;
        getDatedd("http://ic.snssdk.com/2/article/v25/stream/?");
        flag = false;
        listView.stopLoadMore();

    }

    class MYAdapter extends BaseAdapter {

        List<MenuInfo.DataBean> data;
        private TextView textView;

        public MYAdapter(List<MenuInfo.DataBean> data) {
            this.data = data;
        }

        public void LoadMore(List<MenuInfo.DataBean> datas, boolean flag) {
            for (MenuInfo.DataBean bean : data) {
                if (flag) {
                    data.add(0, bean);
                } else {
                    data.add(bean);
                }
            }
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

                        convertView = convertView.inflate(SecondActivity.this, R.layout.item, null);
                    }
                    textView = (TextView) convertView.findViewById(R.id.text);
                    textView.setText(data.get(position).getTitle() + "\n" + data.get(position).getSource());


                }

                break;


                case 1: {
                    if (convertView == null) {

                        convertView = convertView.inflate(SecondActivity.this, R.layout.item1, null);
                    }
                    ImageView imge1 = (ImageView) convertView.findViewById(R.id.imge1);
                    ImageView imge2 = (ImageView) convertView.findViewById(R.id.imge2);
                    ImageView imge3 = (ImageView) convertView.findViewById(R.id.imge3);
                    getImage(data.get(position).getMedia_info().getAvatar_url(), imge1);
                    getImage(data.get(position).getMedia_info().getAvatar_url(), imge2);
                    getImage(data.get(position).getMedia_info().getAvatar_url(), imge3);
                }

                break;


            }


            return convertView;
        }
    }

    public void getImage(String path, ImageView image) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(path, image, options);
    }


}
