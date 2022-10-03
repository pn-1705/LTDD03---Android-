package com.example.androidapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp1.SQLite.DBHelper;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView img;
    TextView logoText, slgText;
    TextInputLayout til_phone, til_password;
    TextInputEditText edt_phone, edt_password;
    CheckBox checkBox_rememberUP;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.close();

        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_password);
        checkBox_rememberUP = findViewById(R.id.cb_savePassword);

        edt_phone.setText(getIntent().getStringExtra("phone"));
        edt_password.setText(getIntent().getStringExtra("password"));

        img = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logoName);
        slgText = findViewById(R.id.logoSlogan);
        til_phone = findViewById(R.id.til_phone);
        til_password = findViewById(R.id.til_password);

        login_btn = findViewById(R.id.login_btn);
        callSignUp = findViewById(R.id.signUp_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(img, "logo_img");
                pairs[1] = new Pair<View, String>(logoText, "logo_Name_Tran");
                pairs[2] = new Pair<View, String>(slgText, "logo_Slg_Tran");
                pairs[3] = new Pair<View, String>(til_phone, "username_Tran");
                pairs[4] = new Pair<View, String>(til_password, "password_Tran");
                pairs[5] = new Pair<View, String>(login_btn, "btn_LogIn_Tran");
                pairs[6] = new Pair<View, String>(callSignUp, "btn_callSignUp_Tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);

                startActivity(intent, options.toBundle());
            }
        });
    }

    public void rememberUP(String phone, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (status == false) {
            editor.clear();
        } else {
            editor.putString("PHONE", phone);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }

    public void checkLogin(View view) {
        if (edt_phone.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không được để trống!",
                    Toast.LENGTH_LONG).show();
        } else {
            UserDao dao = new UserDao(this);
            String phone = edt_phone.getText().toString();
            String password = edt_password.getText().toString();
            Boolean ckUP = dao.checkPhonePassword(phone, password);
            if (ckUP == true) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công",
                        Toast.LENGTH_SHORT).show();

                user = dao.getByPhone(phone);

                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.putExtra("phone", user.getPhone());
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveUP(View view) {
        String phone = edt_phone.getText().toString();
        String pass = edt_password.getText().toString();
        boolean status = checkBox_rememberUP.isChecked();
        rememberUP(phone, pass, status);
    }
}