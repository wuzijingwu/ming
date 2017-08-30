package com.example.my;

import android.Manifest;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
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

    private XListView list;
    private int index = 1;
    private boolean flag=false;
    private Mydaapter mydaapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (XListView) findViewById(R.id.list);
        list.setPullLoadEnable(true);
        list.setXListViewListener(this);


        getDatd("http://apis.juhe.cn/cook/query.php", index + "", 20 + "");

    }

    public void getDatd(String path, String pn, String rn) {

        new AsyncTask<String, Void, String>() {



            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean.DataBean> data = menuInfo.getResult().getData();

                    if (mydaapter == null) {
                        mydaapter = new Mydaapter(data);

                        list.setAdapter(new Mydaapter(data));


                    } else {
                        mydaapter.Loadmore(data, flag);
                        mydaapter.notifyDataSetChanged();


                    }


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
                    outputStream.write(("key=15023923d1fc4d0c8f5c538f49d0baab&menu="
                            + URLEncoder.encode("秘制红烧肉", "utf-8") + "&pn=" + pn + "&rn=" + rn).getBytes());

//                    (("key=ff00d7339861c7fd7d5b54b16b76422a&menu="+
//                            URLEncoder.encode("秘制红烧肉","utf-8")+"&pn="+pn+"&rn="+rn).getBytes());


                    outputStream.flush();


                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        String json = StreamStook.read(inputStream);
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

//刷新


        ++index;

        getDatd("http://apis.juhe.cn/cook/query.php", index + "", 10 + "");

        flag = true;
        list.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {

//加载跟多条目
        ++index;
        getDatd("http://apis.juhe.cn/cook/query.php", index + "", 10 + "");
        flag = false;
        list.stopLoadMore();
    }


    class Mydaapter extends BaseAdapter {
        List<MenuInfo.ResultBean.DataBean> data;

        public Mydaapter(List<MenuInfo.ResultBean.DataBean> data) {

            this.data = data;

        }

        public void Loadmore(List<MenuInfo.ResultBean.DataBean> datas, boolean flag) {

            for (MenuInfo.ResultBean.DataBean bean : datas) {

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
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = new TextView(MainActivity.this);
            textView.setHeight(100);
            textView.setText(data.get(position).getTags());


            return textView;
        }
    }


}
