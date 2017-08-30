package com.example.imagesss;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.imagesss.bean.HealthInfos;
import com.example.imagesss.utils.StreamTool;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView) findViewById(R.id.lv);


        //请求数据
        loadData("http://op.juhe.cn/yi18/news/list?key=6c0d8a8f3f374893d6e2cad284c1dbc2");

    }

    /**
     * 加载数据
     * @param path
     */
    private void loadData(String path){


        new AsyncTask<String,Void,String>(){

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                if(s == null)
                    return;

                //不为空

                Gson gson = new Gson();

                HealthInfos healthInfo = gson.fromJson(s, HealthInfos.class);

                List<HealthInfos.ResultBean.ListBean> list = healthInfo.getResult().getList();

                //设置适配器
                lv.setAdapter(new MyBaseAdapter(list));


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

                    if(code == 200){
                        InputStream is = connection.getInputStream();
                        String json = StreamTool.readFromNetWork(is);
                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);


    }




    class MyBaseAdapter extends BaseAdapter{

        private  List<HealthInfos.ResultBean.ListBean> list;
        private DisplayImageOptions options;

        public MyBaseAdapter( List<HealthInfos.ResultBean.ListBean> list){
            this.list = list;

            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)//是否内存缓存
                    .cacheOnDisk(true)//是否sdcard缓存
                    .build();


        }


        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
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


            if(convertView == null){

                convertView = convertView.inflate(MainActivity.this,R.layout.item,null);
            }



            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);

            TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);



            //设置文本信息
            tvContent.setText(list.get(position).getTitle());

            //设置图片

            ImageLoader.getInstance().displayImage(list.get(position).getImg(),ivIcon,options);

            return convertView;
        }
    }




}
