package com.example.androidapp1.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.androidapp1.R;
import com.example.androidapp1.SQLite.UserDao;
import com.example.androidapp1.adapter.PhotoAdapter;
import com.example.androidapp1.adapter.StoreAdapter;
import com.example.androidapp1.adapter.UserAdapter;
import com.example.androidapp1.model.Photo;
import com.example.androidapp1.model.Store;
import com.example.androidapp1.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private RecyclerView rcv_Store;
    private StoreAdapter storeAdapter;
    private List<Store> list_Store;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> listPhoto;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //slider photo
        viewPager = view.findViewById(R.id.view_pager_slider);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        listPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(getActivity(), listPhoto);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        //listView Store
        rcv_Store = view.findViewById(R.id.rcv_store);
        storeAdapter = new StoreAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcv_Store.setLayoutManager(linearLayoutManager);

        storeAdapter.setData(getList_Store());
        rcv_Store.setAdapter(storeAdapter);

        autoSlider();
        return view;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();

        list.add(new Photo(R.drawable.p1));
        list.add(new Photo(R.drawable.p2));
        list.add(new Photo(R.drawable.p3));
        list.add(new Photo(R.drawable.p4));
        list.add(new Photo(R.drawable.p5));

        return list;
    }

    private List<Store> getList_Store() {
        List<Store> list = new ArrayList<>();

        list.add(new Store("123", "C??m g?? quay 99", R.drawable.p1, "30 L?? ?????, Thanh Kh??, ???? N???ng"));
        list.add(new Store("123", "C??m g?? quay 99", R.drawable.p1, "30 L?? ?????, Thanh Kh??, ???? N???ng"));
        list.add(new Store("123", "C??m g?? quay 99", R.drawable.p1, "30 L?? ?????, Thanh Kh??, ???? N???ng"));
        list.add(new Store("123", "Qu??n A - B??n ?????u m???m t??m", R.drawable.p2, "Viet Nam"));
        list.add(new Store("123", "Tr?? s???a nh?? l??m - Tr???n Cao V??n", R.drawable.p4, "Viet Nam"));
        list.add(new Store("123", "Tr?? s???a nh?? l??m - Tr???n Cao V??n", R.drawable.p4, "Viet Nam"));
        list.add(new Store("123", "Tr?? s???a nh?? l??m - Tr???n Cao V??n", R.drawable.p4, "Viet Nam"));
        list.add(new Store("123", "C??m chi??n gi??n 404", R.drawable.avt, "Viet Nam"));
        list.add(new Store("123", "Qu??n nh???u M??? Li??n", R.drawable.p5, "Viet Nam"));
        list.add(new Store("123", "Qu??n nh???u M??? Li??n", R.drawable.p5, "Viet Nam"));
        list.add(new Store("123", "Qu??n nh???u M??? Li??n", R.drawable.p5, "Viet Nam"));
        list.add(new Store("123", "Qu??n nh???u M??? Li??n", R.drawable.p5, "Viet Nam"));

        return list;
    }

    private void autoSlider() {
        if (listPhoto == null || listPhoto.isEmpty() || viewPager == null) {
            return;
        }
        //T???o timer
        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listPhoto.size() - 1;

                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
