package com.example.duanlonmain.writing.data.local.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserWriting {
    @PrimaryKey(autoGenerate = true) public int id;
    public int exerciseId;
    public String userAnswer;
    public Integer score;
    public String feedback;
    public long submittedAt;
    public boolean isSynced = false;
}
