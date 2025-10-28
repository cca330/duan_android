package com.example.duanlonmain.database.noi_part3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface noi_part3_Dao {

    @Query("Select * From noi_part3")
    noi_part3 getAllData();

    @Query("Select * From noi_part3 Order by RANDOM() Limit 1")
    noi_part3 getData();

    @Query("Delete From noi_part3")
    void deleteAllData();
    @Insert
    void insertNoiPart3(noi_part3 part3);

    @Delete
    void deleteNoiPart3(noi_part3 part3);

    @Update
    void updateNoiPart3(noi_part3 part3);
}
