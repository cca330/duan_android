package com.example.duanlonmain.listening;

public class Question {
    public String[] options;
    public int correctIndex;
    public int imageRes;   // ảnh minh họa
    public int audioRes;   // file âm thanh

    public Question(String[] options, int correctIndex, int imageRes, int audioRes) {
        this.options = options;
        this.correctIndex = correctIndex;
        this.imageRes = imageRes;
        this.audioRes = audioRes;
    }
}