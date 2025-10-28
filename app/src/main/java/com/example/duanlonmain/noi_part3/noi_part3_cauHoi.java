package com.example.duanlonmain.noi_part3;

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
import com.example.duanlonmain.database.noi_part3.*;
import com.example.duanlonmain.menu_Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class noi_part3_cauHoi extends Activity {
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
        txt_engQuestion.setText("In this part, you will be asked to answer three questions. After listening to each question, you will have three seconds to prepare your response. You will have 15 seconds to respond to Questions 5 and 6, and 30 seconds to respond to Question 7.");
        txt_viQuestion.setText("Trong phần này, bạn sẽ được yêu cầu trả lời ba câu hỏi. Sau khi nghe mỗi câu hỏi, bạn sẽ có ba giây để chuẩn bị câu trả lời. Bạn sẽ có 15 giây để trả lời Câu hỏi 5 và 6, và 30 giây để trả lời Câu hỏi 7.");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part3_cauHoi.this, menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_batDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(noi_part3_cauHoi.this, noi_part3_deBai_1.class),
                        intent2 = new Intent(noi_part3_cauHoi.this, noi_part3_deBai_2.class),
                        intent3 = new Intent(noi_part3_cauHoi.this, noi_part3_deBai_3.class);

                DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
                noi_part3_Dao noiPart3Dao = db.noiPart3Dao();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        noi_part3 result = noiPart3Dao.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent1.putExtra("context", result.context);
                                intent2.putExtra("context", result.context);
                                intent3.putExtra("context", result.context);
                                intent1.putExtra("q1", result.q1);
                                intent2.putExtra("q2", result.q2);
                                intent3.putExtra("q3", result.q3);
                                startActivity(intent1);
                                finish();
                            }
                        });
                    } finally {
                        db.close();
                        executor.shutdown();
                    }
                });

            }
        });
    }
}
