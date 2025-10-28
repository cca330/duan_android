package com.example.duanlonmain.writing.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WritingResultViewModel extends ViewModel {

    private final MutableLiveData<Integer> score = new MutableLiveData<>();
    private final MutableLiveData<String> feedback = new MutableLiveData<>();
    private final MutableLiveData<String> corrected = new MutableLiveData<>();

    // Nhận dữ liệu từ Activity
    public void setResult(int score, String feedback, String corrected) {
        this.score.postValue(score);
        this.feedback.postValue(feedback);
        this.corrected.postValue(corrected);
    }

    // Getters
    public LiveData<Integer> getScore() { return score; }
    public LiveData<String> getFeedback() { return feedback; }
    public LiveData<String> getCorrected() { return corrected; }
}