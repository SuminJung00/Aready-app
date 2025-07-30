package com.example.aready

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""CREATE TABLE lecture (
                lectureTitle text,
                dayOfWeek text,
                startHour INTEGER,
                startMinute INTEGER,
                reviewHour INTEGER,
                reviewMinute INTEGER,
                unresolvedHour INTEGER,
                unresolvedMinute INTEGER,
                reviewContent TEXT
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS lecture")
//        onCreate(db)
    }
}