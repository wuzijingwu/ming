package com.example.zhoukao1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/13.
 */

public class StreamTook {
    public static String read(InputStream is) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       byte[] buffe= new byte[1024];
        int len=0;
        while ((len=is.read(buffe))!=-1){

            byteArrayOutputStream.write(buffe,0,len);

        }
        byteArrayOutputStream.close();
        return  byteArrayOutputStream.toString();


    }


}
