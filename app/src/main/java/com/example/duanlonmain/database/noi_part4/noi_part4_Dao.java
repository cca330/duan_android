package com.example.duanlonmain.database.noi_part4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface noi_part4_Dao {

    @Query("Select * From noi_part4")
    noi_part4 getAllData();

    @Query("Select * From noi_part4 Order by RANDOM() Limit 1")
    noi_part4 getData();

    @Query("Delete From noi_part4")
    void deleteAllData();
    @Insert
    void insertNoiPart4(noi_part4 part4);

    @Delete
    void deleteNoiPart4(noi_part4 part4);

    @Update
    void updateNoiPart4(noi_part4 part4);
}
