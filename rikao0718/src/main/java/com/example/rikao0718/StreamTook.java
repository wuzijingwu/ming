package com.example.rikao0718;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/18.
 */

public class StreamTook {
    public static String read(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);


        }

        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();

    }


}
