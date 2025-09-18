package com.example.duanlonmain.vocabulary;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanlonmain.R;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Business;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Conferences;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Contract;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Marketing;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Them_tuvung_toeic;
import com.example.duanlonmain.vocabulary.tuvug_toeic.Warranties;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Vocabulary extends AppCompatActivity {


    TextView txt_vocabulary_contract;
    TextView txt_vocabulary_marketing;
    TextView txt_vocabulary_warranties;
    TextView txt_vocabulary_business_plan;
    TextView txt_vocabulary_conferences;
    TextView txt_vocabulary_xemthem;

    TextView txt_vocabulary_chu_de_mausac;
    TextView txt_vocabulary_chu_de_traicay;
    TextView txt_vocabulary_chu_de_giadinh;
    TextView txt_vocabulary_chu_de_nhatruong;
    TextView txt_vocabulary_chu_de_trangphuc;
    TextView txt_vocabulary_chu_de_xemthem;
    TextView txt_vocabulary_1;
    TextView txt_vocabulary_101;
    TextView txt_vocabulary_201;
    TextView txt_vocabulary_301;
    TextView txt_vocabulary_401;
    TextView txt_vocabulary_501;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        txt_vocabulary_contract = findViewById(R.id.vocabulary_contract);
        txt_vocabulary_marketing = findViewById(R.id.recycler_vocabulary_marketing);
        txt_vocabulary_warranties = findViewById(R.id.recycler_vocabulary_warranties);
        txt_vocabulary_business_plan = findViewById(R.id.recycler_vocabulary_business_plan);
        txt_vocabulary_conferences = findViewById(R.id.recycler_vocabulary_conferences);
        txt_vocabulary_xemthem = findViewById(R.id.recycler_vocabulary_xemthem);
        txt_vocabulary_chu_de_mausac = findViewById(R.id.recycler_vocabulary_chu_de_mausac);
        txt_vocabulary_chu_de_traicay = findViewById(R.id.recycler_vocabulary_chu_de_traicay);
        txt_vocabulary_chu_de_giadinh = findViewById(R.id.recycler_vocabulary_chu_de_giadinh);
        txt_vocabulary_chu_de_nhatruong = findViewById(R.id.recycler_vocabulary_chu_de_nhatruong);
        txt_vocabulary_chu_de_trangphuc = findViewById(R.id.recycler_vocabulary_chu_de_trangphuc);
        txt_vocabulary_chu_de_xemthem = findViewById(R.id.recycler_vocabulary_chu_de_xemthem);
        txt_vocabulary_1 = findViewById(R.id.recycler_vocabulary_1);
        txt_vocabulary_101 = findViewById(R.id.recycler_vocabulary_101);
        txt_vocabulary_201 = findViewById(R.id.recycler_vocabulary_201);
        txt_vocabulary_301 = findViewById(R.id.recycler_vocabulary_301);
        txt_vocabulary_401 = findViewById(R.id.recycler_vocabulary_401);
        txt_vocabulary_501 = findViewById(R.id.recycler_vocabulary_501);



        txt_vocabulary_contract.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Contract.class);
            startActivity(intent);
        });


        txt_vocabulary_marketing.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Marketing.class);
            startActivity(intent);
        });


        txt_vocabulary_warranties.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Warranties.class);
            startActivity(intent);
        });


        txt_vocabulary_business_plan.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Business.class);
            startActivity(intent);
        });


        txt_vocabulary_conferences.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Conferences.class);
            startActivity(intent);
        });


        txt_vocabulary_xemthem.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this, Them_tuvung_toeic.class);
            startActivity(intent);
        });


    }


}