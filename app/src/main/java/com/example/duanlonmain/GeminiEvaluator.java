package com.example.duanlonmain;


import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class GeminiEvaluator {
    private final String geminiKey;
    private final OkHttpClient client = new OkHttpClient();

    public GeminiEvaluator(String geminiKey) {
        this.geminiKey = geminiKey;
    }

    public interface Callback {
        void onSuccess(String feedback);
        void onError(String error);
    }

    public void evaluate(String prompt, Callback callback) {

        JSONObject body = new JSONObject();
        try {
            JSONArray contents = new JSONArray();
            JSONObject part = new JSONObject();
            part.put("text", prompt);
            JSONObject parts = new JSONObject();
            parts.put("parts", new JSONArray().put(part));
            contents.put(parts);
            body.put("contents", contents);
        } catch (Exception e) {
            callback.onError("Failed to construct JSON body: " + e.getMessage());
            return;
        }

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + geminiKey)
                .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String res = response.body().string();
                    JSONObject json = new JSONObject(res);
                    String feedback = json
                            .getJSONArray("candidates")
                            .getJSONObject(0)
                            .getJSONObject("content")
                            .getJSONArray("parts")
                            .getJSONObject(0)
                            .getString("text");
                    callback.onSuccess(feedback);
                } catch (Exception e) {
                    callback.onError("Failed to parse response: " + e.getMessage());
                }
            }
        });
    }
}
