package com.example.duanlonmain.writing.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.duanlonmain.writing.data.responsitory.WritingRepository;


public class WritingExerciseViewModel extends ViewModel {

    private final WritingRepository repository = new WritingRepository();
    private final MutableLiveData<Integer> score = new MutableLiveData<>();
    private final MutableLiveData<String> feedback = new MutableLiveData<>();
    private final MutableLiveData<String> corrected = new MutableLiveData<>();
    private final MutableLiveData<String> grammarErrors = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();

    // === CHẤM BÀI TỔNG THỂ (OPENAI) ===
    public void gradeEssay(String prompt, String essay) {
        isLoading.postValue(true);
        repository.gradeEssay(prompt, essay, new WritingRepository.AICallback() {
            @Override
            public void onGradeResult(int s, String f, String c) {
                score.postValue(s);
                feedback.postValue(f);
                corrected.postValue(c);
                isLoading.postValue(false);
            }

            @Override
            public void onError(String e) {
                error.postValue(e);
                isLoading.postValue(false);
            }
        });
    }

    // === KIỂM TRA LỖI NGỮ PHÁP (LANGAUGETOOL) ===
    public void checkGrammar(String text) {
        repository.checkGrammar(text, new WritingRepository.GrammarCallback() {
            @Override
            public void onGrammarResult(String errors) {
                grammarErrors.postValue(errors);  // ĐÚNG: postValue trong onResponse
            }

            @Override
            public void onError(String e) {
                grammarErrors.postValue("Lỗi kiểm tra: " + e);  // ĐÚNG
            }
        });
    }

    // === GETTERS ===
    public LiveData<Integer> getScore() { return score; }
    public LiveData<String> getFeedback() { return feedback; }
    public LiveData<String> getCorrected() { return corrected; }
    public LiveData<String> getGrammarErrors() { return grammarErrors; }
    public LiveData<Boolean> isLoading() { return isLoading; }
    public LiveData<String> getError() { return error; }
}