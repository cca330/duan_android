package com.example.duanlonmain.writing.data.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LanguageToolApi {
    @FormUrlEncoded
    @POST("v2/check")
    Call<LanguageToolResponse> checkGrammar(
            @Field("text") String text,
            @Field("language") String language
    );
}
