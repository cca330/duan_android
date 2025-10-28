package com.example.duanlonmain.writing.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.duanlonmain.R;
import com.example.duanlonmain.writing.viewmodel.WritingResultViewModel;
import com.google.android.material.button.MaterialButton;

public class WritingResultActivity extends AppCompatActivity {

    public static final int RESULT_RETRY = 100;
    public static final int RESULT_NEXT = 101;

    private TextView tvScore, tvFeedback, tvCorrected;
    private ProgressBar progressCircle;
    private MaterialButton btnRetry, btnNext;
    private ImageButton btnBack;
    private WritingResultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_result);

        initViews();
        setupViewModel();
        loadDataFromIntent();  // GỌI TRƯỚC observe
        observeViewModel();
        setupButtons();
    }

    private void initViews() {
        tvScore = findViewById(R.id.tv_score);
        tvFeedback = findViewById(R.id.tv_feedback);
        tvCorrected = findViewById(R.id.tv_corrected);
        progressCircle = findViewById(R.id.progress_circle);
        btnRetry = findViewById(R.id.btn_retry);
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back); // 2. ÁNH XẠ VIEW TỪ XML
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(WritingResultViewModel.class);
    }

    private void loadDataFromIntent() {
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        String feedback = intent.getStringExtra("FEEDBACK");
        String corrected = intent.getStringExtra("CORRECTED");

        // GỬI DỮ LIỆU VÀO VIEWMODEL
        viewModel.setResult(score, feedback, corrected);
    }

    private void observeViewModel() {
        viewModel.getScore().observe(this, score -> {
            tvScore.setText(String.valueOf(score));
            progressCircle.setProgress(score);
        });

        viewModel.getFeedback().observe(this, feedback -> {
            tvFeedback.setText(feedback != null ? feedback : "Không có phản hồi.");
        });

        viewModel.getCorrected().observe(this, corrected -> {
            tvCorrected.setText(corrected != null ? corrected : "Không có bản sửa.");
        });
    }

    private void setupButtons() {
        btnRetry.setOnClickListener(v -> {
            setResult(RESULT_RETRY);
            finish();
        });

        btnNext.setOnClickListener(v -> {
            setResult(RESULT_NEXT);
            finish();
        });
        btnBack.setOnClickListener(v -> {
            finish(); // Đơn giản là đóng Activity hiện tại và quay về màn hình trước đó
        });
    }
}