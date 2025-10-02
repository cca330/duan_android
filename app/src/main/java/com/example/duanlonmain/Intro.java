package com.example.duanlonmain;

import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.duanlonmain.Adapter.Adapter_walkthrough;

import me.relex.circleindicator.CircleIndicator;

public class Intro extends AppCompatActivity {

    TextView txt_introgetstarted;

    public ViewPager viewPager;
    Adapter_walkthrough adapter;

    int currentPage = 0;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);


        txt_introgetstarted = findViewById(R.id.txt_introgetstarted);
        txt_introgetstarted.setOnClickListener(v -> {
            Intent intent = new Intent(Intro.this, Login.class);
            startActivity(intent);
        });




        viewPager = findViewById(R.id.viewpager);
        CircleIndicator indicator=findViewById(R.id.indicator);
//        CircleIndicator = hiển thị chấm tròn chỉ vị trí.

        adapter = new Adapter_walkthrough(getSupportFragmentManager());
//        Adapter = cung cấp nội dung từng slide.

        viewPager.setAdapter(adapter);


        indicator.setViewPager(viewPager);
//        Khi vuốt sang trang 2 → indicator tự động nhảy từ
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
//        3 dòng cuối cùng là để đồng bộ indicator với viewPager.

        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == adapter.getCount()) {
                    currentPage = 0; // quay lại trang đầu nếu hết
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // tránh tran bo nho
    }



}