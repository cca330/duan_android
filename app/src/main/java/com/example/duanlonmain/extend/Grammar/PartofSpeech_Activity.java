package com.example.duanlonmain.extend.Grammar;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanlonmain.R;

import java.util.ArrayList;
import java.util.List;

public class PartofSpeech_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    PartofSpeechAdapter adapter;
    List<PartofSpeech> partList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partofspeech);

        recyclerView = findViewById(R.id.recyl_partofspeech);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        partList = new ArrayList<>();
        addData();
        adapter = new PartofSpeechAdapter(this, partList);
        recyclerView.setAdapter(adapter);

        ImageButton btnBack = findViewById(R.id.btn_backpartofspeech);
        btnBack.setOnClickListener(v -> finish());
    }

    private void addData() {
        partList.add(new PartofSpeech(
                "Danh từ (Noun)",
                "Danh từ là từ dùng để chỉ người, vật, địa điểm, sự vật hoặc khái niệm trừu tượng.\nNó có thể đóng vai trò chủ ngữ, tân ngữ hoặc bổ ngữ trong câu.",
                "Danh từ chung (boy, car, city)\nDanh từ riêng (John, Vietnam)\nDanh từ trừu tượng (love, freedom)\nDanh từ cụ thể (table, dog)\nDanh từ tập hợp (team, family)\nDanh từ đếm được (a book)\nDanh từ không đếm được (water)",
                "The teacher is very kind.\nWater is essential for life.",
                R.drawable.n // thay bằng icon của bạn
        ));

        partList.add(new PartofSpeech(
                "Đại từ (Pronoun)",
                "Đại từ là từ dùng để thay thế cho danh từ, giúp tránh lặp lại từ ngữ trong câu.",
                "Đại từ nhân xưng: I, you, he, she...\nĐại từ sở hữu: mine, yours...\nTính từ sở hữu: my, your...\nĐại từ phản thân: myself...\nĐại từ chỉ định: this, that...\nĐại từ nghi vấn: who, what...\nĐại từ quan hệ: who, which...\nĐại từ bất định: someone, anything...",
                "She is my friend.\nHe hurt himself.",
                R.drawable.pronouns
        ));

        partList.add(new PartofSpeech(
                "Động từ (Verb)",
                "Động từ là từ chỉ hành động, trạng thái hoặc cảm xúc của chủ ngữ.",
                "Động từ thường (run, eat)\nĐộng từ trạng thái (be, have)\nĐộng từ khuyết thiếu (can, must)\nĐộng từ liên kết (be, seem)\nNgoại động từ (reads a book)\nNội động từ (sleeps)",
                "He runs fast.\nShe is tired.\nI can swim.",
                R.drawable.verb
        ));

        partList.add(new PartofSpeech(
                "Tính từ (Adjective)",
                "Tính từ là từ dùng để miêu tả hoặc bổ nghĩa cho danh từ hoặc đại từ.",
                "Tính từ miêu tả: beautiful, tall\nTính từ chỉ số lượng: many, few\nTính từ sở hữu: my, your\nTính từ so sánh: taller, tallest",
                "She is beautiful.\nHe is taller than me.",
                R.drawable.adj
        ));

        partList.add(new PartofSpeech(
                "Trạng từ (Adverb)",
                "Trạng từ bổ nghĩa cho động từ, tính từ hoặc cả câu.",
                "Chỉ cách thức: slowly, well\nChỉ thời gian: now, yesterday\nChỉ nơi chốn: here, there\nChỉ tần suất: always, never\nChỉ mức độ: very, too",
                "He runs fast.\nShe always gets up early.",
                R.drawable.adv
        ));

        partList.add(new PartofSpeech(
                "Giới từ (Preposition)",
                "Giới từ nối danh từ hoặc đại từ với phần còn lại của câu.",
                "Chỉ nơi chốn: in, on, at\nChỉ thời gian: in, on, at\nChỉ hướng: to, from, into\nChỉ nguyên nhân: for, because of",
                "He is in the room.\nWe’ll meet on Monday.",
                R.drawable.preposition
        ));

        partList.add(new PartofSpeech(
                "Liên từ (Conjunction)",
                "Liên từ nối từ, cụm từ hoặc mệnh đề.",
                "Đẳng lập: and, but, or\nPhụ thuộc: because, if, when\nTương quan: both...and, either...or",
                "I like apples and oranges.\nEither you study hard or you fail.",
                R.drawable.confunction
        ));

        partList.add(new PartofSpeech(
                "Thán từ (Interjection)",
                "Thán từ biểu lộ cảm xúc như vui, buồn, ngạc nhiên, đau đớn,...",
                "Ngạc nhiên: Wow!\nVui mừng: Yay!\nBuồn: Oh no!\nĐau: Ouch!",
                "Wow! That’s amazing!\nOuch! It hurts!",
                R.drawable.interjection
        ));
    }
}

