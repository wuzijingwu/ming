package com.example.wuzijing1506a20170710;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 姓名：吴子敬
 * 时间：2017.07.10 *
 * 作用：做一个类用来读取数据的方法
 *
 *
 */

public class TakeToos {
    public static String read(InputStream is) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {

            out.write(buffer, 0, len);

        }
        out.close();
        return out.toString();

    }


}
