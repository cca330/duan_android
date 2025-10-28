package com.example.duanlonmain.noi_part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.duanlonmain.R;
import com.example.duanlonmain.database.DatabaseHelper;
import com.example.duanlonmain.database.noi_part2.*;
import com.example.duanlonmain.menu_Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class noi_part2_cauHoi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_cauhoi);

        TextView txt_tenPhanThi = findViewById(R.id.txt_tenPhanThi),
                txt_engQuestion = findViewById(R.id.txt_engQuestion),
                txt_viQuestion = findViewById(R.id.txt_viQuestion);

        ImageButton img_back = findViewById(R.id.img_back);

        Button btn_batDau = findViewById(R.id.btn_batDau);

        txt_tenPhanThi.setText("Part 2: Describe a picture");
        txt_engQuestion.setText("In this part, you will be asked to describe the picture on the screen in as much detail as possible. You will have 45 seconds to prepare your response. Then you will have 30 second to talk about the pictures.");
        txt_viQuestion.setText("Trong phần này, bạn sẽ được yêu cầu mô tả hình ảnh trên màn hình càng chi tiết càng tốt. Bạn sẽ có 45 giây để chuẩn bị câu trả lời. Sau đó, bạn sẽ có 30 giây để nói về các hình ảnh.");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part2_cauHoi.this, menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_batDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part2_cauHoi.this, noi_part2_deBai.class);

                DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
                noi_part2_Dao noiPart2Dao = db.noiPart2Dao();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        int pictureId = noiPart2Dao.getData();
                        intent.putExtra("pictureId", pictureId);
                        startActivity(intent);
                        finish();
                    } finally {
                        executor.shutdown();
                    }
                });
            }
        });
    }
}

