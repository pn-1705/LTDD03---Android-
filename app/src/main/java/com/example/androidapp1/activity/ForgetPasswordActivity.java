package com.example.androidapp1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class ForgetPasswordActivity extends AppCompatActivity {

    private CountryCodePicker countryCodePicker;
    private TextInputEditText edt_phone_forgetpassword;
    private Button btn_Next;

    private FirebaseAuth mAuth;

    private static final String TAG = SignUpActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        //Ánh xạ
        countryCodePicker = findViewById(R.id.ccp);
        edt_phone_forgetpassword = findViewById(R.id.edt_phone_forgetpassword);
        btn_Next = findViewById(R.id.btn_ContinueForgetPassword);

        String code_ccp = countryCodePicker.getSelectedCountryCodeWithPlus();
        String phone = code_ccp + edt_phone_forgetpassword.getText().toString().trim();

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ForgetPasswordActivity.this, code_ccp + edt_phone_forgetpassword.getText().toString().trim(), Toast.LENGTH_LONG).show();

                UserDao dao = new UserDao(ForgetPasswordActivity.this);

                Boolean checkPhone = dao.checkPhone(code_ccp + edt_phone_forgetpassword.getText().toString().trim());
                Toast.makeText(ForgetPasswordActivity.this, checkPhone+"", Toast.LENGTH_LONG).show();
                if (checkPhone == true) {
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber(code_ccp + edt_phone_forgetpassword.getText().toString())       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(ForgetPasswordActivity.this)                 // Activity (for callback binding)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            signInWithPhoneAuthCredential(phoneAuthCredential);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Toast.makeText(ForgetPasswordActivity.this, "Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(verificationID, forceResendingToken);
                                            goToVertifuOTP(code_ccp + edt_phone_forgetpassword.getText().toString(), verificationID);
                                        }
                                    })          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Tài khoản không tồn tại? Vui lòng đăng kí!", Toast.LENGTH_SHORT).show();
                }
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

                            goToChangePasswodActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(ForgetPasswordActivity.this, "Mã đăng nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToChangePasswodActivity(String phoneNumber) {
        Intent intent = new Intent(ForgetPasswordActivity.this, ChangePasswordActivity.class);
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
    }

    private void goToVertifuOTP(String phone, String verificationID) {
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNumberActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("dh", 2);
        intent.putExtra("mVerificationID", verificationID);
        startActivity(intent);
    }
}