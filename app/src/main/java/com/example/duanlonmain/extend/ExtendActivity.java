package com.example.duanlonmain.extend; // Thay đổi package nếu cần

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanlonmain.R;
import com.example.duanlonmain.chat_bot.ChatwithAI;
import com.example.duanlonmain.extend.Grammar.GrammarActivity;
import com.example.duanlonmain.MainActivity;

import com.example.duanlonmain.user.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExtendActivity extends AppCompatActivity {

    // Khai báo các biến cho các layout có thể click
    private TextView grammar;
    private BottomNavigationView bottomNav;
    private ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_extend);
        // 2. Ánh xạ (tìm) các view từ layout
        initViews();
        // 3. Thiết lập các sự kiện click
        setupClickListeners();
    }
    private void initViews() {
        grammar = findViewById(R.id.gram);
        bottomNav = findViewById(R.id.bottom_nav);
        btnback = findViewById(R.id.btn_back);
    }
    private void setupClickListeners() {
        // Sự kiện click cho mục "Grammar"
        grammar.setOnClickListener(v -> {
            Intent intent = new Intent(ExtendActivity.this, GrammarActivity.class);
            startActivity(intent);
        });

        btnback.setOnClickListener(v -> {
           finish();
        });

        // Sự kiện click cho mục "thêm"

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        // Giả sử mục này là "Extend", bạn cần có ID tương ứng trong file menu
        // bottomNav.setSelectedItemId(R.id.navigation_extend);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Thay thế các ID và class cho phù hợp với dự án của bạn
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Đóng ExtendActivity
                return true;
            } else if (itemId == R.id.nav_chat) {
                startActivity(new Intent(getApplicationContext(), ChatwithAI.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Đóng ExtendActivity
                return true;
            } else if (itemId == R.id.nav_vocab) { // Giả sử ID của Profile là nav_profile
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Đóng ExtendActivity
                return true;
            } else if (itemId == R.id.nav_profile) { // Giả sử ID của Profile là nav_profile
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Đóng ExtendActivity
                return true;
            }
            return false;
        });
    }
}
