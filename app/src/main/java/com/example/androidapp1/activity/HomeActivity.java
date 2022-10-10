package com.example.androidapp1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.androidapp1.R;
import com.example.androidapp1.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
//        viewPager = findViewById(R.id.view_pager);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_home:
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case R.id.friends:
//                        viewPager.setCurrentItem(1);
//                        break;
//                    case R.id.profile:
//                        viewPager.setCurrentItem(2);
//                        break;
//                }
//                return true;
//            }
//        });

        phone = getIntent().getStringExtra("phone");
        //setUpViewPager();
    }

//    private void setUpViewPager() {
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
//                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        viewPager.setAdapter(viewPagerAdapter);
//
//    }

    public void profile(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

    public void userListView(View view) {
        Intent intent = new Intent(getApplicationContext(), LvUsersActivity.class);
        startActivity(intent);
    }
}