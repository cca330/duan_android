package com.example.duanlonmain.database.noi_part5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface noi_part5_Dao {
    @Query("Select * From noi_part5")
    List<noi_part5> getAllData();

    @Query("Select question From noi_part5 Order by RANDOM() Limit 1")
    String getData();

    @Query("Delete From noi_part5")
    void deleteAllData();
    @Insert
    void insertNoiPart5(noi_part5 part5);

    @Delete
    void deleteNoiPart5(noi_part5 part5);

    @Update
    void updateNoiPart5(noi_part5 part5);
}
