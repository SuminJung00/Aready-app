package com.example.aready  // ← 반드시 AndroidManifest의 패키지와 일치해야 함

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LectureListActivity : AppCompatActivity() {

    lateinit var btnBack : ImageButton
    lateinit var btnAddLecture : Button

    lateinit var recyclerLectureList : RecyclerView

    //
    lateinit var dbManager: DBManager
    lateinit var adapter: LectureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_list)

        btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnAddLecture = findViewById<Button>(R.id.btnAddLecture)

        recyclerLectureList = findViewById(R.id.recyclerLectureList)
        // tvLectureName = findViewById(R.id.tvLectureName)

        // RecyclerView 설정
        recyclerLectureList.layoutManager = LinearLayoutManager(this)

        // DB에서 데이터 가져오기
        dbManager = DBManager(this, "lectureDB", null, 1)
        val sqlitedb = dbManager.readableDatabase
        val cursor = sqlitedb.rawQuery("SELECT * FROM lecture", null)

        val lectureList = mutableListOf<LectureData>()

        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow("lectureTitle"))
            val day = cursor.getString(cursor.getColumnIndexOrThrow("dayOfWeek"))
            val hour = cursor.getInt(cursor.getColumnIndexOrThrow("startHour"))
            val min = cursor.getInt(cursor.getColumnIndexOrThrow("startMinute"))

            lectureList.add(LectureData(title, day, hour, min))
        }

        cursor.close()
        sqlitedb.close()

        adapter = LectureAdapter(lectureList) { lectureToDelete ->
            // 1. DB에서 삭제
            val db = dbManager.writableDatabase
            db.execSQL("DELETE FROM lecture WHERE lectureTitle = ? AND dayOfWeek = ? AND startHour = ? AND startMinute = ?",
                arrayOf(lectureToDelete.lectureTitle, lectureToDelete.dayOfWeek, lectureToDelete.startHour.toString(), lectureToDelete.startMin.toString()))
            db.close()

            // 2. RecyclerView에서 삭제
            adapter.removeLecture(lectureToDelete)
        }

        recyclerLectureList.adapter = adapter

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnAddLecture.setOnClickListener {
            val intent = Intent(this, AddLectureActivity::class.java)
            startActivity(intent)
        }
    }
}
