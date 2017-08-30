package com.example.wuzijing1506a20170710;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.jar.Manifest;

/**
 * 姓名：吴子敬
 * 时间：2017.07.10 *
 * 作用：在listview中显示请求的数据
 *
 *
 */


public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private List<Chaidan.ResultBean.DataBean> data;


    //handler发送消息
    Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 1:
                    Chaidan chaidan = (Chaidan) msg.obj;
//                    result> ResultBean> DataBean
                    data = chaidan.getResult().getData();

                    listview.setAdapter(new Myadapter());
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
}
                    });





                    break;


            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //找控件
        listview = (ListView) findViewById(R.id.listView);

//        获取数据
        getDates();


    }

    public void getDates() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                getdatggg();

            }
        }.start();


    }

    public void getdatggg() {

        try {
            //网络请求
            URL url = new URL("http://apis.juhe.cn/cook/query");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //post的方法
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStream os = connection.getOutputStream();
            os.write("key=9a3b48705b2c121641e6ed769ac1e677&rn=10&pn=3&menu=红烧肉".getBytes());
            os.flush();

            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                String json = TakeToos.read(is);
                Gson gson = new Gson();
                Chaidan chaidan = gson.fromJson(json, Chaidan.class);
                //发送一个消息
                Message msg = new Message();
                msg.what = 1;
                msg.obj = chaidan;
                handler.sendMessage(msg);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //做listview的优化
    class Myadapter extends BaseAdapter {


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
                convertView = convertView.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
                TextView text = (TextView) convertView.findViewById(android.R.id.text1);
                text.setText("Imtro:" + data.get(position).getImtro() + "\nIngredients" + data.get(position).getIngredients());

            }


            return convertView;
        }
    }


}
