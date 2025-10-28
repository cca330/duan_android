package com.example.duanlonmain.writing.data.local.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.duanlonmain.writing.data.local.model.WritingTopic;

@Dao
public interface WritingTopicDao {
    @Query("SELECT * FROM WritingTopic WHERE id = :id")
    WritingTopic getById(int id);

    @Insert
    void insert(WritingTopic exercise);
}