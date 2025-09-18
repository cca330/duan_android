package com.example.duanlonmain.chat_bot;

import android.content.Context;//cần khi khởi tạo DB.

import androidx.room.Database;//annotation để Room biết class này là một Database.
import androidx.room.Room;//class hỗ trợ khởi tạo DB.
import androidx.room.RoomDatabase;//class cha của Room Database mà bạn phải kế thừa.

@Database(entities = {MessageEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {// abstract class này không thể tự tạo object trực tiếp bằng new.
    public abstract MessageDao messageDao();

    private static volatile AppDatabase INSTANCE;//Biến INSTANCE giữ singleton (chỉ có một DB duy nhất trong toàn bộ app).
    // volatile để đảm bảo nhiều thread cùng truy cập biến này vẫn nhìn thấy giá trị mới nhất.

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {//đảm bảo thread-safe
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),//tạo DB:
                                    AppDatabase.class, "chat_db")
                            .fallbackToDestructiveMigration() // Xóa DB cũ nếu thay đổi
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

