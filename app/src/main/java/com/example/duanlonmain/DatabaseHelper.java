package com.example.duanlonmain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SQLite.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_PART1 = "noi_part1";
    public static final String TABLE_PART2 = "noi_part2";
    public static final String TABLE_PART3 = "noi_part3";
    public static final String TABLE_PART4 = "noi_part4";
    public static final String TABLE_PART5 = "noi_part5";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PART1 + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "paragram TEXT," +
                "completed BOOLEAN DEFAULT 0)");

        db.execSQL("CREATE TABLE " + TABLE_PART2 + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "picture INTEGER," +
                "completed BOOLEAN DEFAULT 0)");

        db.execSQL("CREATE TABLE " + TABLE_PART3 + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "context TEXT," +
                "q1 TEXT," +
                "q2 TEXT," +
                "q3 TEXT," +
                "completed BOOLEAN DEFAULT 0)");

        db.execSQL("CREATE TABLE " + TABLE_PART4 + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "context INTEGER," +
                "q1 TEXT," +
                "q2 TEXT," +
                "q3 TEXT," +
                "completed BOOLEAN DEFAULT 0)");

        db.execSQL("CREATE TABLE " + TABLE_PART5 + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT," +
                "completed BOOLEAN DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PART5);
        onCreate(db);
    }



    // -------- INSERT FUNCTIONS --------

    public void insertPart1(String paragram) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("paragram", paragram);
        db.insert(TABLE_PART1, null, values);
        db.close();
    }

    public void insertPart2(int drawableResId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("picture", drawableResId);
        db.insert(TABLE_PART2, null, values);
        db.close();
    }

    public void insertPart3(String context, String q1, String q2, String q3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("context", context);
        values.put("q1", q1);
        values.put("q2", q2);
        values.put("q3", q3);
        db.insert(TABLE_PART3, null, values);
        db.close();
    }

    public void insertPart4(int drawableResId, String q1, String q2, String q3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("context", drawableResId);
        values.put("q1", q1);
        values.put("q2", q2);
        values.put("q3", q3);
        db.insert(TABLE_PART4, null, values);
        db.close();
    }

    public void insertPart5(String question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question);
        db.insert(TABLE_PART5, null, values);
        db.close();
    }

    public void dropTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.close();
    }

    private byte[] readFileAsBytes(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }
}
