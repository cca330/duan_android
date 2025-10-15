package com.example.duanlonmain;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.duanlonmain.gemini.GeminiEvaluator;

import java.util.ArrayList;

public class noi_part4_deBai_1 extends Activity {
    int id = -1;
    String text = "";
    int context = 0;
    String question = "";
    SpeechRecognizer speechRecognizer;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean isRecording = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_blanklayout);

        ImageView img_hinhAnh = findViewById(R.id.img_hinhAnh); img_hinhAnh.setVisibility(View.VISIBLE);
        TextView txt_cauHoi = findViewById(R.id.txt_cauHoi); txt_cauHoi.setVisibility(View.VISIBLE); txt_cauHoi.setTextSize(20);
        ImageButton img_ghiAm = findViewById(R.id.img_ghiAm);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM noi_part4 ORDER BY RANDOM() LIMIT 1", null);
        if (cursor.moveToNext()) {
            context = cursor.getInt(cursor.getColumnIndex("context"));
            question = cursor.getString(cursor.getColumnIndex("q1"));
            img_hinhAnh.setImageResource(context);
            txt_cauHoi.setText(question);
        }
        cursor.close();

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        img_ghiAm.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_AUDIO_PERMISSION);
                Toast.makeText(this, "Please grant audio recording permission", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isRecording) {
                isRecording = true;
                startSpeechRecognition();
                Toast.makeText(this, "Recording...", Toast.LENGTH_SHORT).show();
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
                if(error == 7) Toast.makeText(noi_part4_deBai_1.this, "No speech input. Please try again.", Toast.LENGTH_SHORT).show();
                else Toast.makeText(noi_part4_deBai_1.this, "Recognition error: " + error, Toast.LENGTH_SHORT).show();
            }
            @Override public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    text = matches.get(0);
                    String prompt = "Bạn là người đánh giá cách phát âm tiếng Anh. "
                            + "So sánh, chấm điểm và đưa ra nhận xét về phát âm và độ chính xác."
                            + "Spoken: \"" + text + "\"";
                    GeminiEvaluator gemini = new GeminiEvaluator("AIzaSyBIKkVrObVo2IMCKsRWUd35mL1LwwDFSfI");
                    gemini.evaluate(prompt, new GeminiEvaluator.Callback() {
                        @Override
                        public void onSuccess(String feedback) {
                            runOnUiThread(() -> {
                                new AlertDialog.Builder(noi_part4_deBai_1.this)
                                        .setTitle("Feedback")
                                        .setMessage(feedback)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(noi_part4_deBai_1.this, noi_part4_deBai_2.class);
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
                                new AlertDialog.Builder(noi_part4_deBai_1.this)
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
