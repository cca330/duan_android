package com.example.duanlonmain;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast; // Dòng này cần thiết để sử dụng Toast
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.Executors; // Thêm dòng này
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    java.sql.Connection con;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ConnectionClass connectionClass = new ConnectionClass();
        con = connectionClass.CONN();
        conect();//goi phuong thuc de kiem tra va hien thi
    }

    public void conect() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Kiểm tra xem con có null không (sau khi đã cố gắng kết nối trong onCreate)
                if (con == null) {
                    str = "Connection Failed";
                } else {
                    // Bạn có thể thử một truy vấn đơn giản ở đây để chắc chắn kết nối hoạt động
                    // Ví dụ: Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT 1");
                    // if(rs.next()) str = "Connection Success"; else str = "Query Failed";
                    // Nhớ đóng rs và stmt
                    str = "Connection Success";
                }
            } catch (Exception e) { // Bắt Exception chung nếu có lỗi khác
                str = "Connection Error: " + e.getMessage();
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });
    }



}
