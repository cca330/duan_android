package com.example.duanlonmain.database.noi_part2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface noi_part2_Dao {

    @Query("Select * From noi_part2")
    List<noi_part2> getAllData();

    @Query("Select picture From noi_part2 Order by RANDOM() Limit 1")
    int getData();

    @Query("Delete From noi_part2")
    void deleteAllData();

    @Insert
    void insertNoiPart2(noi_part2 part2);

    @Delete
    void deleteNoiPart2(noi_part2 part2);

    @Update
    void updateNoiPart2(noi_part2 part2);


}
