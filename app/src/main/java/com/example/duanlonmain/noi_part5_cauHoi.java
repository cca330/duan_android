package com.example.duanlonmain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class noi_part5_cauHoi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_cauhoi);

        TextView txt_tenPhanThi = findViewById(R.id.txt_tenPhanThi),
                txt_engQuestion = findViewById(R.id.txt_engQuestion),
                txt_viQuestion = findViewById(R.id.txt_viQuestion);

        ImageButton img_back = findViewById(R.id.img_back);

        Button btn_batDau = findViewById(R.id.btn_batDau);

        txt_tenPhanThi.setText("Part 3: Respond to questions");
        txt_engQuestion.setText("");
        txt_viQuestion.setText("");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part5_cauHoi.this, menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_batDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part5_cauHoi.this, noi_part5_deBai.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
