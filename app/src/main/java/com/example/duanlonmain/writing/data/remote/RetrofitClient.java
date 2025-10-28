package com.example.duanlonmain.writing.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {

    private static final String LANGUAGETOOL_BASE_URL = "https://api.languagetool.org/";
    private static final String GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/"; // <-- THÊM BASE URL CỦA GEMINI
    private static Retrofit languageToolRetrofit = null;

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("User-Agent", "HcTingAnh-App/1.0")
                        .build();
                return chain.proceed(request);
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public static Retrofit getLanguageToolClient() {
        if (languageToolRetrofit == null) {
            languageToolRetrofit = new Retrofit.Builder()
                    .baseUrl(LANGUAGETOOL_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return languageToolRetrofit;
    }
}