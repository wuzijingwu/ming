package com.example.wuzijing1506a20170825;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/8/25.
 */

public class MyFragmnet extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView xlistview;
    private ImageLoader imageLoader;
    private Dao dao;
    private ArrayList<Data> data1;
    private int p = 1;
    private boolean flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myfragment, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xlistview = view.findViewById(R.id.xlistview);
        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);

        dao = new Dao(getActivity());
        dao.add("news_title");

        if (isConnection(getActivity())) {
            getDates("http://api.expoon.com/AppNews/getNewsList/type/2/", p + "");

        } else {
            data1 = dao.findData();
        }


    }

    public boolean isConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }


    public void getDates(String path, final String page) {

        new AsyncTask<String, Void, String>() {

            private Mydsapter mydsapter;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    final List<MenuInfo.DataBean> data = menuInfo.getData();
                    if (mydsapter == null) {
                        mydsapter = new Mydsapter(data);
                        xlistview.setAdapter(mydsapter);
                        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent = new Intent(getActivity(), SecondActivity.class);
                                intent.putExtra("exter",data.get(i-1).getPic_url());

                                startActivity(intent);


                            }
                        });


                        if (data.size() == 0) {
                            for (Data list : data1) {
                                boolean add = dao.add(list.getNews_title());
                                if (add) {
                                    Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();

                                }


                            }


                        }
                    } else {
                        mydsapter.Loadsmore(data, flag);
                        mydsapter.notifyDataSetChanged();

                    }


                }


            }

            @Override
            protected String doInBackground(String... strings) {

                try {
                    String path = strings[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(2000);
                    urlConnection.setReadTimeout(2000);

                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(("p=" + page).getBytes());

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
        }.execute(path, page);


    }

    @Override
    public void onRefresh() {
        p++;
        getDates("http://api.expoon.com/AppNews/getNewsList/type/2/", p + "");
        flag = true;
        xlistview.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        p++;
        getDates("http://api.expoon.com/AppNews/getNewsList/type/2/", p + "");
        flag = false;
        xlistview.stopLoadMore();
    }

    class Mydsapter extends BaseAdapter {
        List<MenuInfo.DataBean> data;
        private final DisplayImageOptions options;

        public Mydsapter(List<MenuInfo.DataBean> data) {
            this.data = data;
            imageLoader = ImageLoader.getInstance();
            File file = new File(Environment.getExternalStorageDirectory(), "/cnvjjwei");
            if (!file.exists())
                file.mkdirs();

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getActivity())
                    .diskCache(new UnlimitedDiskCache(file))
                    .build();

            imageLoader.init(configuration);

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .cacheOnDisk(true)
                    .build();
        }

        public void Loadsmore(List<MenuInfo.DataBean> datas, boolean flag) {
            for (MenuInfo.DataBean bean : datas) {
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = convertView.inflate(getActivity(), R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.textitem = convertView.findViewById(R.id.textitem);
                viewHolder.imageitem = convertView.findViewById(R.id.imageitem);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textitem.setText(data.get(position).getNews_title());
            getimage(data.get(position).getPic_url(), viewHolder.imageitem);
            return convertView;
        }

        class ViewHolder {
            TextView textitem;
            ImageView imageitem;
        }


    }

    public void getimage(String path, ImageView imageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .build();
        ImageLoader.getInstance().displayImage(path, imageView, options);


    }


}
