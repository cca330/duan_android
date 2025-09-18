package com.example.duanlonmain;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent; // QUAN TRỌNG: Import Intent
import android.widget.Toast;
import android.widget.ImageButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // Import SQLException


public class Quenmatkhaunhaplaimatkhau extends AppCompatActivity {

    EditText edt_quenmatkhaunhaplaimatkhau111, edt_quenmatkhaunhaplaimatkhau222;
    Button quenmatkhaunhaplaimatkhauxacnhan;
    TextView quyenmatkhaunhaplaimatkhauquaylai;

    boolean isPasswordVisible = false;
    boolean isPasswordVisible2 = false;
    ImageButton img_quenmatkhaunhaplaimatkhau111showpass, img_quenmatkhaunhaplaimatkhau222showpass;


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



        quyenmatkhaunhaplaimatkhauquaylai.setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View v) {
                                                                      Intent intent = new Intent(Quenmatkhaunhaplaimatkhau.this, Login.class);
                                                                      startActivity(intent);
                                                                  }
                                                             });



        img_quenmatkhaunhaplaimatkhau111showpass.setOnClickListener(v -> {
            if (isPasswordVisible) {
                edt_quenmatkhaunhaplaimatkhau111.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                img_quenmatkhaunhaplaimatkhau111showpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt đóng
                isPasswordVisible = false;
            } else {
                edt_quenmatkhaunhaplaimatkhau111.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                img_quenmatkhaunhaplaimatkhau111showpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt mở
                isPasswordVisible = true;
            }

            // Giữ con trỏ ở cuối
//           Lấy độ dài chuỗi hiện tại trong EditText.
//           Đặt lại vị trí con trỏ = cuối chuỗi (sau ký tự cuối).
            edt_quenmatkhaunhaplaimatkhau111.setSelection(edt_quenmatkhaunhaplaimatkhau111.getText().length());
        });




        img_quenmatkhaunhaplaimatkhau222showpass.setOnClickListener(v -> {
            if (isPasswordVisible2) {
                edt_quenmatkhaunhaplaimatkhau222.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                img_quenmatkhaunhaplaimatkhau222showpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt đóng
                isPasswordVisible2 = false;
            } else {
                edt_quenmatkhaunhaplaimatkhau222.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                img_quenmatkhaunhaplaimatkhau222showpass.setImageResource(R.drawable.baseline_remove_red_eye_24); // icon mắt mở
                isPasswordVisible2 = true;
            }

            // Giữ con trỏ ở cuối
//           Lấy độ dài chuỗi hiện tại trong EditText.
//           Đặt lại vị trí con trỏ = cuối chuỗi (sau ký tự cuối).
            edt_quenmatkhaunhaplaimatkhau222.setSelection(edt_quenmatkhaunhaplaimatkhau222.getText().length());
        });






        // Lấy username từ Intent
        String username = getIntent().getStringExtra("USENAME");

        quenmatkhaunhaplaimatkhauxacnhan.setOnClickListener(new View.OnClickListener() {
            //            co ovrride giup equals so sanh chuoi string thay vi dia chi bo nho
            @Override
            public void onClick(View v) {
                if (edt_quenmatkhaunhaplaimatkhau111.getText().toString().equals(edt_quenmatkhaunhaplaimatkhau222.getText().toString())) {

                    // Thực hiện thay đổi mật khẩu trong một luồng riêng biệt
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection conn = ConnectionClass.CONN();
                                if (conn != null) {
                                    String query = "UPDATE USE_PASSWORD SET PASSWORD=? WHERE USENAME=?";
                                    PreparedStatement ps = conn.prepareStatement(query);
                                    ps.setString(1, edt_quenmatkhaunhaplaimatkhau111.getText().toString());
                                    ps.setString(2, username);
                                    ps.executeUpdate();
                                    conn.close();

//                                    Còn khi bạn kết nối SQL (JDBC) → bạn đang chạy trong Thread riêng (new Thread(...)) để không bị crash.
//                                    Do đó, nếu bạn gọi Toast.makeText(...) trực tiếp trong Thread phụ, Android sẽ báo lỗi Only the original thread that created a view hierarchy can touch its views.
//                                    Vì vậy, bạn phải bao nó trong runOnUiThread(...) để chuyển việc hiển thị Toast về Main Thread.
                                    runOnUiThread(() ->
                                            Toast.makeText(Quenmatkhaunhaplaimatkhau.this, "Mật khẩu đã được thay đổi", Toast.LENGTH_SHORT).show()
                                    );
                                    Intent intent = new Intent(Quenmatkhaunhaplaimatkhau.this, Login.class);
                                    startActivity(intent);
                                } else {
                                    runOnUiThread(() ->
                                            Toast.makeText(Quenmatkhaunhaplaimatkhau.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show()
                                    );
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                                runOnUiThread(() ->
                                        Toast.makeText(Quenmatkhaunhaplaimatkhau.this, "Lỗi cơ sở dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show()
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                                runOnUiThread(() ->
                                        Toast.makeText(Quenmatkhaunhaplaimatkhau.this, "Đã xảy ra lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show()
                                );
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(Quenmatkhaunhaplaimatkhau.this, "Mật khẩu không ko trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
