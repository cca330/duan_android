package com.example.duanlonmain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent; // QUAN TRỌNG: Import Intent
import android.widget.Toast;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends AppCompatActivity {

    TextView taotailhoan, quenmatkhau;
    Button login;
    EditText edtUsername, edtPassword;
    ImageButton imgLoginshowpass;
    boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_nhapusername_login);
        edtPassword = findViewById(R.id.edt_loginnhappassword);
        taotailhoan = findViewById(R.id.txt_logintaotaikhoan);
        quenmatkhau = findViewById(R.id.txt_loginquenmatkhau);
        imgLoginshowpass = findViewById(R.id.img_loginshowpass);



        taotailhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Dangky.class);
                startActivity(intent);
            }
        });




        imgLoginshowpass.setOnClickListener(v -> {
            if (isPasswordVisible) {
                // Đang hiện → chuyển sang ẩn
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgLoginshowpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt đóng
                isPasswordVisible = false;
            } else {
                // Đang ẩn → chuyển sang hiện
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imgLoginshowpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt mở
                isPasswordVisible = true;
            }

            // Giữ con trỏ ở cuối
            edtPassword.setSelection(edtPassword.getText().length());
        });




        quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Quenmatkhau.class);
                startActivity(intent);
            }
        });






        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Connection conn = ConnectionClass.CONN();
                            if (conn != null) {
                                String query = "SELECT COUNT(*) FROM USE_PASSWORD WHERE USENAME=? AND PASSWORD=?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                ps.setString(1, username);
                                ps.setString(2, password);
                                ResultSet rs = ps.executeQuery();

                                if (rs.next() && rs.getInt(1) > 0) {
                                    runOnUiThread(() ->
                                            Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                                    );
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    runOnUiThread(() ->
                                            Toast.makeText(Login.this, "Sai Username hoặc Password!", Toast.LENGTH_SHORT).show()
                                    );
                                }
                                conn.close();
                            } else {
                                runOnUiThread(() ->
                                        Toast.makeText(Login.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show()
                                );
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() ->
                                    Toast.makeText(Login.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                            );
                        }
                    }
                }).start();
            }
        });



    }
}