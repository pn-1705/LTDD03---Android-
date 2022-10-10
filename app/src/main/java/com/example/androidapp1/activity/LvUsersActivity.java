package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.adapter.UserAdapter;
import com.example.androidapp1.model.User;

import java.util.List;

public class LvUsersActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvusers);

        lvUsers = findViewById(R.id.lv_Users);
        UserDao dao = new UserDao(this);
        List<User> list = dao.getAll();

        userAdapter = new UserAdapter(this, list);
        lvUsers.setAdapter(userAdapter);
    }
}