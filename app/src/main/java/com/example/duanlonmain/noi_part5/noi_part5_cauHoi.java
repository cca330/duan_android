package com.example.duanlonmain.noi_part5;

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
import com.example.duanlonmain.database.noi_part5.*;
import com.example.duanlonmain.menu_Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        txt_tenPhanThi.setText("Part 5: Express an opinion");
        txt_engQuestion.setText("In Question 11, you will give your opinion about a topic. You will have 45 seconds to prepare your response. You will have 60 seconds to speak. Say as much as you can in the time you have.");
        txt_viQuestion.setText("Trong Câu hỏi 11, bạn sẽ đưa ra ý kiến của mình về một chủ đề. Bạn sẽ có 45 giây để chuẩn bị câu trả lời. Bạn sẽ có 60 giây để nói. Hãy nói càng nhiều càng tốt trong thời gian cho phép.");

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
                DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
                noi_part5_Dao noiPart5Dao = db.noiPart5Dao();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        String question = noiPart5Dao.getData();
                        intent.putExtra("question", question);
                        startActivity(intent);
                        finish();
                    } finally {
                        db.close();
                    }
                });

            }
        });
    }
}
