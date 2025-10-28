package com.example.duanlonmain.database.noi_part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class noi_part2 {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "picture")
    public int picture;
    @ColumnInfo
    public int score;

    public noi_part2(int picture) {
        this.picture = picture;
    }
}
