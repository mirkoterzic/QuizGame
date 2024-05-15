package com.example.mirkoterzicseminarskirad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "leaderboard.db";
    private static final int DATABASE_VERSION = 2;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your table
        String createTable = "CREATE TABLE leaderboard (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, result INTEGER,time INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Upgrade logic to add 'time' column
            db.execSQL("ALTER TABLE results ADD COLUMN time INTEGER DEFAULT 0");
        }
    }
    public void addResult(String name, int result,int time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("result", result);
        values.put("time",time);
        db.insert("leaderboard", null, values);
        db.close();
    }

    public Cursor getAllResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("leaderboard", null, null, null, null, null, "result DESC, time ASC");
    }



}
