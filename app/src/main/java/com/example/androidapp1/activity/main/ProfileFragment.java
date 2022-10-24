package com.example.androidapp1.activity.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.activity.LoginActivity;
import com.example.androidapp1.activity.MainActivity;
import com.example.androidapp1.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private View mView;
    private Button btn_edProfile, btn_logout;
    private TextInputEditText edt_phone, edt_email, edt_name, edt_password;
    private TextView fullname;
    private String phone, password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        MainActivity activity = (MainActivity) getActivity();
        phone = activity.getMyData();
        createProfileFragment();

        return mView;

    }

    public void createProfileFragment() {
        btn_edProfile = mView.findViewById(R.id.btn_editprofile);
        btn_logout = mView.findViewById(R.id.btn_logout);

        edt_name = mView.findViewById(R.id.edt_name);
        edt_email = mView.findViewById(R.id.edt_email);
        edt_password = mView.findViewById(R.id.edt_password);
        edt_phone = mView.findViewById(R.id.edt_phone);

        edt_name.setEnabled(false);
        edt_email.setEnabled(false);
        edt_phone.setEnabled(false);
        edt_password.setEnabled(false);

        UserDao dao = new UserDao(getActivity());
        User user;

        user = dao.getByPhone(phone);

        edt_name.setText(user.getName());
        edt_email.setText(user.getEmail());
        edt_phone.setText(user.getPhone());
        edt_password.setText(user.getPassword());
        password = user.getPassword();

        fullname = mView.findViewById(R.id.profile_fullname);
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

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                bundle.putString("password", password);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
