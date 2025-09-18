package com.example.duanlonmain.chat_bot;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao//Đánh dấu interface này là nơi khai báo các phương thức để làm việc với database
public interface MessageDao {
    @Insert
    void insert(MessageEntity message);//Tham số MessageEntity message → đối tượng cần lưu (mỗi đối tượng sẽ map với 1 dòng trong bảng).

    @Query("SELECT * FROM messages")
    List<MessageEntity> getAllMessages();
}
