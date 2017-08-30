package com.bawei.qqdang;

import com.mob.MobSDK;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by Administrator on 2017/8/4.
 * 1505D
 * 郝健澄
 */

public class App extends com.andy.share.App {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        MobSDK.init(this, "1ff5ed71aa72a", "1f878402692891a029781339935f3804");
    }
}
