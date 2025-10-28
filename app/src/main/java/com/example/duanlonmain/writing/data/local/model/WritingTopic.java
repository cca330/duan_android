package com.example.duanlonmain.writing.data.local.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Bảng lưu chủ đề viết
 * Dùng với Room Database
 */
@Entity(tableName = "WritingTopic")
public class WritingTopic {

    @PrimaryKey
    public int id;

    public String title;
    public String instruction;
    public int minWords;

    // Constructor đầy đủ
    public WritingTopic(int id, String title, String instruction, int minWords) {
        this.id = id;
        this.title = title;
        this.instruction = instruction;
        this.minWords = minWords;
    }

    // Constructor không ID (nếu dùng autoGenerate)

}