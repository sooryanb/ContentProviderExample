package com.example.contentproviderexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context?): SQLiteOpenHelper(context, "AC_DB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE AC_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO AC_TABLE(NAME, MEANING) VALUES('IT', 'Information Technology')")
        db?.execSQL("INSERT INTO AC_TABLE(NAME, MEANING) VALUES('BCA', 'Bachelor Computer Science')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}