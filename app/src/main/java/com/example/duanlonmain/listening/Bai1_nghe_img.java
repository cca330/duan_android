package com.example.duanlonmain.listening;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;

import com.example.duanlonmain.R;

public class Bai1_nghe_img extends AppCompatActivity {


    RecyclerView recyclerQuestions;
    ArrayList<Question_bai1> questions;
    QuestionAdapter_bai1 adapter;
    ImageView btnNext, btnPrev,btnBack;

    final int[] currentPosition = {0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listening_bai1_nghe_img);

        recyclerQuestions = findViewById(R.id.recyclerQuestions);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> {
            adapter.stopCurrentAudio();
            finish();
        });





        questions = new ArrayList<>();
        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                1, // đáp án đúng là B
                R.drawable.bai1_nghe,
                R.raw.bai1nghe,
                new String[]{"He's packing a truck",
                        "He's lifting some furniture",
                        "He's starting an engine",
                        "He's diving a car"},
                1
        ));

        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                2,
                R.drawable.bai2_nghe,
                R.raw.bai2nghe,
                new String[]{"Some curtains have been closed",
                        "Some jackets have been laid on a chair",
                        "Some people are gathered around a desk",
                        "Someone is turning on a lamp"},
                2
        ));



        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                1,
                R.drawable.bai1_nghe,
                R.raw.bai1nghe,
                new String[]{"One of the women is reaching into her bag",
                        "The women are waiting in a line",
                        "The man is leading a tour group",
                        "The man is opening a cash register"},
                3
        ));

        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                0,
                R.drawable.bai2_nghe,
                R.raw.bai2nghe,
                new String[]{"The man is bending over a bicycle",
                        "A wheel has been propped against a stack of bracks",
                        "The man is collecting some pieces of wood",
                        "A handrail is being installed"},
                4
        ));

        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                3,
                R.drawable.bai1_nghe,
                R.raw.bai1nghe,
                new String[]{"An armchair has been placed under a window",
                        "Some reading materials have fallen on the floor",
                        "Some flowers are being watered",
                        "Some picture frames are hanging on a wall"},
                5
        ));

        questions.add(new Question_bai1(
                new String[]{"A", "B", "C", "D"},
                2,
                R.drawable.bai2_nghe,
                R.raw.bai2nghe,
                new String[]{"She's adjusting the height of an umbrella",
                        "She's inspecting the tires on a vending cart",
                        "There's a mobile food stand on a walkway",
                        "There are some cooking utensils on the groupnd"},
                6
        ));



        // thay vì LinearLayoutManager dọc:
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);


        adapter = new QuestionAdapter_bai1(this, questions);
        recyclerQuestions.setAdapter(adapter);









        btnNext.setOnClickListener(v -> {
            if (currentPosition[0] < questions.size() - 1) {
                currentPosition[0]++;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
                adapter.stopCurrentAudio();
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentPosition[0] > 0) {
                currentPosition[0]--;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
                adapter.stopCurrentAudio();
            }
        });

       // hiển thị 1 câu / trang
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerQuestions);





    }
}