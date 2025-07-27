package com.example.aready

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabLectureList = findViewById<FloatingActionButton>(R.id.fabLectureList)
        fabLectureList.setOnClickListener {
            val intent = Intent(this, LectureListActivity::class.java)
            startActivity(intent)
        }
    }
}
