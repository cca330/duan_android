package com.example.duanlonmain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class noi_part1_cauHoi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_cauhoi);

        TextView txt_tenPhanThi = findViewById(R.id.txt_tenPhanThi),
                txt_engQuestion = findViewById(R.id.txt_engQuestion),
                txt_viQuestion = findViewById(R.id.txt_viQuestion);

        ImageButton img_back = findViewById(R.id.img_back);

        Button btn_batDau = findViewById(R.id.btn_batDau);

        txt_tenPhanThi.setText("Part 1: Read a text aloud");
        txt_engQuestion.setText("In this part of the test, you will read the text on the screen. You have 45 seconds to prepare. Then you will have 45 seconds to read the text aloud.");
        txt_viQuestion.setText("Trong phần này của bài kiểm tra, bạn sẽ đọc đoạn văn trên màn hình. Bạn sẽ có 45 giây để chuẩn bị. Sau đó, bạn sẽ có 45 giây để đọc to đoạn văn bản.");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part1_cauHoi.this, menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_batDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part1_cauHoi.this, noi_part1_deBai.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
