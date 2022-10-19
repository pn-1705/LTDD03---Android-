package com.example.androidapp1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp1.R;
import com.example.androidapp1.adapter.PhotoAdapter;
import com.example.androidapp1.adapter.StoreAdapter;
import com.example.androidapp1.model.Store;
import com.example.androidapp1.model.User;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private TextView name, address;
    private ListView lv_menu;
    private GestureDetector gestureDetector;

    ArrayAdapter arrayAdapter;
    List<String> menu;
    Integer i;
    LinearLayout ll_title_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //Lấy thông tin quán
        Bundle bundle = getIntent().getExtras();
        Store store = (Store) bundle.get("object");
        name = findViewById(R.id.name_store);
        address = findViewById(R.id.address_store);
        name.setText(store.getName());
        address.setText(store.getAddress());

        //Tạo listview thực đơn món ăn
        lv_menu = findViewById(R.id.lv_menu);
        ll_title_store = findViewById(R.id.title_store);
        ViewGroup.LayoutParams params = ll_title_store.getLayoutParams();

        menu = new ArrayList<>();
        for (i = 0; i < 15; i++) {
            menu.add("Món " + i);
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menu);
        lv_menu.setAdapter(arrayAdapter);

        //gestureDetector = new GestureDetector(this, new MyGesture());
//        lv_menu.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                //gestureDetector.onTouchEvent(motionEvent);
//                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){
//                    params.height = 0;
//                    ll_title_store.setLayoutParams(params);
//                }
//                return true;
//            }
//        });

//        lv_menu.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//                    params.height = 0;
//                    ll_title_store.setLayoutParams(params);
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
    }


    private List<Store> getList_Store() {
        List<Store> list = new ArrayList<>();

        list.add(new Store("123", "Cơm gà quay 99", R.drawable.p1, "30 Lê Độ, Thanh Khê, Đà Nẵng"));
        list.add(new Store("123", "Quán A - Bún đậu mắm tôm", R.drawable.p2, "Viet Nam"));
        list.add(new Store("123", "Trà sữa nhà làm - Trần Cao Vân", R.drawable.p4, "Viet Nam"));
        list.add(new Store("123", "Cơm chiên giòn 404", R.drawable.avt, "Viet Nam"));
        list.add(new Store("123", "Quán nhậu Mỹ Liên", R.drawable.p5, "Viet Nam"));

        return list;
    }
}