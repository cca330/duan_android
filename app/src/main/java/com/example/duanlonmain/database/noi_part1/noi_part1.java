package com.example.duanlonmain.database.noi_part1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class noi_part1 {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String paragram;
    @ColumnInfo(defaultValue = "0")
    public int score;

    public noi_part1(String paragram) {
        this.paragram = paragram;
    }
}

