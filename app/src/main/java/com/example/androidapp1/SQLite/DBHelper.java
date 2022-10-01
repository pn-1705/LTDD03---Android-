package com.example.androidapp1.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_name = "AndroidApp";
    public static final int DB_version = 1;

    public DBHelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table NHANVIEN("
                + "phone text primary key, "
                + "email text not null, "
                + "name text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists NHANVIEN";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
