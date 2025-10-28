package com.example.duanlonmain.noi_part4;

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
import com.example.duanlonmain.database.noi_part4.*;
import com.example.duanlonmain.menu_Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class noi_part4_cauHoi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_cauhoi);

        TextView txt_tenPhanThi = findViewById(R.id.txt_tenPhanThi),
                txt_engQuestion = findViewById(R.id.txt_engQuestion),
                txt_viQuestion = findViewById(R.id.txt_viQuestion);

        ImageButton img_back = findViewById(R.id.img_back);

        Button btn_batDau = findViewById(R.id.btn_batDau);
        txt_tenPhanThi.setText("Part 4: Respond to questions");
        txt_engQuestion.setText("You will answer three questions based on information on the screen. You will have 45 seconds to read the information. You will have 15 seconds to respond to Questions 8 and 9, and you will have 30 seconds to respond to Question 10. You will hear Question 10 two times. For each question, you will have three seconds to prepare before answering.");
        txt_viQuestion.setText("Bạn sẽ trả lời ba câu hỏi dựa trên thông tin trên màn hình. Bạn sẽ có 45 giây để đọc thông tin. Bạn sẽ có 15 giây để trả lời Câu hỏi 8 và 9, và bạn sẽ có 30 giây để trả lời Câu hỏi 10. Bạn sẽ được nghe Câu hỏi 10 hai lần. Với mỗi câu hỏi, bạn sẽ có ba giây để chuẩn bị trước khi trả lời.");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noi_part4_cauHoi.this, menu_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_batDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(noi_part4_cauHoi.this, noi_part4_deBai_1.class),
                        intent2 = new Intent(noi_part4_cauHoi.this, noi_part4_deBai_2.class),
                        intent3 = new Intent(noi_part4_cauHoi.this, noi_part4_deBai_3.class);

                DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
                noi_part4_Dao noiPart4Dao = db.noiPart4Dao();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        noi_part4 result = noiPart4Dao.getData();
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
