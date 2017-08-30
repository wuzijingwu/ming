package com.example.wuzijing1506a20170821;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2017/8/21.
 */

public class MyopenHelper extends SQLiteOpenHelper {
    public MyopenHelper(Context context) {
        super(context, "bawe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(title varchar(100),image varchar(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
