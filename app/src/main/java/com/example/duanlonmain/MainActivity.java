package com.example.duanlonmain;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button; // Sửa lại import
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanlonmain.chat_bot.ChatwithAI;
import com.example.duanlonmain.extend.ExtendActivity;
import com.example.duanlonmain.noi_part1.noi_part1_deBai;
import com.example.duanlonmain.user.ProfileActivity;
import com.example.duanlonmain.writing.MainWriting;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến cho các nút chức năng
    private Button btnWriting, btnReading;
    TextView noipart1, noipart2, noipart3, noipart4, noipart5,lis_img,lis_ques_res,lis_conversation;

    // Khai báo biến cho Bottom Navigation
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Ánh xạ các view từ layout XML
        initViews();

        // Thiết lập sự kiện click cho các nút chức năng
        setupButtonClickListeners();

        // Thiết lập sự kiện cho Bottom Navigation
        setupBottomNavigation();
    }

    /**
     * Ánh xạ các biến trong code với các view trong file layout XML.
     */
    private void initViews() {
        // Các nút trong các card
        btnWriting = findViewById(R.id.btn_writing);
        btnReading = findViewById(R.id.btn_read);   // Sửa ID theo file XML

        // Bottom Navigation
        bottomNav = findViewById(R.id.bottom_nav);
        noipart1 = findViewById(R.id.noi_part1);

        noipart2 = findViewById(R.id.noi_part2);
        noipart3 = findViewById(R.id.noi_part3);
        noipart4 = findViewById(R.id.noi_part4);
        noipart5 = findViewById(R.id.noi_part5);
        lis_conversation = findViewById(R.id.lis_conversation);
        lis_img = findViewById(R.id.lis_img);
        lis_ques_res = findViewById(R.id.lis_ques_res);
    }

    /**
     * Đăng ký và xử lý các sự kiện click cho các nút chức năng.
     */
    private void setupButtonClickListeners() {
        // Sự kiện click cho chức năng "Luyện viết"
        btnWriting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainWriting.class);
            startActivity(intent);
        });

      

        // Sự kiện click cho chức năng "Học đọc"
        btnReading.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Chức năng Luyện đọc sắp ra mắt!", Toast.LENGTH_SHORT).show();
        });


        noipart1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, noi_part1_deBai.class);
            startActivity(intent);
        });

    }

    /**
     * Thiết lập sự kiện và xử lý khi người dùng chọn một mục trong BottomNavigationView.
     */
    private void setupBottomNavigation() {
        // Đặt mục "Home" được chọn mặc định khi vào app
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    // Đang ở màn hình Home rồi, không cần làm gì cả
                    // Hoặc có thể cuộn lên đầu trang nếu cần
                    Toast.makeText(MainActivity.this, "Bạn đang ở trang chủ", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_chat) {
                    // Chuyển sang ChatActivity
                    Intent intent = new Intent(MainActivity.this, ChatwithAI.class);
                    startActivity(intent);
                    // Dùng overridePendingTransition(0,0) để loại bỏ hiệu ứng chuyển cảnh
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if (itemId == R.id.nav_extend) {
                    // Chuyển sang ExtendActivity
                    Intent intent = new Intent(MainActivity.this, ExtendActivity.class);
                    startActivity(intent);
                    // Dùng overridePendingTransition(0,0) để loại bỏ hiệu ứng chuyển cảnh
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    // Chuyển sang ChatActivity
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    // Dùng overridePendingTransition(0,0) để loại bỏ hiệu ứng chuyển cảnh
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                }

                return false; // Trả về false nếu không xử lý mục được chọn
            }
        });
    }
}
