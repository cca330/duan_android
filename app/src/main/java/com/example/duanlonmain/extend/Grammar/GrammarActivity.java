package com.example.duanlonmain.extend.Grammar;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanlonmain.R;


public class GrammarActivity extends AppCompatActivity {

    ImageButton btnBack, btnPartsOfSpeech, btnTenses, btnSentenceStructure,
            btnSentense, btnSpecialVerbs, btnNounAdjAdv, btnRelativeClause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar); // layout bạn gửi ở trên

        // Ánh xạ View
        btnBack = findViewById(R.id.btn_back);
        btnPartsOfSpeech = findViewById(R.id.btn_parts_of_speech);
        btnTenses = findViewById(R.id.btn_tenses);
        btnSentenceStructure = findViewById(R.id.btn_sentence_structure);
        btnSentense = findViewById(R.id.btn_sentense);
        btnSpecialVerbs = findViewById(R.id.btn_special_verbs);
        btnNounAdjAdv = findViewById(R.id.btn_noun_adj_adv);
        btnRelativeClause = findViewById(R.id.btn_relative_clause);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Từ loại (Parts of Speech)
        btnPartsOfSpeech.setOnClickListener(v -> {
            Intent intent = new Intent(this, PartofSpeech_Activity.class);
            startActivity(intent);
        });

        // Các thì (Tenses)
        btnTenses.setOnClickListener(v -> {
            Intent intent = new Intent(this, TenseActivity.class);
            startActivity(intent);
        });

        // Cấu trúc câu (Sentence Structure)
        btnSentenceStructure.setOnClickListener(v -> {
            Intent intent = new Intent(this, SentenceStructureActivity.class);
            startActivity(intent);
        });

        // Cấu trúc ngữ pháp thông dụng
        btnSentense.setOnClickListener(v -> {
            Intent intent = new Intent(this, StructurePopularActivity.class);
            startActivity(intent);
        });

        // Động từ đặc biệt
        btnSpecialVerbs.setOnClickListener(v -> {
            Intent intent = new Intent(this, Typeofverb_Activity.class);
            startActivity(intent);
        });

        // Danh từ, Tính từ, Trạng từ
        btnNounAdjAdv.setOnClickListener(v -> {
            Intent intent = new Intent(this, NounAdjAdv_Activity.class);
            startActivity(intent);
        });

        // Mệnh đề quan hệ
        btnRelativeClause.setOnClickListener(v -> {
            Intent intent = new Intent(this, RelativeClauActivity.class);
            startActivity(intent);
        });
    }
}
