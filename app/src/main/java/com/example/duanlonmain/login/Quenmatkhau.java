package com.example.duanlonmain.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent; // QUAN TRỌNG: Import Intent
import android.widget.Toast;


import com.example.duanlonmain.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Quenmatkhau extends AppCompatActivity {
    Button btn_quenmatkhauxacthuc;
    EditText edt_quenmatkhaunhapusername, edt_quenmatkhaunhapsdt;
    TextView txt_dangkyquaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quenmatkhau);
        btn_quenmatkhauxacthuc = findViewById(R.id.btn_quenmatkhauxacthuc);
        edt_quenmatkhaunhapusername = findViewById(R.id.edt_quenmatkhaunhapusername);
        edt_quenmatkhaunhapsdt = findViewById(R.id.edt_quenmatkhaunhapsdt);
        txt_dangkyquaylai = findViewById(R.id.txt_quenmatkhaunhaplaimatkhau);



        txt_dangkyquaylai.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     Intent intent = new Intent(Quenmatkhau.this, Login.class);
                                                     startActivity(intent);
                                                 }
                                             });






        btn_quenmatkhauxacthuc.setOnClickListener(v -> {
            String username = edt_quenmatkhaunhapusername.getText().toString().trim();
            String sdt = edt_quenmatkhaunhapsdt.getText().toString().trim();

            if (username.isEmpty() || sdt.isEmpty()) {
                Toast.makeText(Quenmatkhau.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }


            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                int count = db.userDao().checkUsernameAndPhone(username, sdt);

                runOnUiThread(() -> {
                    if (count > 0) {
                        Toast.makeText(this, "Tài khoản hợp lệ!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Quenmatkhaunhaplaimatkhau.class);
                        intent.putExtra("USENAME", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Sai Username hoặc SĐT!", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });




    }



}