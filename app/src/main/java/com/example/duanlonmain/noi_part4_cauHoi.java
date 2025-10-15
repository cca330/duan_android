package com.example.duanlonmain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

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
        txt_tenPhanThi.setText("Part 3: Respond to questions");
        txt_engQuestion.setText("In this part, you will be asked to refer to information on the screen in order to answer three questions. The information will be shown for 45 seconds before you hear the questions. After listening to each question, you will have three seconds to prepare your response. You will have 15 seconds to respond to Questions 8 and 9 and 30 seconds to respond to Question 10.");
        txt_viQuestion.setText("Trong phần này, bạn sẽ được yêu cầu tham khảo thông tin trên màn hình để trả lời ba câu hỏi. Thông tin sẽ được hiển thị trong 45 giây trước khi bạn nghe câu hỏi. Sau khi nghe mỗi câu hỏi, bạn sẽ có ba giây để chuẩn bị câu trả lời. Bạn sẽ có 15 giây để trả lời Câu hỏi 8 và 9 và 30 giây để trả lời Câu hỏi 10.");

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
                Intent intent = new Intent(noi_part4_cauHoi.this, noi_part4_deBai_1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
