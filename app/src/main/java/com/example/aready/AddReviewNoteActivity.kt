package com.example.aready

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddReviewNoteActivity : AppCompatActivity() {

    private lateinit var etReviewContent: EditText
    private lateinit var btnSubmitReviewNote: Button
    private lateinit var btnBack: ImageButton
    private lateinit var tvLectureTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review_note) // ★ 중요!

        // View 연결
        etReviewContent = findViewById(R.id.etReviewContent)
        btnSubmitReviewNote = findViewById(R.id.btnSubmitReviewNote)
        btnBack = findViewById(R.id.btnBack)
        tvLectureTitle = findViewById(R.id.tvLectureTitle)

        // 전달받은 강의 제목
        val lectureTitle = intent.getStringExtra("lectureTitle") ?: "강의 제목"
        tvLectureTitle.text = lectureTitle

        // 등록 버튼 클릭 시
        btnSubmitReviewNote.setOnClickListener {
            val content = etReviewContent.text.toString().trim()
            if (content.isNotEmpty()) {
                val intent = Intent(this, ReviewNoteActivity::class.java)
                intent.putExtra("lectureTitle", lectureTitle) // 다시 넘겨줘야 함
                intent.putExtra("new_review_note", content)   // 새 복습 노트 내용도 함께
                startActivity(intent)
                finish() // 현재 액티비티 종료
            } else {
                Toast.makeText(this, "내용을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }


        // 뒤로가기
        btnBack.setOnClickListener {
            finish()
        }
    }
}
