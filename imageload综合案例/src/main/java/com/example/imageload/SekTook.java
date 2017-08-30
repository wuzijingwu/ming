package com.example.imageload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by dell on 2017/7/11.
 */

public class SekTook {

    public static String makeRead(InputStream is) throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=is.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,len);

        }
        byteArrayOutputStream.close();
        return  byteArrayOutputStream.toString();


    }

}
