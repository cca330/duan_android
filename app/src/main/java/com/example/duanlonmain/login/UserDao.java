package com.example.duanlonmain.login;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT COUNT(*) FROM user WHERE username = :username")
    int checkUsernameExists(String username);

    @Query("SELECT COUNT(*) FROM user WHERE sdt = :sdt")
    int checkPhoneExists(String sdt);

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("SELECT * FROM user WHERE sdt = :sdt LIMIT 1")
    User getUserByPhone(String sdt);


    @Query("SELECT COUNT(*) FROM user WHERE username = :username AND sdt = :sdt")
    int checkUsernameAndPhone(String username, String sdt);

    @Query("UPDATE user SET password = :newPassword WHERE username = :username")
    int updatePassword(String username, String newPassword);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();
}
