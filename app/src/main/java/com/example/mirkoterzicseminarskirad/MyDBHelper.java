package com.example.mirkoterzicseminarskirad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your table
        String createTable = "CREATE TABLE results (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, result INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades
    }
    public void addResult(String name, int result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("result", result);
        db.insert("results", null, values);
        db.close();
    }

    public Cursor getAllResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("results", null, null, null, null, null, "result DESC");
    }



}
