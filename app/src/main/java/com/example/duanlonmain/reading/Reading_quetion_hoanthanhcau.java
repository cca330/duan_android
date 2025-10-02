package com.example.duanlonmain.reading;

public class Reading_quetion_hoanthanhcau {
    public String[] options;

    public String cauhoi;
    public String giaithichcauhoi;
    public int dapan;
    public String[] dichdapan;
    public int socau;
    public String[] giaithichdapan;


    public Reading_quetion_hoanthanhcau(String[] op,String cauhoi, String giaithichcauhoi, int dapan,String[] dichdapan, int socau, String[] giaithichdapan){
        this.options=op;
        this.cauhoi=cauhoi;
        this.giaithichcauhoi=giaithichcauhoi;
        this.dapan=dapan;
        this.dichdapan=dichdapan;
        this.socau=socau;
        this.giaithichdapan=giaithichdapan;
    }


}
