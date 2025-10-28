package com.example.duanlonmain.writing;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duanlonmain.R;
import com.example.duanlonmain.writing.ui.home.WritingHomeFragment;

public class MainWriting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_writing);

        // TÌM ĐÚNG ID
        ImageButton btnBack = findViewById(R.id.btn_back);
        LinearLayout btnWriting = findViewById(R.id.btn_writing);

        // GẮN SỰ KIỆN
        btnWriting.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new WritingHomeFragment())
                    .addToBackStack("Writing")
                    .commit();
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

    }
}