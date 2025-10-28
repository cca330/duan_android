package com.example.duanlonmain.extend.Grammar;

public class Tense {
    private String tenThi;
    private String cachDung;
    private String khangDinh;
    private String phuDinh;
    private String nghiVan;
    private String viDu;
    private int imageResId;

    public Tense(String tenThi, String cachDung, String khangDinh, String phuDinh,
                 String nghiVan, String viDu, int imageResId) {
        this.tenThi = tenThi;
        this.cachDung = cachDung;
        this.khangDinh = khangDinh;
        this.phuDinh = phuDinh;
        this.nghiVan = nghiVan;
        this.viDu = viDu;
        this.imageResId = imageResId;
    }

    public String getTenThi() { return tenThi; }
    public String getCachDung() { return cachDung; }
    public String getKhangDinh() { return khangDinh; }
    public String getPhuDinh() { return phuDinh; }
    public String getNghiVan() { return nghiVan; }
    public String getViDu() { return viDu; }
    public int getImageResId() { return imageResId; }
}
