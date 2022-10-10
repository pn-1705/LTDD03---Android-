package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_edProfile;
    private TextInputEditText edt_phone, edt_email, edt_name, edt_password;
    private TextView fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.back_home).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
        btn_edProfile = findViewById(R.id.btn_editprofile);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_phone = findViewById(R.id.edt_phone);

        edt_name.setEnabled(false);
        edt_email.setEnabled(false);
        edt_phone.setEnabled(false);
        edt_password.setEnabled(false);

        UserDao dao = new UserDao(this);
        User user = new User();

        user = dao.getByPhone(getIntent().getStringExtra("phone"));

        edt_name.setText(user.getName());
        edt_email.setText(user.getEmail());
        edt_phone.setText(user.getPhone());
        edt_password.setText(user.getPassword());

        fullname = findViewById(R.id.profile_fullname);
        fullname.setText(user.getName());

        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn_edProfile.setText("Lưu");
                btn_edProfile.setBackgroundColor(Color.parseColor("#fece2f"));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_home:
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent2 = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_editprofile:
                edit_information(view);
                break;
        }
    }

    public void edit_information(View view) {
        edt_name.setEnabled(true);
        edt_email.setEnabled(true);
        edt_password.setEnabled(true);
    }
}