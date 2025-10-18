package com.example.duanlonmain.listening;

public class Question_bai2 {
    public String[] options;//a b c d
    public String cauhoi;
    public int correctIndex;//location dap an
    public int audioRes;   // file âm thanh
    public String[] answ;
    public int causo;

    public Question_bai2(String[] options,String cauhoi, int correctIndex, int audioRes, String[] answ, int causo){
        this.options=options;
        this.cauhoi=cauhoi;
        this.correctIndex=correctIndex;
        this.audioRes=audioRes;
        this.answ=answ;
        this.causo=causo;
    }


}
