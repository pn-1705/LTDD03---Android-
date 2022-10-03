package com.example.androidapp1.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_name = "AndroidApp1";
    public static final int DB_version = 1;

    public DBHelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table USERS("
                + "phone text primary key, "
                + "name text not null, "
                + "email text not null, "
                + "password text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists USERS";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
