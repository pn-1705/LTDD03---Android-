package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    Button backSignIn, btn_register;
    private TextInputEditText reg_name, reg_phone, reg_email, reg_password, reg_cfpassword;

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


        reg_name = findViewById(R.id.name);
        reg_phone = findViewById(R.id.phone);
        reg_email = findViewById(R.id.email);
        reg_password = findViewById(R.id.password);
        reg_cfpassword = findViewById(R.id.confirm_password);
        //Nhấn đăng ki tài khoản
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = reg_name.getText().toString();
                String phone = reg_phone.getText().toString();
                String email = reg_email.getText().toString();
                String password = reg_password.getText().toString();
                String cfpassword = reg_cfpassword.getText().toString();

                if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || cfpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng không để trống",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(cfpassword)){
                        UserDao dao = new UserDao(SignUpActivity.this);
                        User user = new User();

                        Boolean checkPhone = dao.checkPhone(phone);
                        if (checkPhone == false){
                            user.setName(name);
                            user.setPhone(phone);
                            user.setEmail(email);
                            user.setPassword(password);

                            dao.insert(user);

                            Toast.makeText( SignUpActivity.this, "Đăng kí thành công với SĐT " + user.getPhone(),
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.putExtra("phone", reg_phone.getText().toString());
                            intent.putExtra("password", reg_password.getText().toString());
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUpActivity.this, "SĐT đã được đăng kí! Vui lòng đăng nhập!",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.putExtra("phone", reg_phone.getText().toString());
                            startActivity(intent);
                        }
                    }else {
                        Toast.makeText(SignUpActivity.this, "Vui lòng xác nhận đúng mật khẩu!",
                                Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

    }
}