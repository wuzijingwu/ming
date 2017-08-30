package com.example.wuzijing20170727rikao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/27.
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
