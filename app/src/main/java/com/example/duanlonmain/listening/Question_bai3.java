package com.example.duanlonmain.listening;

public class Question_bai3 {
    public String[] options;
    public String hoithoai;
    public String[] cauhoi;
    public String[] answ;
    public int[] dapan;
    public int causo;
    public int audio;
    public int[] selectedAnswers;

    public Question_bai3(String[] options, String hoithoai, String[] cauhoi, String[] answ, int[] dapan, int causo, int audio){
        this.options = options;
        this.hoithoai = hoithoai;
        this.cauhoi = cauhoi;
        this.answ = answ;
        this.dapan = dapan;
        this.causo = causo;
        this.audio = audio;
        this.selectedAnswers = new int[]{-1, -1, -1};
    }

}
