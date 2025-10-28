package com.example.duanlonmain.database.noi_part4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class noi_part4 {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public int context;
    @ColumnInfo
    public String q1;
    @ColumnInfo
    public String q2;
    @ColumnInfo
    public String q3;
    @ColumnInfo(defaultValue = "0")
    public int score;

    public noi_part4(int context, String q1, String q2, String q3) {
        this.context = context;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }
}
