package com.example.duanlonmain.vocabulary.tuvug_toeic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanlonmain.R;
import com.example.duanlonmain.vocabulary.Vocabulary;

public class Contract extends AppCompatActivity {

    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contract);

        btn_back=findViewById(R.id.btn_contract_back);



        btn_back.setOnClickListener(v->{
            finish();//quay lai
        });




    }
}