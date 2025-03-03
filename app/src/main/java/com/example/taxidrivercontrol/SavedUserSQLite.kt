package com.example.taxidrivercontrol

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SavedUserSQLite(mContext:Context):SQLiteOpenHelper(mContext,"SavedUser",null,1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE SavedUser(userName TEXT, mail TEXT, password TEXT)")
        db.execSQL("INSERT INTO SavedUser(userName,mail,password) VALUES('null','null','null')")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS SavedUser")
    }
}