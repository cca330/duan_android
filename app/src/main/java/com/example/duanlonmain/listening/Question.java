package com.example.duanlonmain.listening;

public class Question {
    public String[] options;
    public int correctIndex;//dap an
    public int imageRes;   // ảnh minh họa
    public int audioRes;   // file âm thanh
    public String[] answ;
    public int socau;

    public Question(String[] options, int correctIndex, int imageRes, int audioRes,String[] answ,int socau) {
        this.options = options;
        this.correctIndex = correctIndex;
        this.imageRes = imageRes;
        this.audioRes = audioRes;
        this.answ=answ;
        this.socau=socau;

    }
}