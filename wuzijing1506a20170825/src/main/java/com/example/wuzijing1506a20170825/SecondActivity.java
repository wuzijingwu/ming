package com.example.wuzijing1506a20170825;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by dell on 2017/8/25.
 */

public class SecondActivity extends Activity {

    private TextView text;
    private ImageView image;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        text = findViewById(R.id.text);


        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Intent intent = getIntent();
        Intent it = getIntent();
        String StringE = it.getStringExtra("exter");
        text.setText(StringE);


        //compile 'com.github.chrisbanes:PhotoView:2.1.3'在app
        //在onCreate中
        // 大工程里jcenter()下加 maven { url "https://jitpack.io" }
        imageLoader = ImageLoader.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(), "Bwei");
        if (!file.exists())
            file.mkdirs();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(file))
                .build();
        imageLoader.init(configuration);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(StringE, photoView, options);


    }

    public void claerClick(View view) {

        imageLoader.clearDiskCache();

    }


}
