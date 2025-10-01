package com.example.duanlonmain.listening;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.Locale;

import com.example.duanlonmain.R;

public class Bai1_nghe_img extends AppCompatActivity {


    RecyclerView recyclerQuestions;
    ArrayList<Question> questions;
    QuestionAdapter adapter;
    ImageView btnNext, btnPrev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1_nghe_img);

        recyclerQuestions = findViewById(R.id.recyclerQuestions);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);




        questions = new ArrayList<>();
        questions.add(new Question(
                new String[]{"Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D"},
                1, // đáp án đúng là B
                R.drawable.bai1_nghe,
                R.raw.bai1nghe
        ));

        questions.add(new Question(
                new String[]{"Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D"},
                1, // đáp án đúng là B
                R.drawable.eye,
                R.raw.bai1nghe
        ));



        // thay vì LinearLayoutManager dọc:
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);


        adapter = new QuestionAdapter(this, questions);
        recyclerQuestions.setAdapter(adapter);



        int[] currentPosition = {0}; // mảng 1 phần tử để thay đổi trong lambda

        btnNext.setOnClickListener(v -> {
            if (currentPosition[0] < questions.size() - 1) {
                currentPosition[0]++;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
            }
        });

        btnPrev.setOnClickListener(v -> {
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