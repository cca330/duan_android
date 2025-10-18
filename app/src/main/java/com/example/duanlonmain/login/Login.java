package com.example.duanlonmain.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanlonmain.R;
import com.example.duanlonmain.menu_Activity;

import java.util.concurrent.Executors;

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

        // Ánh xạ view
        login = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_nhapusername_login);
        edtPassword = findViewById(R.id.edt_loginnhappassword);
        taotailhoan = findViewById(R.id.txt_logintaotaikhoan);
        quenmatkhau = findViewById(R.id.txt_loginquenmatkhau);
        imgLoginshowpass = findViewById(R.id.img_loginshowpass);

        // Chuyển sang màn tạo tài khoản
        taotailhoan.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Dangky.class);
            startActivity(intent);
        });

        // Hiện / Ẩn mật khẩu
        imgLoginshowpass.setOnClickListener(v -> {
            if (isPasswordVisible) {
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgLoginshowpass.setImageResource(R.drawable.baseline_remove_red_eye_24);
                isPasswordVisible = false;
            } else {
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imgLoginshowpass.setImageResource(R.drawable.baseline_remove_red_eye_24);
                isPasswordVisible = true;
            }
            edtPassword.setSelection(edtPassword.getText().length());
        });

        // Quên mật khẩu
        quenmatkhau.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Quenmatkhau.class);
            startActivity(intent);
        });

        // Nút đăng nhập
        login.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gọi Room DB trong luồng phụ
            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDao userDao = db.userDao();
                User user = userDao.login(username, password);

                runOnUiThread(() -> {
                    if (user != null) {
                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, menu_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }
}
