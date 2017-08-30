package com.example.myxlistview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView viewxlistview;
    private int index = 1;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewxlistview = (XListView) findViewById(R.id.xlistview);
        viewxlistview.setPullLoadEnable(true);
        viewxlistview.setXListViewListener(this);


        getDates("http://apis.juhe.cn/cook/query.php?", index + "", 10 + "");
//        key=ff00d7339861c7fd7d5b54b16b76422a&menu="+
//        URLEncoder.encode("秘制红烧肉","utf-8")+"&pn="+pn+"&rn="+rn).getBytes()

    }

    public void getDates(String path, String pn, String rn) {

        new AsyncTask<String, Void, String>() {

            private Myadapter myadapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean> list = menuInfo.getResult().getData();


                    if (myadapter == null) {

                        myadapter = new Myadapter(list);
                        viewxlistview.setAdapter(new Myadapter(list));

                    } else {
                        myadapter.LoadMore(list, flag);
                        myadapter.notifyDataSetChanged();

                    }


//

                }


            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    String pn = params[1];
                    String rn = params[2];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(("key=ff00d7339861c7fd7d5b54b16b76422a&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8") + "&pn=" + pn + "&rn=" + rn).getBytes());
                    outputStream.flush();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = urlConnection.getInputStream();
                        String json = StreamTook.read(is);
                        return json;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path, pn, rn);


    }

    @Override
    public void onRefresh() {

        ++index;
        getDates("http://apis.juhe.cn/cook/query.php", index + "", 10 + "");
        flag = true;
        viewxlistview.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++index;
        getDates("http://apis.juhe.cn/cook/query.php", index + "", 10 + "");
        flag = false;
        viewxlistview.stopLoadMore();
    }

    class Myadapter extends BaseAdapter {
        List<MenuInfo.ResultBean.DataBean> list;

        public Myadapter(List<MenuInfo.ResultBean.DataBean> list) {
            this.list = list;
        }

        public void LoadMore(List<MenuInfo.ResultBean.DataBean> lists, boolean flag) {

            for (MenuInfo.ResultBean.DataBean bean : lists) {

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
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);

            }
            TextView text = (TextView) convertView.findViewById(R.id.text);
            text.setText(list.get(position).getTags());
            text.setHeight(100);
            return convertView;
        }
    }


}
