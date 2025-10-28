package com.example.duanlonmain.writing.data.responsitory;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.MutableLiveData;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.*;
import com.example.duanlonmain.writing.data.remote.LanguageToolApi;
import com.example.duanlonmain.writing.data.remote.RetrofitClient;
import com.example.duanlonmain.writing.data.remote.LanguageToolResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingRepository {

    private static final String GEMINI_API_KEY = "AIzaSyDIet7saT20mNZ734GfuJGJhAdVEUTHP9g";
    private static final long MIN_DELAY = 5000;

    private final GenerativeModelFutures model;
    private final LanguageToolApi languageToolApi;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private long lastRequestTime = 0;

    public WritingRepository() {
        GenerativeModel gm = new GenerativeModel("gemini-2.5-flash", GEMINI_API_KEY);
        this.model = GenerativeModelFutures.from(gm);
        this.languageToolApi = RetrofitClient.getLanguageToolClient().create(LanguageToolApi.class);
    }

    // CHẤM BÀI BẰNG GEMINI
    public void gradeEssay(String prompt, String essay, AICallback callback) {
        long now = System.currentTimeMillis();
        if (now - lastRequestTime < MIN_DELAY) {
            long wait = MIN_DELAY - (now - lastRequestTime);
            new Handler(Looper.getMainLooper()).postDelayed(() -> sendGeminiRequest(prompt, essay, callback), wait);
        } else {
            sendGeminiRequest(prompt, essay, callback);
        }
    }

    private void sendGeminiRequest(String prompt, String essay, AICallback callback) {
        lastRequestTime = System.currentTimeMillis();
        isLoading.postValue(true);

        String fullPrompt = prompt + "\n\nEssay: " + essay +
                "\n\nChấm điểm từ 0-100, sửa lỗi, phản hồi ngắn gọn.";
        Content content = new Content.Builder().addText(fullPrompt).build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                isLoading.postValue(false);
                String reply = result.getText();
                parseGeminiResult(reply, callback);
            }

            @Override
            public void onFailure(Throwable t) {
                isLoading.postValue(false);
                callback.onError("Lỗi Gemini: " + t.getMessage());
            }
        }, executor);
    }

    private void parseGeminiResult(String text, AICallback callback) {
        try {
            int score = extractScore(text);
            String feedback = extractSection(text, "phản hồi", "sửa");
            String corrected = extractSection(text, "sửa", "kết thúc");
            callback.onGradeResult(score, feedback.trim(), corrected.trim());
        } catch (Exception e) {
            callback.onError("Lỗi phân tích kết quả Gemini: " + e.getMessage());
        }
    }

    private int extractScore(String text) {
        Matcher m = Pattern.compile("\\b(\\d+)\\b").matcher(text);
        return m.find() ? Integer.parseInt(m.group(1)) : 75;
    }

    private String extractSection(String text, String start, String end) {
        int s = text.toLowerCase().indexOf(start);
        int e = text.toLowerCase().indexOf(end, s + 1);
        if (s == -1) return "";
        if (e == -1) e = text.length();
        return text.substring(s, e).trim();
    }

    // KIỂM TRA LỖI NGỮ PHÁP
    public void checkGrammar(String text, GrammarCallback callback) {
        isLoading.postValue(true);
        languageToolApi.checkGrammar(text, "en-US")
                .enqueue(new Callback<LanguageToolResponse>() {
                    @Override
                    public void onResponse(Call<LanguageToolResponse> call, Response<LanguageToolResponse> response) {
                        isLoading.postValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            StringBuilder errors = new StringBuilder();
                            for (LanguageToolResponse.Match match : response.body().matches) {
                                String original = text.substring(match.offset, match.offset + match.length);
                                String suggest = match.replacements.isEmpty() ? "xóa" : match.replacements.get(0).value;
                                errors.append("• \"").append(original)
                                        .append("\" → \"").append(suggest)
                                        .append("\" (").append(match.message).append(")\n");
                            }
                            callback.onGrammarResult(errors.toString());
                        } else {
                            callback.onError("Lỗi LanguageTool: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<LanguageToolResponse> call, Throwable t) {
                        isLoading.postValue(false);
                        callback.onError("Mạng lỗi: " + t.getMessage());
                    }
                });
    }

    public interface AICallback {
        void onGradeResult(int score, String feedback, String corrected);
        void onError(String error);
    }

    public interface GrammarCallback {
        void onGrammarResult(String errors);
        void onError(String error);
    }
}