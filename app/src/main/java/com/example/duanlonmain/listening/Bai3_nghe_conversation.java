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

public class Bai3_nghe_conversation extends AppCompatActivity {

    Adapter_bai3 adapter;

    ArrayList<Question_bai3> questions;

    final int[] currentPosition = {0};

    RecyclerView recyclerQuestions;
    ImageView btnNext, btnPrev,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai3_nghe_conversation);


        recyclerQuestions = findViewById(R.id.recyclerQuestions);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnBack = findViewById(R.id.btn_back);


        btnBack.setOnClickListener(v -> {
            adapter.stopCurrentAudio();
            finish();
        });


        questions = new ArrayList<>();

        questions.add(new Question_bai3(
                new String[]{"A", "B", "C", "D"},
                "1-3\n" +
                        "W: Excuse me. Is this seat free? I didn't think the auditorium would fill up so fast.\n" +
                        "M: Of course. My name is Kevin Moore, by the way.\n" +
                        "W: Nice to meet you, Kevin. I'm Carmen Sanchez.\n" +
                        "M: Are you the Carmen Sanchez leading a networking workshop later on?\n" +
                        "W: That's right. I've been holding one at this event for the last few years. How'd you hear about it?\n" +
                        "M: A friend of mine recommended it to\n" +
                        "me, so I signed up for it. He mentioned Something about there being no better place to network than a networking shop.",
                new String[]{"1. Where is the conversation taking place?",
                        "2. What is the main reason Carmen Sanchez is at the",
                        "3. How did the man hear about Carmen Sanchez?"},
                new String[]{"At a conference", "At a book signing", "At a product launch", "At a company dinner",
                        "To network", "To deliver a speech", "To hold a workshop", "To introduce a product",
                        "He saw her profile online.", "A friend told him about her.", "She was featured in a magazine.", "He learned about her from an email."},
                new int[] {0,2,1},
                1,
                R.raw.bai3_nghe1
        ));




        questions.add(new Question_bai3(
                new String[]{"A", "B", "C", "D"},
                "4-6\n" +
                        "M1: Have you guys thought about taking an internship after graduating?\n" +
                        "W: Maybe. There are a couple of cool opportunities that I've been looking into. Toggle, for example, offers a summer internship in their research and development department.\n" +
                        "M2: I imagine the hands-on experience you get in that kind of position would be invaluable. However, I would much rather be compensated for my work.\n" +
                        "W: I would too, but I'm not sure those routes exist for young professionals anymore.\n" +
                        "M1: Yeah. Every, quote-unquote, real job I've looked at requires anywhere from two to three years of experience. At least internships get your foot in the door.\n" +
                        "M2: You two might be right. I just wish it wasn't the case.",
                new String[]{"4. What are the speakers mainly discussing?",
                        "5. How do the speakers most likely know each",
                        "6. What does the second man think is the most important?"},
                new String[]{"Wages", "Internships", "Graduation", "Summer jobs",
                        "They are related.", "They work together.", "They study at the same school.", "They are doing research together.",
                        "Getting hands-on experience", "Getting one's foot in the door", "Getting hired by a large corporation", "Getting compensated for one's labor"},
                new int[] {1,2,3},
                2,
                R.raw.bai3_nghe2
        ));






        questions.add(new Question_bai3(
                new String[]{"A", "B", "C", "D"},
                "7-9\n" +
                        "W: How can help you today, Jackson? M: We're holding a scientific symposium on the 15th and are flying in some prominent scientists from abroad to speak at the event. The issue is two of them don't speak a word of English.\n" +
                        "W: So you need our live interpretation services. I might be able to help. Do you know what language they plan to deliver their talks in?\n" +
                        "M: One in French and the other in Spanish.\n" +
                        "W: That's not a problem. I've got two interpreters that can handle that.\n" +
                        "M: Great! I'll let you know the time of each presentation and the address of the venue mail later on.",
                new String[]{"7. What is scheduled to happen on the 15th?",
                        "8. What information does the man say he will",
                        "9. Look at the graphic. What floor are the speakers likely on?"},
                new String[]{"A job fair", "A symposium", "A political rally", "A graduation ceremony",
                        "The notes of the speeches", "The details on the speakers", "The location and time of the talks", "The location and time of the classes",
                        "Floor 1", "Floor 2", "Floor 3", "Floor 4"},
                new int[]{1,2,1},
                3,
                R.raw.bai3_nghe3
        ));















        // thay vì LinearLayoutManager dọc:
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);


        adapter = new Adapter_bai3(this, questions);
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