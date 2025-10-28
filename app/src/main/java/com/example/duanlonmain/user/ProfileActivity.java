package com.example.duanlonmain.user;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.duanlonmain.R;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Chỉ tải Fragment nếu đây là lần đầu tiên Activity được tạo
        if (savedInstanceState == null) {
            // Tạo một đối tượng của ProfileFragment
            ProfileFragment profileFragment = new ProfileFragment();

            // Bắt đầu một transaction để thêm Fragment vào container
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.profile_container, profileFragment);
            transaction.commit();
        }
    }
}
