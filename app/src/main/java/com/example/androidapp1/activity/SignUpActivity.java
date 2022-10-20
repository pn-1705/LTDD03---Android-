package com.example.androidapp1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    Button backSignIn, btn_register;
    private TextInputEditText reg_name, reg_phone, reg_email, reg_password, reg_cfpassword;

    private FirebaseAuth mAuth;

    private static final String TAG = SignUpActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Khởi tạo
        mAuth = FirebaseAuth.getInstance();

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

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || cfpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng không để trống",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(cfpassword)) {
                        UserDao dao = new UserDao(SignUpActivity.this);
                        User user = new User();

                        Boolean checkPhone = dao.checkPhone(phone);
                        if (checkPhone == false) {

                            user.setName(name);
                            user.setPhone(phone);
                            user.setEmail(email);
                            user.setPassword(password);

                            dao.insert(user);

//                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                            intent.putExtra("phone", phone);
//                            startActivity(intent);

                            PhoneAuthOptions options =
                                    PhoneAuthOptions.newBuilder(mAuth)
                                            .setPhoneNumber(phone)       // Phone number to verify
                                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                            .setActivity(SignUpActivity.this)                 // Activity (for callback binding)
                                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                @Override
                                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                                }

                                                @Override
                                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                                    Toast.makeText(SignUpActivity.this, "Xác thực thất bại!", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                    super.onCodeSent(verificationID, forceResendingToken);
                                                    goToVertifuOTP(phone, verificationID);
                                                }
                                            })          // OnVerificationStateChangedCallbacks
                                            .build();
                            PhoneAuthProvider.verifyPhoneNumber(options);
                        } else {
                            Toast.makeText(SignUpActivity.this, "SĐT đã được đăng kí! Vui lòng đăng nhập!",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.putExtra("phone", reg_phone.getText().toString());
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Vui lòng xác nhận đúng mật khẩu!",
                                Toast.LENGTH_LONG).show();
                    }
                }
                //Xác thực số điện thoại
            }
        });

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI

                            goToSignInActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SignUpActivity.this, "Mã đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToSignInActivity(String phoneNumber) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
    }

    private void goToVertifuOTP(String phone, String verificationID) {
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNumberActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("mVerificationID", verificationID);
        startActivity(intent);
    }
}