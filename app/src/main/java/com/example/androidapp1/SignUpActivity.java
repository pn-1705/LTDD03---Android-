package com.example.androidapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    Button backSignIn;
    TextInputLayout reg_name, reg_phone, reg_password, reg_cfpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Đã có tài khoản, quay lại đăng nhập
        backSignIn = findViewById(R.id.back_SignIn);
        backSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


//        public void registerUser(View view){
//            String name = reg_name.getEditText().getText().toString();
//            String phone = reg_name.getEditText().getText().toString();
//            String password = reg_name.getEditText().getText().toString();
//            String cfpassword = reg_name.getEditText().getText().toString();
//            UserHelpClass helpClass = new UserHelpClas
//        }
    }
}