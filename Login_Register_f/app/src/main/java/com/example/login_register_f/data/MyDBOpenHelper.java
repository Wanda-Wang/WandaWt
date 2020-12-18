package com.example.login_register_f.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    SharedHelper sh;
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {super(context, "my.db", null, 1); }
    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
//        sh=new SharedHelper(getApplicationContext());
        db.execSQL("CREATE TABLE if not exists user_table(TIMES TEXT PRIMARY KEY,USERNAME TEXT,ID TEXT, TITLE TEXT, CONTENT TEXT, AUTHOR TEXT)");

    }
    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
    }
}