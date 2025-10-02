package com.example.duanlonmain.reading;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;

import com.example.duanlonmain.R;

public class Reading_hoanthanhcau extends AppCompatActivity {
    ImageView btnPrey, btnNext;
    ReadingAdapter adapter;

    RecyclerView recyclerQuestions;
    ArrayList<Reading_quetion_hoanthanhcau> questions;

    final int[] currentPosition = {0};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reading_hoanthanhcau);

        btnPrey = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        recyclerQuestions = findViewById(R.id.recyclerQuestions);



        questions = new ArrayList<>();






        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. addition",
                "B.additions",
                "C. additional",
                "D. additionlly"},
                "1.The coordinator concluded that Thursday's workshop will need ___ tables.",
                "Người điều phối kết luận rằng hội thảo thứ năm sẽ cần 'thêm' bàn",
                2,
                new String[]{"(n)sự bổ sung","sự bổ sung(danh từ số nhiều)","(adj)bổ sung","(adv)thêm vào đó"},
                1,
                new String[]{"Chỗ trống cần một tính từ để bổ nghĩa cho danh từ 'tables'",
                        "A. addition là một danh từ có nghĩa là 'hành động thêm vào' hoặc 'thứ được thêm vào'. Nó không thể trực tiếp bổ sung nghĩa cho danh từ 'tables'",
                        "B. additions là dạng số nhiều của danh từ addition. Tương tự như addition, nó cũng là một danh từ và không thể hoạt động như một tính từ để mô tả tables",
                        "C. additional là một tính từ, có nghĩa là 'được thêm vào, phụ thêm, hoặc bổ sung'. Nó bổ nghĩa chính xác danh từ tables, chỉ ra rằng cần thêm bàn. Điều anyf hoàn toàn phù hớp với ngữ cảnh, ngụ ý rằng buổi hội thảo caanf nhiều bàn hơn số lượng hiện có.",
                        "D. additionally là một trạng từ có nghĩa là 'ngoiaf ra, cũng vậy'. Trạng từ bổ nghĩa cho động từ ,tính từ hoặc các trạng từ khác, nhưng chúng không trực tiếp bổ sung nghĩa cho danh từ. Do đó sai về mựt ngữ pháp ở vị trí này.",
                        "Vì vậy, additional là lựa chọn đúng"}));





        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. addition",
                "B.additions",
                "C. additional",
                "D. additionlly"},
                "1.The coordinator concluded that Thursday's workshop will need ___ tables.",
                "Người điều phối kết luận rằng hội thảo thứ năm sẽ cần 'thêm' bàn",
                2,
                new String[]{"(n)sự bổ sung","sự bổ sung(danh từ số nhiều)","(adj)bổ sung","(adv)thêm vào đó"},
                1,
                new String[]{"thêm1234","thêm3442","thêm564442","thêm52223212","thê4eoger","thêm"}));





        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);
        adapter = new ReadingAdapter(this, questions);
        recyclerQuestions.setAdapter(adapter);



        btnNext.setOnClickListener(v -> {
            if (currentPosition[0] < questions.size() - 1) {
                currentPosition[0]++;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
            }
        });



        btnPrey.setOnClickListener(v -> {
            if (currentPosition[0] > 0) {
                currentPosition[0]--;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
            }
        });


        // hiển thị 1 câu / trang
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerQuestions);

    }
}