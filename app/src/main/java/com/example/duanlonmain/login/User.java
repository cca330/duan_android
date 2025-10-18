package com.example.duanlonmain.login;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password; // lưu hashed password

    @ColumnInfo(name = "sdt")
    public String sdt;

    @ColumnInfo(name = "email")
    public String email; // có thể null

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "role")
    public String role; // "user" mặc định, có thể "admin"

    @ColumnInfo(name = "avatar")
    public String avatar; // uri hoặc url, có thể null

    // Default constructor required by Room
    public User() {}

    // Constructor tiện dụng cho đăng ký cơ bản
    public User(String username, String passwordHashed, String sdt) {
        this.username = username;
        this.password = passwordHashed;
        this.sdt = sdt;
        this.email = null;
        this.createdAt = System.currentTimeMillis();
        this.role = "user";
        this.avatar = null;
    }

    // Optional: constructor đầy đủ
    public User(String username, String passwordHashed, String sdt, String email, String role, String avatar) {
        this.username = username;
        this.password = passwordHashed;
        this.sdt = sdt;
        this.email = email;
        this.createdAt = System.currentTimeMillis();
        this.role = role == null ? "user" : role;
        this.avatar = avatar;
    }
}
