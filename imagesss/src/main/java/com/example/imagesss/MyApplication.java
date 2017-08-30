package com.example.imagesss;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


       String path = Environment.getExternalStorageDirectory().getPath()+"/Health";
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
