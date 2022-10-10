package com.example.androidapp1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidapp1.R;
import com.example.androidapp1.activity.UserDetailActivity;
import com.example.androidapp1.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_user_item, null);
        }
        TextView tvName = view.findViewById(R.id.name);
        TextView tvPhone = view.findViewById(R.id.phone);
        Button btn_function = view.findViewById(R.id.btn_function);

        btn_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(user);
            }
        });

        tvName.setText(user.getName());
        tvPhone.setText(user.getPhone());

        return view;
    }
    private void onClickDetail(User user) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
