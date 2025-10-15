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
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.duanlonmain.gemini.GeminiEvaluator;

import java.util.ArrayList;

public class noi_part3_deBai_3 extends Activity {
    SpeechRecognizer speechRecognizer;
    String text = "";
    int id = -1;
    String question = "";
    String context = "";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean isRecording = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_blanklayout);

        TextView txt_context = findViewById(R.id.txt_doanVan); txt_context.setVisibility(View.VISIBLE); txt_context.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        TextView txt_cauHoi = findViewById(R.id.txt_cauHoi); txt_cauHoi.setVisibility(View.VISIBLE); txt_cauHoi.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        ImageButton img_ghiAm = findViewById(R.id.img_ghiAm);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM noi_part3 ORDER BY RANDOM() LIMIT 1", null);
        while (cursor.moveToNext()) {

            context = cursor.getString(cursor.getColumnIndexOrThrow("context"));
            question = cursor.getString(cursor.getColumnIndexOrThrow("q3"));
        }
        cursor.close();
        txt_context.setText(context);
        txt_cauHoi.setText(question);

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
                if(error == 7) {
                    Toast.makeText(noi_part3_deBai_3.this, "No speech input recognized. Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(noi_part3_deBai_3.this, "Recognition error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
            @Override public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    text = matches.get(0);
                    String prompt = "Bạn là người đánh giá cách phát âm tiếng Anh. "
                            + "So sánh, chấm điểm và đưa ra nhận xét về phát âm và độ chính xác.\n"
                            + "Spoken: \"" + text + "\"";
                    GeminiEvaluator gemini = new GeminiEvaluator("AIzaSyBIKkVrObVo2IMCKsRWUd35mL1LwwDFSfI");
                    gemini.evaluate(prompt, new GeminiEvaluator.Callback() {
                        @Override
                        public void onSuccess(String feedback) {
                            runOnUiThread(() -> {
                                new AlertDialog.Builder(noi_part3_deBai_3.this)
                                        .setTitle("Feedback")
                                        .setMessage(feedback)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(noi_part3_deBai_3.this, noi_part3_cauHoi.class);
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
                                new AlertDialog.Builder(noi_part3_deBai_3.this)
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
