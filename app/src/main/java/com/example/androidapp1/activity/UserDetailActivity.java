package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidapp1.R;
import com.example.androidapp1.model.User;

public class UserDetailActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Bundle bundle = getIntent().getExtras();

        User user = (User) bundle.get("object");

        name = findViewById(R.id.name);
        name.setText(user.getName());
    }
}