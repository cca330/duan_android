package com.example.duanlonmain.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanlonmain.database.noi_part1.*;
import com.example.duanlonmain.database.noi_part2.*;
import com.example.duanlonmain.database.noi_part3.*;
import com.example.duanlonmain.database.noi_part4.*;
import com.example.duanlonmain.database.noi_part5.*;

@Database(entities = {noi_part1.class, noi_part2.class, noi_part3.class, noi_part4.class, noi_part5.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase{
    private static volatile DatabaseHelper INSTANCE;
    public abstract noi_part1_Dao noiPart1Dao();
    public abstract noi_part2_Dao noiPart2Dao();
    public abstract noi_part3_Dao noiPart3Dao();
    public abstract noi_part4_Dao noiPart4Dao();
    public abstract noi_part5_Dao noiPart5Dao();

    public static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseHelper.class, "duanlonmain.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
