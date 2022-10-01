package com.example.androidapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    Button backSignIn, btn_register;
    private TextInputLayout reg_name, reg_phone, reg_email, reg_password, reg_cfpassword;

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

        //Nhấn đăng ki tài khoản
        btn_register = findViewById(R   .id.btn_register);

        reg_name = findViewById(R.id.name);
        reg_phone = findViewById(R.id.phone);
        reg_email = findViewById(R.id.email);
        reg_password = findViewById(R.id.password);
        reg_cfpassword = findViewById(R.id.confirm_password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao dao = new UserDao(SignUpActivity.this);
                User user = new User();

                user.setName(reg_name.getEditText().toString());
                user.setPhone(reg_phone.getEditText().toString());
                user.setEmail(reg_email.getEditText().toString());
                user.setPassword(reg_password.getEditText().toString());

                dao.insert(user);

                Toast.makeText(SignUpActivity.this, "Đăng kí thành công",
                        Toast.LENGTH_SHORT).show();
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