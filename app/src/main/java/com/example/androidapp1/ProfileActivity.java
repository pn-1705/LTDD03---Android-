package com.example.androidapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back_Home, view_LVUser;
    private TextInputEditText edt_phone, edt_email, edt_name, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.back_home).setOnClickListener(this);
        findViewById(R.id.view_ListViewUser).setOnClickListener(this);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_phone = findViewById(R.id.edt_phone);

        UserDao dao = new UserDao(this);
        User user = new User();

        user = dao.getByPhone(getIntent().getStringExtra("phone"));

        edt_name.setText(user.getName());
        edt_email.setText(user.getEmail());
        edt_phone.setText(user.getPhone());
        edt_password.setText(user.getPassword());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_home:
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.view_ListViewUser:
                Intent intent1 = new Intent(ProfileActivity.this, LvUsersActivity.class);
                startActivity(intent1);
                break;
        }
    }
}