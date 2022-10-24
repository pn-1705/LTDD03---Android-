package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputEditText edt_password, edt_cfpassword;
    Button btn_ChangePassword, btn_Exit;

    String password, cfpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        String phone = getIntent().getStringExtra("phone");

        edt_password.findViewById(R.id.edt_password);
        edt_cfpassword.findViewById(R.id.edt_cfpassword);
        btn_ChangePassword.findViewById(R.id.btn_ChangePassword);
        btn_Exit.findViewById(R.id.btn_Exit);

        //Quay lại trang quên mật khẩu
        btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Tiến hành đổi mật khẩu
        btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = edt_password.getText().toString().trim();
                cfpassword = edt_cfpassword.getText().toString().trim();

                if (password.equals(cfpassword)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng xác nhận lại mật khẩu!", Toast.LENGTH_SHORT).show();
                } else {
                    UserDao dao = new UserDao(ChangePasswordActivity.this);
                    User user = new User();

                    user = dao.getByPhone(phone);

                    user.setPassword(password);
                    dao.update(user);
                }
            }
        });
    }
}