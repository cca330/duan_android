package com.example.duanlonmain.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.widget.ImageButton;
import android.widget.Toast; // Dòng này cần thiết để sử dụng Toast
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.example.duanlonmain.R;

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





       btn_dangkitaotaikhoan.setOnClickListener(v -> {
           String username = edt_dangkinhapusername.getText().toString().trim();
           String password = edt_dangkinhappassword.getText().toString().trim();
           String repassword = edt_dangkinhaplaipassword.getText().toString().trim();
           String sdt = edt_dangkysdt.getText().toString().trim();

           if (username.isEmpty() || password.isEmpty() || repassword.isEmpty() || sdt.isEmpty()) {
               Toast.makeText(Dangky.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
               return;
           }
           if (!password.equals(repassword)) {
               Toast.makeText(Dangky.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
               return;
           }
           if (sdt.length() != 10) {
               Toast.makeText(Dangky.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
               return;
           }

           new Thread(() -> {
               AppDatabase db = AppDatabase.getInstance(Dangky.this);
               UserDao userDao = db.userDao();

               if (userDao.checkUsernameExists(username) > 0) {
                   runOnUiThread(() -> Toast.makeText(Dangky.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show());
               } else if (userDao.checkPhoneExists(sdt) > 0) {
                   runOnUiThread(() -> Toast.makeText(Dangky.this, "Số điện thoại đã tồn tại!", Toast.LENGTH_SHORT).show());
               } else {
                   userDao.insertUser(new User(username, password, sdt));
                   runOnUiThread(() -> {
                       Toast.makeText(Dangky.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(Dangky.this, Login.class));
                   });
               }
           }).start();
       });








   }





}