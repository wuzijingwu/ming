package com.example.kaoshi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private ViewPager viewPager;
    private XListView xListView;
    private ArrayList<String> arrayList;
    private Timer timer;
    private int index = 0;
    private static final int TAG = 12;
    private int indep = 1;
    private boolean flag = false;
    private ProgressDialog dialog;
    private List<Bean.ResultBean.DataBean> data;
    private SQLiteDatabase db;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG:
                    int index = (int) msg.obj;
                    viewPager.setCurrentItem(index);

                    break;

            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySqLite mySqLite = new MySqLite(this);
        db = mySqLite.getWritableDatabase();






        viewPager = (ViewPager) findViewById(R.id.viewpager);
        xListView = (XListView) findViewById(R.id.xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        arrayList = new ArrayList<>();

        String path1 = "http://www.taoqao.com/uploads/allimg/140425/1-140425212357.jpg";
        String path2 = "http://www.taoqao.com/uploads/allimg/140425/1-140425212400.jpg";
        String path3 = "http://www.taoqao.com/uploads/allimg/140425/1-140425212403.jpg";
        String path4 = "http://www.taoqao.com/uploads/allimg/140425/1-140425212522.jpg";
        String path5 = "http://www.taoqao.com/uploads/allimg/140425/1-140425212525.jpg";
//        tupian.add("http://www.taoqao.com/uploads/allimg/140425/1-140425212357.jpg");
//        tupian.add("http://www.taoqao.com/uploads/allimg/140425/1-140425212400.jpg");
//        tupian.add("http://www.taoqao.com/uploads/allimg/140425/1-140425212403.jpg");
//        tupian.add("http://www.taoqao.com/uploads/allimg/140425/1-140425212522.jpg");
//        tupian.add("http://www.taoqao.com/uploads/allimg/140425/1-140425212525.jpg");
        arrayList.add(path1);
        arrayList.add(path2);
        arrayList.add(path3);
        arrayList.add(path4);
        arrayList.add(path5);

        if (WangLuo.isConnection(this)) {
            getDates("http://apis.juhe.cn/cook/query.php?", indep + "", 10 + "");
        } else {
            show();


        }


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final ImageView imageView = new ImageView(MainActivity.this);
                new ImageLoad(new ImageLoad.ImageCallBack() {
                    @Override
                    public void imagecall(Bitmap bitmap) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        } else {
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        }


                    }
                }).execute(arrayList.get(position));

                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (index < arrayList.size() - 1) {
                    index++;

                } else if (index == arrayList.size() - 1) {

                    index = 0;

                }
                Message msg = new Message();
                msg.what = TAG;
                msg.obj = index;
                handler.sendMessage(msg);

            }
        }, 0, 2000);


    }

    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("没有网络，请设置网络");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction("android.settings.WIRELESS_SETTINGS");
                startActivity(intent);
            }
        });
        builder.create().show();
    }


    public void getDates(String path, String pn, String rn) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("正在玩命加载");
                dialog.show();
            }

            private MyAdapter myAdapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(s, Bean.class);
                    List<Bean.ResultBean.DataBean> data = bean.getResult().getData();

                    if (myAdapter == null) {

                        myAdapter = new MyAdapter(data);
                        xListView.setAdapter(new MyAdapter(data));

                    } else {
                        myAdapter.LoadMore(data, flag);
                        myAdapter.notifyDataSetChanged();
                    }

                    dialog.dismiss();


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
                    outputStream.write(("key=a71e47bbe5a7e7a2ecf068ca9a756032&menu=" +
                            URLEncoder.encode("西红柿炒鸡蛋", "utf-8") + "&pn = " + pn + "&rn=" + rn).getBytes());
                    outputStream.flush();
                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        String json = Utils.read(is);
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
        ++indep;
        getDates("http://apis.juhe.cn/cook/query.php?", indep + "", 10 + "");
        flag = true;
        xListView.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++indep;
        getDates("http://apis.juhe.cn/cook/query.php?", indep + "", 10 + "");
        flag = false;
        xListView.stopLoadMore();

    }

    class MyAdapter extends BaseAdapter {
        private List<Bean.ResultBean.DataBean> data;

        public MyAdapter(List<Bean.ResultBean.DataBean> data) {
            this.data = data;
        }

        public void LoadMore(List<Bean.ResultBean.DataBean> datas, boolean flag) {

            for (Bean.ResultBean.DataBean bean : datas) {
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

                case 1:
                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item1, null);
                    }
                    ImageView imageView1 = (ImageView) convertView.findViewById(R.id.tu1);
                    TextView textView1 = (android.widget.TextView) convertView.findViewById(R.id.wenzi1);
                    textView1.setText(data.get(position).getTitle());
                    getimage(data.get(position).getAlbums().get(0), imageView1);
                    db.insert(data.get(position).getTitle(), "yuekao", null);
                    break;
                case 0:

                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item2, null);
                    }
                    ImageView imageView2 = (ImageView) convertView.findViewById(R.id.tu2);
                    TextView textView2 = (android.widget.TextView) convertView.findViewById(R.id.wenzi2);
                    textView2.setText(data.get(position).getTitle());
                    getimage(data.get(position).getAlbums().get(0), imageView2);
                    db.insert(data.get(position).getTitle(), "yuekao", null);
                    break;


            }


            return convertView;
        }
    }

    public void getimage(String path, ImageView imageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageView, options);


    }


}
