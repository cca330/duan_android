package com.example.duanlonmain.writing.data.local.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.duanlonmain.writing.data.local.model.WritingTopic;
import com.example.duanlonmain.writing.data.local.model.UserWriting;

@Database(entities = {WritingTopic.class, UserWriting.class}, version = 1)
public abstract class WritingDatabase extends RoomDatabase {
    public abstract WritingTopicDao exerciseDao();
    private static WritingDatabase instance;

    public static WritingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, WritingDatabase.class, "writing_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
