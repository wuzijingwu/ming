package com.example.wuzijing20170728;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 姓名：吴子敬
 * 时间：20170728
 * 作用：StreamTook读取数据
 */

public class StreamTook {
    public static String read(InputStream is) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);


        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();


    }


}
