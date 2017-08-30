package com.example.wuzijing20170728;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 姓名：吴子敬
 * 时间：20170728
 * 作用：WngLuo用于网络判断
 */

public class WngLuo {

    public static boolean isConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}
