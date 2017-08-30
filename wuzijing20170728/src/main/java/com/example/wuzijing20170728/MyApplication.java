package com.example.wuzijing20170728;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * 姓名：吴子敬
 * 时间：20170728
 * 作用：MyApplication做一个框架
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String s = Environment.getExternalStorageDirectory() + "/baweikaoshi";
        File file = new File(s);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(200, 200)
                .threadPoolSize(3)
                .threadPriority(1000)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(50)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
         ImageLoader.getInstance().init(configuration);

    }
}
