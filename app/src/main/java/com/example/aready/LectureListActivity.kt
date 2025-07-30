package com.example.aready  // ← 반드시 AndroidManifest의 패키지와 일치해야 함

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class LectureListActivity : AppCompatActivity() {

    lateinit var btnBack : ImageButton
    lateinit var btnAddLecture : Button

    lateinit var recyclerLectureList : RecyclerView
    lateinit var tvLectureName : TextView
    lateinit var tvLectureDayTime : TextView
    lateinit var btnReviewNote : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_list)

        btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnAddLecture = findViewById<Button>(R.id.btnAddLecture)

        recyclerLectureList = findViewById(R.id.recyclerLectureList)
        // tvLectureName = findViewById(R.id.tvLectureName)


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
