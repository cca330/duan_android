package com.example.duanlonmain.vocabulary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

// Đây là INTERFACE, không phải class thường
//Class bình thường (class) thì bạn viết ra và có thể tạo đối tượng bằng new.
// Interface (interface) chỉ định nghĩa hàm abstract (chưa có code xử lý), còn phần code thực sự sẽ do Retrofit tự sinh ra khi bạn gọi.
public interface ApiService {
    @GET("entries/en/{word}")//Retrofit sẽ nhìn vào cái @GET("entries/en/{word}") này rồi tự động sinh code gọi API thật cho bạn.
    Call<List<WordResponse>> getWordMeaning(@Path("word") String word);
}
