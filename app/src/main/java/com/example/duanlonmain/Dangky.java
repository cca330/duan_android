package com.example.duanlonmain;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.InputType;
import android.widget.ImageButton;
import android.widget.Toast; // Dòng này cần thiết để sử dụng Toast
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dangky extends AppCompatActivity {

   EditText edt_dangkinhapusername,edt_dangkinhappassword,edt_dangkinhaplaipassword,edt_dangkysdt;
   Button btn_dangkitaotaikhoan;
   boolean isPasswordVisible = false;
   ImageButton img_dangkishowpass,img_dangkishowrepass;
   TextView txt_dangkyquaylai;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangky);

        edt_dangkinhapusername = findViewById(R.id.edt_dangkinhapusername);
        edt_dangkinhappassword = findViewById(R.id.edt_dangkinhappassword);
        edt_dangkinhaplaipassword = findViewById(R.id.edt_dangkinhaplaipassword);
        edt_dangkysdt = findViewById(R.id.edt_dangkysdt);
        btn_dangkitaotaikhoan = findViewById(R.id.btn_dangkitaotaikhoan);
        img_dangkishowpass = findViewById(R.id.img_dangkishowpass);
        img_dangkishowrepass = findViewById(R.id.img_dangkishowrepass);
        txt_dangkyquaylai = findViewById(R.id.txt_dangkyquaylai);



        txt_dangkyquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dangky.this, Login.class));
            }
        });




       img_dangkishowpass.setOnClickListener(v -> {
           if (isPasswordVisible) {
               edt_dangkinhappassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
               img_dangkishowpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt đóng
               isPasswordVisible = false;
           } else {
               // Đang ẩn -> chuyển sang hiện
               edt_dangkinhappassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
               img_dangkishowpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt mở
               isPasswordVisible = true;
           }

           // Giữ con trỏ ở cuối
//           Lấy độ dài chuỗi hiện tại trong EditText.
//           Đặt lại vị trí con trỏ = cuối chuỗi (sau ký tự cuối).
           edt_dangkinhappassword.setSelection(edt_dangkinhappassword.getText().length());
       });




       img_dangkishowrepass.setOnClickListener(v -> {
           if (isPasswordVisible) {
               // Đang hiện -> chuyển sang ẩn
               edt_dangkinhaplaipassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
               img_dangkishowrepass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt gạch
               isPasswordVisible = false;
           } else {
               // Đang ẩn -> chuyển sang hiện
               edt_dangkinhaplaipassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
               img_dangkishowrepass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt mở
               isPasswordVisible = true;
           }

           // Giữ con trỏ ở cuối
//           Lấy độ dài chuỗi hiện tại trong EditText.
//           Đặt lại vị trí con trỏ = cuối chuỗi (sau ký tự cuối).
           edt_dangkinhaplaipassword.setSelection(edt_dangkinhaplaipassword.getText().length());
       });




        btn_dangkitaotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_dangkinhapusername.getText().toString();
                String password = edt_dangkinhappassword.getText().toString();
                String repassword = edt_dangkinhaplaipassword.getText().toString();
                String sdt = edt_dangkysdt.getText().toString();

                if (username.isEmpty() || password.isEmpty() || repassword.isEmpty() || sdt.isEmpty()) {
                    Toast.makeText(Dangky.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(repassword)) {
                    Toast.makeText(Dangky.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sdt.length() != 10 ){
                    Toast.makeText(Dangky.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }


                // chạy trong Thread riêng, không chạy trực tiếp trong UI Thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Kết nối SQL Server
                            Connection conn = ConnectionClass.CONN();
                            if (conn != null) {

                                // kiem tra user
                                String checkUser = "SELECT COUNT(*) FROM USE_PASSWORD WHERE USENAME=?";
                                PreparedStatement psUser = conn.prepareStatement(checkUser);
                                psUser.setString(1, username);
                                ResultSet rsUser = psUser.executeQuery();
                                if (rsUser.next() && rsUser.getInt(1) > 0) {
                                    runOnUiThread(() ->
                                            Toast.makeText(Dangky.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show()
                                    );
                                    return;
                                }

// check sdt                    / Kiểm tra số điện thoại trùng
                                String checkPhone = "SELECT COUNT(*) FROM USE_PASSWORD WHERE SDT=?";
                                PreparedStatement psPhone = conn.prepareStatement(checkPhone);
                                psPhone.setString(1, sdt);
                                ResultSet rsPhone = psPhone.executeQuery();
                                if (rsPhone.next() && rsPhone.getInt(1) > 0) {
                                    runOnUiThread(() ->
                                            Toast.makeText(Dangky.this, "Số điện thoại đã tồn tại!", Toast.LENGTH_SHORT).show()
                                    );
                                    return;
                                }
                                else {
                                    String insertQuery = "INSERT INTO USE_PASSWORD (USENAME, PASSWORD, SDT) VALUES (?, ?, ?)";
                                    PreparedStatement psInsert = conn.prepareStatement(insertQuery);
                                    psInsert.setString(1, username);
                                    psInsert.setString(2, password);
                                    psInsert.setString(3, sdt);
                                    psInsert.executeUpdate();

                                    runOnUiThread(() -> {
                                        Toast.makeText(Dangky.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Dangky.this, Login.class));
                                    });
                                }
                                conn.close();
                            } else {
                                runOnUiThread(() ->
                                        Toast.makeText(Dangky.this, "Kết nối thất bại!", Toast.LENGTH_SHORT).show()
                                );
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() ->
                                    Toast.makeText(Dangky.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                            );
                        }
                    }
                }).start();


            }
        });







   }





}