package com.example.wuzijing1506a20170821;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by dell on 2017/8/21.
 */

public class MyFragment extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView xlistview;
    private int p = 1;
    private ImageLoader imageLoader;
    private boolean flag;
    private MyAdapters adapter;
    private MyopenHelper myopenHelper;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.myfragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myopenHelper = new MyopenHelper(getActivity());
        db = myopenHelper.getWritableDatabase();
        xlistview = view.findViewById(R.id.xlistview);
        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);

        getDates("http://api.expoon.com/AppNews/getNewsList/type/1/", p + "");
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            Bean1 bean1 = new Bean1(title, image);
        }
    }


    public void getDates(String path, final String page) {

        new AsyncTask<String, Void, String>() {


            private List<MenuInfo.DataBean> data;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    data = menuInfo.getData();

                    if (adapter == null) {
                        adapter = new MyAdapters(data);
                        xlistview.setAdapter(adapter);


                    } else {
                        adapter.LoadMores(data, flag);
                        adapter.notifyDataSetChanged();
                    }


                    xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            System.out.println(position);


                            final ImageView image = (ImageView) view.findViewById(R.id.image);
                            TextView text = (TextView) view.findViewById(R.id.text);
                            View view2 = View.inflate(getActivity(), R.layout.pop, null);
                            final PopupWindow pop = new PopupWindow(view2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            TextView delete = (TextView) view2.findViewById(R.id.delete);
                            ImageView dismiss = (ImageView) view2.findViewById(R.id.dismiss);
                            TextView shoucang = (TextView) view2.findViewById(R.id.shoucang);
                            TextView read = (TextView) view2.findViewById(R.id.read);

                            image.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    int[] a = new int[2];
                                    image.getLocationOnScreen(a);
                                    pop.setFocusable(true);
                                    // pop.setTouchable(true);
                                    pop.setBackgroundDrawable(new BitmapDrawable());
                                    pop.setOutsideTouchable(true);
                                    pop.showAtLocation(image, 0, a[0], a[1]);

                                    // pop.showAsDropDown(imageView);

                                }
                            });

                            delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    data.remove(position - 1);
                                    adapter.notifyDataSetChanged();
                                    pop.dismiss();
                                }
                            });
                            dismiss.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    pop.dismiss();
                                }
                            });


                        }
                    });


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
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(("p=" + page).getBytes());
                    os.flush();


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
        ++p;
        getDates("http://api.expoon.com/AppNews/getNewsList/type/1/", p + "");
        flag = true;
        xlistview.stopRefresh(true);


    }

    @Override
    public void onLoadMore() {
        ++p;
        getDates("http://api.expoon.com/AppNews/getNewsList/type/1/", p + "");
        flag = false;
        xlistview.stopLoadMore();

    }

    class MyAdapters extends BaseAdapter {
        List<MenuInfo.DataBean> data;
        private final DisplayImageOptions options;

        public MyAdapters(List<MenuInfo.DataBean> data) {
            this.data = data;
            imageLoader = ImageLoader.getInstance();
            File file = new File(Environment.getExternalStorageDirectory(), "/vkokifct");
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

        public void LoadMores(List<MenuInfo.DataBean> datas, boolean flag) {
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
                viewHolder.text = convertView.findViewById(R.id.textitem);
                viewHolder.imagess = convertView.findViewById(R.id.imageitem);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text.setText(data.get(position).getNews_title());
            getimage(data.get(position).getPic_url(), viewHolder.imagess);
            return convertView;
        }

        class ViewHolder {
            TextView text;
            ImageView imagess;
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
