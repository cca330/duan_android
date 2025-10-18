package com.example.duanlonmain.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanlonmain.R;
import com.example.duanlonmain.login.AppDatabase;

public class Quenmatkhaunhaplaimatkhau extends AppCompatActivity {

    EditText edt_quenmatkhaunhaplaimatkhau111, edt_quenmatkhaunhaplaimatkhau222;
    Button quenmatkhaunhaplaimatkhauxacnhan;
    TextView quyenmatkhaunhaplaimatkhauquaylai;
    ImageButton img_quenmatkhaunhaplaimatkhau111showpass, img_quenmatkhaunhaplaimatkhau222showpass;

    boolean isPasswordVisible = false;
    boolean isPasswordVisible2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quenmatkhaunhaplaimatkhau);

        edt_quenmatkhaunhaplaimatkhau111 = findViewById(R.id.edt_quenmatkhaunhaplaimatkhau111);
        edt_quenmatkhaunhaplaimatkhau222 = findViewById(R.id.edt_quenmatkhaunhaplaimatkhau222);
        quenmatkhaunhaplaimatkhauxacnhan = findViewById(R.id.btn_quenmatkhaunhaplaimatkhauxacnhan);
        img_quenmatkhaunhaplaimatkhau111showpass = findViewById(R.id.img_quenmatkhaunhaplaimatkhau111showpass);
        img_quenmatkhaunhaplaimatkhau222showpass = findViewById(R.id.img_quenmatkhaunhaplaimatkhau222showpass);
        quyenmatkhaunhaplaimatkhauquaylai = findViewById(R.id.quyenmatkhaunhaplaimatkhauquaylai);

        // Nút quay lại màn hình đăng nhập
        quyenmatkhaunhaplaimatkhauquaylai.setOnClickListener(v -> {
            Intent intent = new Intent(Quenmatkhaunhaplaimatkhau.this, Login.class);
            startActivity(intent);
        });

        // Hiện / ẩn mật khẩu
        img_quenmatkhaunhaplaimatkhau111showpass.setOnClickListener(v -> togglePasswordVisibility(edt_quenmatkhaunhaplaimatkhau111));
        img_quenmatkhaunhaplaimatkhau222showpass.setOnClickListener(v -> togglePasswordVisibility(edt_quenmatkhaunhaplaimatkhau222));

        // Nhận username từ Intent (đã truyền từ màn hình quên mật khẩu)
        String username = getIntent().getStringExtra("USENAME");

        // Xác nhận đổi mật khẩu
        quenmatkhaunhaplaimatkhauxacnhan.setOnClickListener(v -> {
            String pass1 = edt_quenmatkhaunhaplaimatkhau111.getText().toString().trim();
            String pass2 = edt_quenmatkhaunhaplaimatkhau222.getText().toString().trim();

            if (pass1.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass1.equals(pass2)) {
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // --- Cập nhật mật khẩu bằng Room ---



            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                int updated = db.userDao().updatePassword(username, pass1);

                runOnUiThread(() -> {
                    if (updated > 0) {
                        Toast.makeText(this, "Đã cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Login.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Không tìm thấy tài khoản!", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }

    // Hàm hiện/ẩn mật khẩu
    private void togglePasswordVisibility(EditText editText) {
        int inputType = editText.getInputType();
        if ((inputType & InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Đang hiển thị -> ẩn
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            // Đang ẩn -> hiển thị
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        editText.setSelection(editText.getText().length()); // Giữ con trỏ cuối
    }
}
