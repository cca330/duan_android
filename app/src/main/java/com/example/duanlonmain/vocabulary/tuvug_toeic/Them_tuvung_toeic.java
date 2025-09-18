package com.example.duanlonmain.vocabulary.tuvug_toeic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanlonmain.R;
import com.example.duanlonmain.vocabulary.Vocabulary;

public class Them_tuvung_toeic extends AppCompatActivity {

    TextView vocabulary_contract;
    TextView recycler_vocabulary_marketing;
    TextView recycler_vocabulary_warranties;
    TextView recycler_vocabulary_business_plan;
    TextView recycler_vocabulary_conferences;
    TextView recycler_vocabulary_com;
    TextView recycler_vocabulary_office_technology;
    TextView recycler_vocabulary_office_procedures;
    TextView recycler_vocabulary_electronics;
    TextView recycler_vocabulary_correspondece;
    TextView recycler_vocabulary_job_ads;
    TextView recycler_vocabulary_shopping;
    TextView recycler_vocabulary_product;
    ImageButton btn_back;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_tuvung_toeic);



        vocabulary_contract = findViewById(R.id.vocabulary_contract);
        recycler_vocabulary_marketing = findViewById(R.id.recycler_vocabulary_marketing);
        recycler_vocabulary_warranties = findViewById(R.id.recycler_vocabulary_warranties);
        recycler_vocabulary_business_plan = findViewById(R.id.recycler_vocabulary_business_plan);
        recycler_vocabulary_conferences = findViewById(R.id.recycler_vocabulary_conferences);
        recycler_vocabulary_com = findViewById(R.id.recycler_vocabulary_com);
        recycler_vocabulary_office_technology = findViewById(R.id.recycler_vocabulary_office_technology);
        recycler_vocabulary_office_procedures = findViewById(R.id.recycler_vocabulary_office_procedures);
        recycler_vocabulary_electronics = findViewById(R.id.recycler_vocabulary_electronics);
        recycler_vocabulary_correspondece = findViewById(R.id.recycler_vocabulary_correspondece);
        recycler_vocabulary_job_ads = findViewById(R.id.recycler_vocabulary_job_ads);
        recycler_vocabulary_shopping = findViewById(R.id.recycler_vocabulary_shopping);
        recycler_vocabulary_product = findViewById(R.id.recycler_vocabulary_product);
        btn_back = findViewById(R.id.btn_back);




        vocabulary_contract.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Contract.class);
            startActivity(intent);
        });


        recycler_vocabulary_marketing.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Marketing.class);
            startActivity(intent);
        });



        recycler_vocabulary_warranties.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Warranties.class);
            startActivity(intent);
        });



        recycler_vocabulary_business_plan.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Business.class);
            startActivity(intent);
        });





        recycler_vocabulary_conferences.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Conferences.class);
            startActivity(intent);
        });



        recycler_vocabulary_com.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Computers.class);
            startActivity(intent);
        });



        recycler_vocabulary_office_technology.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Office_technology.class);
            startActivity(intent);
        });



        recycler_vocabulary_office_procedures.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Office_procedures.class);
            startActivity(intent);
        });



        recycler_vocabulary_electronics.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Electronics.class);
            startActivity(intent);
        });





        recycler_vocabulary_correspondece.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Correspondence.class);
            startActivity(intent);
        });



        recycler_vocabulary_job_ads.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Job_ads.class);
            startActivity(intent);
        });




        recycler_vocabulary_shopping.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Shopping.class);
            startActivity(intent);
        });



        recycler_vocabulary_product.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Product_development.class);
            startActivity(intent);
        });




        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(Them_tuvung_toeic.this, Vocabulary.class);
            startActivity(intent);
        });



    }
}