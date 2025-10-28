package com.example.duanlonmain.extend.Grammar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanlonmain.R;


public class NounAdjAdv_Activity extends AppCompatActivity{

          @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_noun_adj_adv);
            ImageButton btnBack = findViewById(R.id.btn_back);
            btnBack.setOnClickListener(v -> finish());
        }


}
