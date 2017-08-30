package com.example.wuzijing1506a20170825;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by dell on 2017/8/25.
 */

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        db = myOpenHelper.getWritableDatabase();


    }

    public boolean add(String news_title) {
//        "create table yuekao(abstractX varchar(1000))
        ContentValues values = new ContentValues();
        values.put("news_title", news_title);
        long yuekao = db.insert("yuekao", null, values);
        if (yuekao != -1) {
            return true;


        }


        return false;
    }

    public ArrayList<Data> findData() {
//news_title
        Cursor cursor = db.query(false, "yuekao", null, null, null, null, null, null, null);
        ArrayList<Data> datas = new ArrayList<Data>();

        while (cursor.moveToNext()) {
            String news_title = cursor.getString(cursor.getColumnIndex("news_title"));
            Data data = new Data();
            data.setNews_title(news_title);
            datas.add(data);
        }
        return datas;
    }

}
