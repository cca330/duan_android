package com.example.duanlonmain.noi_part1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.duanlonmain.R;
import com.example.duanlonmain.GeminiEvaluator;

import java.util.ArrayList;

public class noi_part1_deBai extends Activity {
    SpeechRecognizer speechRecognizer;
    String text = "";
    int id = -1;
    String doanVan = "";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean isRecording = false;

    OnBackPressedDispatcher callback = new OnBackPressedDispatcher();
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_blanklayout);

        TextView txt_doanVan = findViewById(R.id.txt_doanVan);
        txt_doanVan.setVisibility(View.VISIBLE);
        txt_doanVan.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        ImageButton img_ghiAm = findViewById(R.id.img_ghiAm);
        ImageButton img_back = findViewById(R.id.img_back);

        txt_doanVan.setText(getIntent().getStringExtra("doanVan"));

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(noi_part1_deBai.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có chắc muốn quay lại không? Bài làm của bạn sẽ không được lưu.");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(noi_part1_deBai.this, noi_part1_cauHoi.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Không", null);
                builder.show();
            }
        });

        img_ghiAm.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(noi_part1_deBai.this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(noi_part1_deBai.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_AUDIO_PERMISSION);
                Toast.makeText(noi_part1_deBai.this, "Please grant audio recording permission", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isRecording) {
                isRecording = true;
                startSpeechRecognition();
                Toast.makeText(noi_part1_deBai.this, "Recording...", Toast.LENGTH_SHORT).show();
            } else {
                isRecording = false;
                speechRecognizer.stopListening();
            }
        });


    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onError(int error) {
                if(error == 7) {
                    Toast.makeText(noi_part1_deBai.this, "No speech input recognized.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(noi_part1_deBai.this, "Recognition error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
            @Override public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    text = matches.get(0);
                    String prompt = "Bạn là người đánh giá cách phát âm tiếng Anh. "
                            + "So sánh, chấm điểm và đưa ra nhận xét về phát âm và độ chính xác.\n"
                            + "Reference: \"" + doanVan + "\"\n"
                            + "Spoken: \"" + text + "\"";
                    GeminiEvaluator gemini = new GeminiEvaluator("AIzaSyBIKkVrObVo2IMCKsRWUd35mL1LwwDFSfI");
                    gemini.evaluate(prompt, new GeminiEvaluator.Callback() {
                        @Override
                        public void onSuccess(String feedback) {
                            runOnUiThread(() -> {
                                new AlertDialog.Builder(noi_part1_deBai.this)
                                        .setTitle("Feedback")
                                        .setMessage(feedback)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(noi_part1_deBai.this, noi_part1_cauHoi.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        })
                                        .show();
                            });
                        }
                        @Override
                        public void onError(String error) {
                            runOnUiThread(() -> {
                                new AlertDialog.Builder(noi_part1_deBai.this)
                                        .setTitle("Error")
                                        .setMessage(error)
                                        .setPositiveButton("OK", null)
                                        .show();
                            });
                        }
                    });
                }
            }
            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
        });

        speechRecognizer.startListening(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }



}
