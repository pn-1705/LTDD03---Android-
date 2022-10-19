package com.example.androidapp1.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.adapter.UserAdapter;
import com.example.androidapp1.model.User;

import java.util.List;


public class FriendsFragment extends Fragment {

    private UserAdapter userAdapter;
    private ListView lvUsers;
    private Context mcontext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lvusers, container, false);

        mcontext = getActivity();
        lvUsers = view.findViewById(R.id.lv_Users);
        UserDao dao = new UserDao(mcontext);
        List<User> list = dao.getAll();

        userAdapter = new UserAdapter(mcontext, list);
        lvUsers.setAdapter((ListAdapter) userAdapter);
//
        return view;
    }
}
