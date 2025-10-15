package com.example.duanlonmain;

public class menu_gridItem {
    String imgId;
    String part;
    String title;

    public menu_gridItem(String imgId, String part, String title) {
        this.imgId = imgId;
        this.part = part;
        this.title = title;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
