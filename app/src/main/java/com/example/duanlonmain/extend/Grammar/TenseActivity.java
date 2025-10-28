package com.example.duanlonmain.extend.Grammar;


import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanlonmain.R;

import java.util.ArrayList;
import java.util.List;

public class TenseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TenseAdapter adapter;
    private List<Tense> tenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tense);

        recyclerView = findViewById(R.id.recyl_tense);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenseList = new ArrayList<>();
        addTenses();

        adapter = new TenseAdapter(this, tenseList);
        recyclerView.setAdapter(adapter);

        ImageButton btnBack = findViewById(R.id.btn_backtense);
        btnBack.setOnClickListener(v -> finish());
    }

    private void addTenses() {
        tenseList.add(new Tense(
                "1. Hiện tại đơn (Simple Present)",
                "Diễn tả thói quen, hành động lặp lại, sự thật hiển nhiên.",
                "S + V(s/es) + O",
                "S + do/does + not + V + O",
                "Do/Does + S + V + O?",
                "I play football every Sunday.\nShe doesn’t like coffee.\nDo you live in Hanoi?",
                R.drawable.presentsimple
        ));

        tenseList.add(new Tense(
                "2. Hiện tại tiếp diễn (Present Continuous)",
                "Diễn tả hành động đang xảy ra ngay lúc nói hoặc kế hoạch sắp tới.",
                "S + am/is/are + V-ing + O",
                "S + am/is/are + not + V-ing + O",
                "Am/Is/Are + S + V-ing + O?",
                "I am studying English now.\nShe isn’t watching TV.\nAre they coming tomorrow?",
                R.drawable.presentcontinous
        ));

        tenseList.add(new Tense(
                "3. Hiện tại hoàn thành (Present Perfect)",
                "Diễn tả hành động đã xảy ra nhưng chưa rõ thời điểm, hoặc kéo dài đến hiện tại.",
                "S + have/has + V3(ed) + O",
                "S + have/has + not + V3(ed) + O",
                "Have/Has + S + V3(ed) + O?",
                "I have finished my homework.\nShe hasn’t seen that movie yet.\nHave you ever been to Japan?",
                R.drawable.presentperfect
        ));

        tenseList.add(new Tense(
                "4. Hiện tại hoàn thành tiếp diễn (Present Perfect Continuous)",
                "Nhấn mạnh tính liên tục của hành động bắt đầu trong quá khứ và còn tiếp diễn.",
                "S + have/has + been + V-ing + O",
                "S + have/has + not + been + V-ing + O",
                "Have/Has + S + been + V-ing + O?",
                "I have been studying for three hours.\nShe hasn’t been sleeping well.\nHave they been waiting long?",
                R.drawable.presentperfectcontinous
        ));

        tenseList.add(new Tense(
                "5. Quá khứ đơn (Simple Past)",
                "Diễn tả hành động xảy ra và kết thúc trong quá khứ.",
                "S + V2/ed + O",
                "S + did + not + V + O",
                "Did + S + V + O?",
                "I visited my grandparents yesterday.\nShe didn’t go to school.\nDid you watch that movie?",
                R.drawable.pasttense
        ));

        tenseList.add(new Tense(
                "6. Quá khứ tiếp diễn (Past Continuous)",
                "Diễn tả hành động đang diễn ra tại một thời điểm trong quá khứ.",
                "S + was/were + V-ing + O",
                "S + was/were + not + V-ing + O",
                "Was/Were + S + V-ing + O?",
                "I was reading at 8 p.m. last night.\nThey weren’t watching TV.\nWas she cooking when you came?",
                R.drawable.pastcontinous
        ));

        tenseList.add(new Tense(
                "7. Quá khứ hoàn thành (Past Perfect)",
                "Diễn tả hành động xảy ra trước một hành động khác trong quá khứ.",
                "S + had + V3(ed) + O",
                "S + had + not + V3(ed) + O",
                "Had + S + V3(ed) + O?",
                "I had finished my work before he arrived.\nShe hadn’t eaten when I came.\nHad you seen him before?",
                R.drawable.pastperfect
        ));

        tenseList.add(new Tense(
                "8. Quá khứ hoàn thành tiếp diễn (Past Perfect Continuous)",
                "Nhấn mạnh hành động kéo dài đến một thời điểm trong quá khứ.",
                "S + had + been + V-ing + O",
                "S + had + not + been + V-ing + O",
                "Had + S + been + V-ing + O?",
                "I had been working for two hours before lunch.\nShe hadn’t been sleeping well.\nHad they been waiting long?",
                R.drawable.pastperfectcontinous
        ));

        tenseList.add(new Tense(
                "9. Tương lai đơn (Simple Future)",
                "Diễn tả hành động sẽ xảy ra trong tương lai.",
                "S + will + V + O",
                "S + will + not + V + O",
                "Will + S + V + O?",
                "I will go to school tomorrow.\nShe won’t come today.\nWill you help me?",
                R.drawable.futuretense
        ));

        tenseList.add(new Tense(
                "10. Tương lai gần (Be going to)",
                "Diễn tả kế hoạch hoặc dự định trong tương lai.",
                "S + am/is/are + going to + V + O",
                "S + am/is/are + not + going to + V + O",
                "Am/Is/Are + S + going to + V + O?",
                "I’m going to visit my friend.\nShe isn’t going to buy it.\nAre you going to join us?",
                R.drawable.futureperfectcontinouse
        ));

        tenseList.add(new Tense(
                "11. Tương lai tiếp diễn (Future Continuous)",
                "Diễn tả hành động đang diễn ra tại một thời điểm trong tương lai.",
                "S + will be + V-ing + O",
                "S + will not be + V-ing + O",
                "Will + S + be + V-ing + O?",
                "I will be studying at 8 p.m. tomorrow.\nShe won’t be sleeping then.\nWill they be playing football?",
                R.drawable.futurecontinous
        ));

        tenseList.add(new Tense(
                "12. Tương lai hoàn thành (Future Perfect)",
                "Diễn tả hành động sẽ hoàn thành trước một thời điểm trong tương lai.",
                "S + will have + V3(ed) + O",
                "S + will not have + V3(ed) + O",
                "Will + S + have + V3(ed) + O?",
                "I will have finished my homework by 9 p.m.\nShe won’t have left yet.\nWill they have arrived by then?",
                R.drawable.futureperfect
        ));
    }
}
