package com.example.aready  // ← 반드시 AndroidManifest의 패키지와 일치해야 함

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LectureListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_list)
    }
}
