package com.example.duanlonmain;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.service.notification.Condition;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent; // QUAN TRỌNG: Import Intent
import android.widget.Toast;


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






        btn_quenmatkhauxacthuc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = edt_quenmatkhaunhapusername.getText().toString().trim();
                String sdt = edt_quenmatkhaunhapsdt.getText().toString().trim();
                if(username.isEmpty() || sdt.isEmpty()){
                    Toast.makeText(Quenmatkhau.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;

                }


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = ConnectionClass.CONN();
                            if (conn != null) {
                                String query = "SELECT COUNT(*) FROM USE_PASSWORD WHERE USENAME=? AND SDT=?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                ps.setString(1, username);
                                ps.setString(2, sdt);
                                ResultSet rs = ps.executeQuery();

                                if (rs.next() && rs.getInt(1) > 0) {
                                    runOnUiThread(() ->
                                            Toast.makeText(Quenmatkhau.this, "Tài khoản hợp lệ!", Toast.LENGTH_SHORT).show()
                                    );

                                     Intent intent = new Intent(Quenmatkhau.this, Quenmatkhaunhaplaimatkhau.class);
                                     intent.putExtra("USENAME", username); // truyền username qua Intent
                                     startActivity(intent);

                                } else {
                                    runOnUiThread(() ->
                                            Toast.makeText(Quenmatkhau.this, "Sai Username hoặc SĐT!", Toast.LENGTH_SHORT).show()
                                    );
                                }
                                conn.close();
                            } else {
                                runOnUiThread(() ->
                                        Toast.makeText(Quenmatkhau.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show()
                                );
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() ->
                                    Toast.makeText(Quenmatkhau.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                            );
                        }
                    }
                }).start();
            }
        });



    }



}