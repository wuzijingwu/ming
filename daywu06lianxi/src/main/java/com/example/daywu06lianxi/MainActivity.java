package com.example.daywu06lianxi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.daywu06lianxi.Utils.NetWorkUtils;
import com.example.daywu06lianxi.Utils.TakeTook;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnRequest(View view) {
        if (NetWorkUtils.isConnection(this)) {


            getDat();

        } else {

            showNetWorkSettingDiaLog();

        }


    }

    public void getDat() {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //准备执行
                showLoadDiaLog();


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //请求网络完毕是自行

                if (s != null) {
                    Gson gson = new Gson();
                    MeanInfo meanInfo = gson.fromJson(s, MeanInfo.class);
                    Log.e("xmvdfjvfsdnfn", meanInfo.getResult().getData().get(0).getImtro());

                }

                dialog.dismiss();


            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                //更新时执行


            }

            @Override
            protected String doInBackground(String... params) {
//                子线程在此更新

                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection content = (HttpURLConnection) url.openConnection();
                    content.setRequestMethod("GET");
                    content.setConnectTimeout(5000);
                    content.setReadTimeout(5000);
                    int responseCode = content.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = content.getInputStream();
                        String json = TakeTook.read(is);
//                        Gson gson = new Gson();
//                        MeanInfo meanInfo = gson.fromJson(json, MeanInfo.class);

                        return json;


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;


            }
        }.execute("http://apis.juhe.cn/cook/query.php?key=ff00d7339861c7fd7d5b54b16b76422a&menu=" + URLEncoder.encode("黄焖鸡"));

    }

    public void showLoadDiaLog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载中。。。。");
        dialog.show();
    }

    public void showNetWorkSettingDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("没有网咯，请设置");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent();
                intent.setAction("android.settings.WIRELESS_SETTINGS");
                startActivity(intent);


            }
        });

    }


}
