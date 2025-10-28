package com.example.duanlonmain.database.noi_part1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface noi_part1_Dao {

    @Query("Select * From noi_part1")
    List<noi_part1> getAllData();

    @Query("Select paragram From noi_part1 Order by RANDOM() Limit 1")
    String getData();

    @Query("Delete From noi_part1")
    void deleteAllData();

    @Insert
    void insertNoiPart1(noi_part1 part1);

    @Delete
    void deleteNoiPart1(noi_part1 part1);

    @Update
    void updateNoiPart1(noi_part1 part1);
}

