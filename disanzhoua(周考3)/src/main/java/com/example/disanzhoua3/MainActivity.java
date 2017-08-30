package com.example.disanzhoua3;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {


    private XListView listView;
    private int indexp = 0;
    private boolean flag;
    private String count;
    private String start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (XListView) findViewById(R.id.listview);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);

        try {

            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8") + "&start=" + start + "&count=" + count, indexp + "", 100 + "");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //    , String start, String count
    public void getDates(String path, String start, String count) {

        new AsyncTask<String, Void, String>() {

            private Myadapter myadapter;
            private String count;
            private String start;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.SubjectsBean> list = menuInfo.getSubjects();
//                    listView.setAdapter(new Myadapter(list));


                    if (myadapter == null) {

                        myadapter = new Myadapter(list);
                        listView.setAdapter(new Myadapter(list));
                    } else {
                        myadapter.LoadMore(list, flag);
                        myadapter.notifyDataSetChanged();

                    }


                }


            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    start = params[1];
                    count = params[2];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
//                    OutputStream outputStream = urlConnection.getOutputStream();
//                    outputStream.write(("apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8")).getBytes());
//                    outputStream.flush();
//                    "&start=" + start + "&count=" + count

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
        }.execute(path, start, count);


    }

    @Override
    public void onRefresh() {
        try {
            ++indexp;
//            getDates("https://api.douban.com/v2/movie/in_theaters?", indexp + "", 10 + "");
//            getDates("https://api.douban.com/v2/movie/in_theaters?");
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8"));
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=10&city=" + URLEncoder.encode("北京", "utf-8"));
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=10&city=" + URLEncoder.encode("北京", "utf-8"),indexp+"",10+"");
            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8") + "&start=" + start + "&count=" + count, indexp + "", 100 + "");

            flag = true;

            listView.stopRefresh(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoadMore() {
        try {
            ++indexp;
//        getDates("https://api.douban.com/v2/movie/in_theaters?");
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8"), indexp + "", 10 + "");
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8"));
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=10&city=" + URLEncoder.encode("北京", "utf-8"));
//            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&start=0&count=10&city=" + URLEncoder.encode("北京", "utf-8"),indexp+"",10+"");
            getDates("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=" + URLEncoder.encode("北京", "utf-8") + "&start=" + start + "&count=" + count, indexp + "", 100 + "");

            flag = false;
            listView.stopLoadMore();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class Myadapter extends BaseAdapter {

        List<MenuInfo.SubjectsBean> list;

        public Myadapter(List<MenuInfo.SubjectsBean> list) {

            this.list = list;

        }

        public void LoadMore(List<MenuInfo.SubjectsBean> list1, boolean flag) {

            for (MenuInfo.SubjectsBean beans : list) {

                if (flag) {

                    list.add(0, beans);

                } else {
                    list.add(beans);
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

                return 0;//偶数

            } else {
                return 1;//奇数

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
                     ImageView image1 = (ImageView) convertView.findViewById(R.id.image1);
                    ImageView image2 = (ImageView) convertView.findViewById(R.id.image2);
                    ImageView image3 = (ImageView) convertView.findViewById(R.id.image3);
                    getImage(list.get(position).getCasts().get(0).getAvatars().getLarge(),image1);
                    getImage(list.get(position).getCasts().get(0).getAvatars().getMedium(),image2);
                    getImage(list.get(position).getCasts().get(0).getAvatars().getSmall(),image3);
                }

                break;

                case 1: {

                    if (convertView == null) {
                        convertView = convertView.inflate(MainActivity.this, R.layout.item1, null);
                    }
                    TextView textView1 = (TextView) convertView.findViewById(R.id.text);
                    textView1.setText(list.get(position).getTitle() + "\n" + list.get(position).getOriginal_title());
                }
                break;
            }


            return convertView;
        }
    }

    private void getImage(String path, ImageView image) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_empty)
                .showImageOnFail(R.mipmap.ic_error)
                .build();
        ImageLoader.getInstance().displayImage(path, image, options);
    }


}
