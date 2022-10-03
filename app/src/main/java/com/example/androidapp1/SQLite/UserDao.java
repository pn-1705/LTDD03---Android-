package com.example.androidapp1.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidapp1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<User> get(String sql, String... selectArgs) {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            User u = new User();
            u.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            u.setName(cursor.getString(cursor.getColumnIndex("name")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            list.add(u);
        }
        return list;
    }

    public List<User> getAll() {
        String sql = "select * from USERS";

        return get(sql);
    }

    public User getByPhone(String phone) {
        String sql = "select * from USERS where phone = ?";

        List<User> list = get(sql, phone);

        return list.get(0);
    }

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("password", user.getPassword());

        return db.insert("USERS", null, values);
    }

    public long update(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        return db.update("USERS", values, "id =?", new String[]{user.getPhone()});
    }

    public boolean checkPhone(String phone) {
        Cursor cursor = db.rawQuery("select * from USERS where phone = ?", new String[]{phone});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

    public boolean checkPhonePassword(String phone, String pass) {
        Cursor cursor = db.rawQuery("select * from USERS where phone = ? and password = ?", new String[]{phone, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }
}
