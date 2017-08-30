package com.example.wuzijing1506a20170717;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 姓名：吴子敬
 * 时间：2017，07，17
 * 作用：做一个输出方法
 *
 *
 *
 */
public class StreamTook {

    public static String read(InputStream is) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {

            byteArrayOutputStream.write(buffer, 0, len);


        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();

    }


}
