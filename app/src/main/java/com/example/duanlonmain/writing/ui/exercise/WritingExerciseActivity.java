package com.example.duanlonmain.writing.ui.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.duanlonmain.R;
import com.example.duanlonmain.writing.ui.result.WritingResultActivity;
import com.example.duanlonmain.writing.viewmodel.WritingExerciseViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;

public class WritingExerciseActivity extends AppCompatActivity {

    private TextInputEditText editText;
    private TextView tvTopicTitle, tvInstruction, tvWordCount, tvCorrections;
    private MaterialCardView cardCorrections;
    private MaterialButton btnCheck, btnSubmit;
    private WritingExerciseViewModel viewModel;
    private ImageButton btnImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_exercise);

        initViews();
        setupViewModel();
        loadTopicData();
        setupWordCount();
        setupButtons();
        observeViewModel();
    }

    private void initViews() {
        editText = findViewById(R.id.edit_text);
        tvTopicTitle = findViewById(R.id.tv_topic_title);
        tvInstruction = findViewById(R.id.tv_instruction);
        tvWordCount = findViewById(R.id.tv_word_count);
        tvCorrections = findViewById(R.id.tv_corrections);
        cardCorrections = findViewById(R.id.card_corrections);
        btnCheck = findViewById(R.id.btn_check);
        btnSubmit = findViewById(R.id.btn_submit);
        btnImageButton = findViewById(R.id.btn_back);

    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(WritingExerciseViewModel.class);
    }

    private void loadTopicData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String instruction = intent.getStringExtra("INSTRUCTION");
        int minWords = intent.getIntExtra("MIN_WORDS", 80);

        tvTopicTitle.setText(title != null ? title : "Chủ đề");
        tvInstruction.setText("MỤC ĐÍCH: " + (instruction != null ? instruction : "Viết theo yêu cầu."));
        tvWordCount.setText("0/" + minWords + " từ");
    }

    private void setupWordCount() {
        int minWords = getIntent().getIntExtra("MIN_WORDS", 80);
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                int wordCount = text.isEmpty() ? 0 : text.split("\\s+").length;
                tvWordCount.setText(wordCount + "/" + minWords + " từ");
                btnSubmit.setEnabled(wordCount >= minWords && !Boolean.TRUE.equals(viewModel.isLoading().getValue()));
            }
        });
    }

    private void setupButtons() {
        btnCheck.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.checkGrammar(text);
        });
        btnImageButton.setOnClickListener(v -> {
            finish();
        });

        btnSubmit.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (text.split("\\s+").length < 80) {
                Toast.makeText(this, "Cần ít nhất 80 từ!", Toast.LENGTH_SHORT).show();
                return;
            }
            String prompt = tvTopicTitle.getText().toString();
            viewModel.gradeEssay(prompt, text);
        });
    }

    private void observeViewModel() {
        viewModel.getGrammarErrors().observe(this, errors -> {
            if (errors != null && !errors.isEmpty()) {
                cardCorrections.setVisibility(View.VISIBLE);
                tvCorrections.setText(errors);
            } else {
                cardCorrections.setVisibility(View.GONE);
            }
        });

        viewModel.getScore().observe(this, score -> {
            if (score != null) {
                Intent intent = new Intent(this, WritingResultActivity.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("FEEDBACK", viewModel.getFeedback().getValue());
                intent.putExtra("CORRECTED", viewModel.getCorrected().getValue());
                startActivityForResult(intent, 1);
            }
        });

        viewModel.isLoading().observe(this, loading -> {
            btnSubmit.setEnabled(!loading && editText.getText().toString().trim().split("\\s+").length >= 80);
            btnCheck.setEnabled(!loading);
        });

        viewModel.getError().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Lỗi")
                        .setMessage(error)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == WritingResultActivity.RESULT_RETRY) {
            editText.setText("");
            editText.requestFocus();
        }
    }
}