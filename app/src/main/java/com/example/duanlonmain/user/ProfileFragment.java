package com.example.duanlonmain.user;




import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanlonmain.R;


// Giả sử bạn có một Activity để đăng nhập/đăng ký
// import com.example.hctinganh.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    // Khai báo các biến cho tất cả các view trong layout
    private ImageView ivAvatar;
    private TextView tvDisplayName, tvEmail;
    private ImageButton btnEditName;
    private TextView btnChangePassword, btnLogout, btnDeleteAccount;
    private ProgressBar progressBar;
    private ImageButton btnback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Kết nối file layout fragment_profile.xml với class này
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Ánh xạ các view từ layout
        initViews(view);

        // Tải dữ liệu người dùng (ví dụ)
        loadUserData();

        // Thiết lập sự kiện click cho các nút
        setupClickListeners();

        return view;
    }

    /**
     * Phương thức để ánh xạ các view từ file layout.
     * @param view View gốc của Fragment.
     */
    private void initViews(View view) {
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvDisplayName = view.findViewById(R.id.tv_display_name);
        tvEmail = view.findViewById(R.id.tv_email);
        btnEditName = view.findViewById(R.id.btn_edit_name);
        btnChangePassword = view.findViewById(R.id.btn_change_password);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnDeleteAccount = view.findViewById(R.id.btn_delete_account);
        progressBar = view.findViewById(R.id.progress_bar);
        btnback = view.findViewById(R.id.btn_back);
    }

    /**
     * Tải và hiển thị thông tin người dùng.
     * (Đây là nơi bạn sẽ gọi API hoặc đọc dữ liệu từ SharedPreferences/Database)
     */
    private void loadUserData() {
        // Ví dụ dữ liệu cứng, bạn sẽ thay thế bằng dữ liệu thật
        progressBar.setVisibility(View.VISIBLE); // Giả vờ đang tải

        // Giả lập việc tải dữ liệu xong sau 2 giây
        new android.os.Handler().postDelayed(() -> {
            tvDisplayName.setText("Dao Quang Tan");
            tvEmail.setText("quangtan@example.com");
            // Dùng thư viện Picasso hoặc Glide để tải ảnh đại diện từ URL
            // Picasso.get().load("URL_AVATAR_CUA_BAN").into(ivAvatar);
            progressBar.setVisibility(View.GONE);
        }, 1000);
    }

    /**
     * Thiết lập các sự kiện click cho các nút và mục menu.
     */
    private void setupClickListeners() {
        // Sự kiện click để thay đổi avatar
        ivAvatar.setOnClickListener(v -> {
            showToast("Chọn ảnh đại diện mới...");
            // TODO: Mở thư viện ảnh để người dùng chọn ảnh
        });
        btnback.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        // Sự kiện click để sửa tên
        btnEditName.setOnClickListener(v -> {
            showToast("Mở màn hình sửa tên...");
            // TODO: Mở một dialog hoặc activity để người dùng nhập tên mới
        });

        // Sự kiện click để đổi mật khẩu
        btnChangePassword.setOnClickListener(v -> {
            showToast("Mở màn hình đổi mật khẩu...");
            // TODO: Mở màn hình/dialog đổi mật khẩu
        });

        // Sự kiện click để đăng xuất
        btnLogout.setOnClickListener(v -> {
            // Hiển thị hộp thoại xác nhận trước khi đăng xuất
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác nhận đăng xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                    .setPositiveButton("Đăng xuất", (dialog, which) -> {
                        // Thực hiện logic đăng xuất ở đây
                        // Ví dụ: Xóa token, thông tin người dùng đã lưu
                        showToast("Đã đăng xuất");

                        // Chuyển về màn hình đăng nhập
                        // Intent intent = new Intent(getActivity(), LoginActivity.class);
                        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        // startActivity(intent);
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        // Sự kiện click để xóa tài khoản
        btnDeleteAccount.setOnClickListener(v -> {
            // Hiển thị hộp thoại xác nhận có tính cảnh báo cao
            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa tài khoản")
                    .setMessage("Hành động này không thể hoàn tác. Tất cả dữ liệu của bạn sẽ bị xóa vĩnh viễn. Bạn có chắc chắn muốn tiếp tục?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        // Thực hiện logic xóa tài khoản ở đây
                        showToast("Tài khoản đã được xóa.");
                        // Chuyển về màn hình đăng nhập
                        // Intent intent = new Intent(getActivity(), LoginActivity.class);
                        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        // startActivity(intent);
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    /**
     * Phương thức trợ giúp để hiển thị Toast nhanh hơn.
     * @param message Nội dung cần hiển thị.
     */
    private void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}

