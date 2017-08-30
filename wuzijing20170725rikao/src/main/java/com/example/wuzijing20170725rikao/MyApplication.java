package com.example.wuzijing20170725rikao;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by dell on 2017/7/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String s = Environment.getExternalStorageDirectory() + "/jsjvs";
        File file = new File(s);
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(100,100)
                .memoryCacheSize(2*1024*1024)
                .threadPoolSize(3)
                .threadPriority(1000)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileCount(20)
                .diskCacheSize(50*1024*1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
