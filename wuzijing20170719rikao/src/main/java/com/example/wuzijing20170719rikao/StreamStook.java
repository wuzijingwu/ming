package com.example.wuzijing20170719rikao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/19.
 */

public class StreamStook {
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
