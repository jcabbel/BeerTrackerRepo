package com.example.beertracker.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "app.db";
    public static final String TABLE_CERVEZAS = "t_cervezas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CERVEZAS + "(" +
                "id INTEGER primary key AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "pais TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "marca TEXT NOT NULL," +
                "precio DOUBLE," +
                "graduacion DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CERVEZAS);
        onCreate(sqLiteDatabase);

    }
}
