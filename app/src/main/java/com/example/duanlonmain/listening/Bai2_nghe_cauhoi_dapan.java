package com.example.duanlonmain.listening;

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

import com.example.duanlonmain.R;

import java.util.ArrayList;

public class Bai2_nghe_cauhoi_dapan extends AppCompatActivity {

     ImageView btnNext, btnPrev,btnBack;
     RecyclerView recyclerQuestions;
     ArrayList<Question_bai2> questions;
     Adapter_bai2 adapter;
     final int[] currentPosition = {0};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2_nghe_cauhoi_dapan);


        recyclerQuestions = findViewById(R.id.recyclerQuestions);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnBack = findViewById(R.id.btn_back);


        btnBack.setOnClickListener(v -> {
            adapter.stopCurrentAudio();
            finish();
        });


        questions = new ArrayList<>();
        questions.add(new Question_bai2(new String[] {"A", "B", "C", "D"},
                new String("Which country do Simon Telecommunication operate out of?"),
                        2,
                        R.raw.audio_nghe_que_res1,
                        new String[] {
                                "They're visiting  this week",
                                "I have a meeting in the city",
                                "Their headquarters are in Hungary"},
                        1));




        questions.add(new Question_bai2(new String[] {"A", "B", "C", "D"},
                new String("We have a meeting with Bob tomorrow, don't we?"),
                        2,
                        R.raw.audio_nghe_que_res2,
                        new String[] {
                        "we have to drive across toen",
                        "Lubch is at the Mexican restaurant",
                        "No. He rescheduled it for next week"},
                        2));



        questions.add(new Question_bai2(new String[] {"A","B","C","D"},
                new String("Do the shoes come in a size 12 or 12 1/2?"),
                        1,
                        R.raw.audio_nghe_que_res3,
                        new String[] {
                        "What time do you need it by?",
                        "I am afraid we have neither in stock",
                        "These are the most comfortable ones"},
                3));



        questions.add(new Question_bai2(new String[] {"A","B","C","D"},
                new String("Did you get that coffee i asked for?"),
                        2,
                        R.raw.audio_nghe_que_res4,
                        new String[] {
                        "I gave up caffeine",
                        "Maybe tomorrow",
                        "Yes. One latte, no sugar"},
                        4));



        questions.add(new Question_bai2(new String[] {"A","B","C","D"},
                new String("Why haven't we shipped out the package gathering dust in the back?"),
                        2,
                        R.raw.audio_nghe_que_res5,
                        new String[] {
                        "It is going to snow later",
                        "The trucks are on their way",
                        "The forklift broke down this morning"},
                  5));



        questions.add(new Question_bai2(new String[] {"A","B","C","D"},
                new String("How many years did you spend working in Italy?"),
                        0,
                        R.raw.audio_nghe_que_res6,
                        new String[] {
                        "Almost 10 years",
                        "It's nice place to visit",
                        "My dad speaks Portuguese"},
                        6));



        questions.add(new Question_bai2(new String[] {"A","B","C","D"},
                new String("Is Tommy getting a ride with us to the party?"),
                        1,
                        R.raw.audio_nghe_que_res7,
                        new String[] {
                        "I'll tell you if i'm driving",
                        "No. He'll meet us there",
                        "What are we going to do?"},
                        7));




        // thay vì LinearLayoutManager dọc:
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);


        adapter = new Adapter_bai2(this, questions);
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