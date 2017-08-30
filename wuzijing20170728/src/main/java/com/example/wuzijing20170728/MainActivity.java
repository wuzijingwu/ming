package com.example.wuzijing20170728;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
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

/**
 * 姓名：吴子敬
 * 时间：20170728
 * 作用：MainActivity用于显示轮播图和listview的主界面
 */


public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private ViewPager viewPager;
    private XListView xListView;
    private ArrayList<String> strings;
    private Timer timer;
    private int index = 0;
    private static final int TAG = 12;
    private int indexp = 1;
    private boolean flag;

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
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        xListView = (XListView) findViewById(R.id.xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("标题" + "\n" + "应用程序无响应");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", null);
                builder.create().show();


            }
        });
        String path1 = "http://p3.pstatp.com/list/190x124/1c19000062675e53b27e";
        String path2 = "http://p3.pstatp.com/list/190x124/26ed0005636b714a9f61";
        String path3 = "http://p3.pstatp.com/list/190x124/213300016c777190f9ed";
        String path4 = "http://p3.pstatp.com/list/190x124/26ed0005636b714a9f61";


        strings = new ArrayList<>();
        strings.add(path1);
        strings.add(path2);
        strings.add(path3);
        strings.add(path4);
        getDates("http://apis.juhe.cn/cook/query");
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return strings.size();
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
                }).execute(strings.get(position));
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
                if (index < strings.size() - 1) {
                    index++;
                } else if (index == strings.size() - 1) {

                    index = 0;
                }
                Message msg = new Message();
                msg.what = TAG;
                msg.obj = index;
                handler.sendMessage(msg);

            }
        }, 0, 2000);


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
                    List<MenuInfo.ResultBean.DataBean.StepsBean> steps = menuInfo.getResult().getData().get(0).getSteps();

                    if (myAdapter == null) {
                        myAdapter = new MyAdapter(steps);
                        xListView.setAdapter(new MyAdapter(steps));
                    } else {
                        myAdapter.LoadMore(steps, flag);
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
                    outputStream.write(("key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("经典红烧肉", "utf-8")).getBytes());
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
        ++indexp;
        getDates("http://apis.juhe.cn/cook/query");
        flag = true;
        xListView.stopRefresh(true);
    }

    @Override
    public void onLoadMore() {
        ++indexp;
        getDates("http://apis.juhe.cn/cook/query");
        flag = false;
        xListView.stopRefresh(true);

    }

    class MyAdapter extends BaseAdapter {
        private List<MenuInfo.ResultBean.DataBean.StepsBean> steps;

        public MyAdapter(List<MenuInfo.ResultBean.DataBean.StepsBean> steps) {
            this.steps = steps;
        }

        public void LoadMore(List<MenuInfo.ResultBean.DataBean.StepsBean> stepsd, boolean flag) {
            for (MenuInfo.ResultBean.DataBean.StepsBean bean : stepsd) {
                if (flag) {

                    steps.add(0, bean);
                } else {
                    steps.add(bean);
                }


            }


        }


        @Override
        public int getCount() {
            return steps.size();
        }

        @Override
        public Object getItem(int position) {
            return steps.get(position);
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
                        convertView = convertView.inflate(MainActivity.this, R.layout.item2, null);
                    }
                    TextView textitem2 = (TextView) convertView.findViewById(R.id.textitem2);
                    ImageView image1item2 = (ImageView) convertView.findViewById(R.id.image1item2);
                    getimager(steps.get(position).getImg(), image1item2);
                    textitem2.setText("title");
                }
                break;

                case 1:

                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item1, null);
                    }
                    TextView textitem1 = (TextView) convertView.findViewById(R.id.textitem1);
                    ImageView image1item1 = (ImageView) convertView.findViewById(R.id.image1item1);
                    ImageView image2item1 = (ImageView) convertView.findViewById(R.id.image2item1);
                    ImageView image3item1 = (ImageView) convertView.findViewById(R.id.image3item1);
                    ImageView image4item1 = (ImageView) convertView.findViewById(R.id.image4item1);
                    getimager(steps.get(position).getImg(), image1item1);
                    getimager(steps.get(position).getImg(), image2item1);
                    getimager(steps.get(position).getImg(), image3item1);
                    getimager(steps.get(position).getImg(), image4item1);
                    textitem1.setText("title");
                    break;
            }

            return convertView;
        }
    }

    public void getimager(String path, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageView, options);

    }


}
