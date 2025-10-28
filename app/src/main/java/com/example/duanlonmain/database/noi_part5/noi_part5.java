package com.example.duanlonmain.database.noi_part5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class noi_part5 {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String question;
    @ColumnInfo(defaultValue = "0")
    public int score;

    public noi_part5(String question) {
        this.question = question;
    }
}
