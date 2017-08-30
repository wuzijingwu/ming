package com.example.imageload;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by dell on 2017/7/21.
 */

public class MyAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String path = Environment.getDownloadCacheDirectory().getPath() + "/Heads";
        File file = new File(path);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(200,200)
                .memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .threadPriority(1000)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileCount(50)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // .writeDebugLogs() 打印加载图片的log日志，跟据自己的需求配置
                .diskCacheSize(50 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
