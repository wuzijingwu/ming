package com.example.kaoshi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 北城 on 2017/7/26.
 */

public class Utils {
    public static String read(InputStream is)throws Exception{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1){
            bao.write(bytes,0,len);
        }
        is.close();
        bao.close();
        return bao.toString();
    }
}
