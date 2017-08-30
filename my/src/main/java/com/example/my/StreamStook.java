package com.example.my;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/18.
 */

public class StreamStook {
    public static String read(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {

            byteArrayOutputStream.write(bytes, 0, len);


        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();
    }


}
