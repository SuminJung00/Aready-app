package com.example.aready

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ReviewNoteActivity : AppCompatActivity() {

    private lateinit var unresolvedLayout: LinearLayout
    private lateinit var resolvedLayout: LinearLayout
    private lateinit var btnAddNewReview: Button
    private lateinit var tvLectureTitle: TextView

    private val unresolvedNotes = mutableListOf<String>()
    private val resolvedNotes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_note)

        unresolvedLayout = findViewById(R.id.unresolvedReviewContainer)
        resolvedLayout = findViewById(R.id.resolvedReviewContainer)
        btnAddNewReview = findViewById(R.id.btnAddNewReviewNote)
        tvLectureTitle = findViewById(R.id.tvLectureTitle)

        val lectureTitle = intent.getStringExtra("lectureTitle") ?: "강의 제목"
        tvLectureTitle.text = lectureTitle

        // 새 복습 노트 전달받은 경우 누적
        intent.getStringExtra("new_review_note")?.let { newNote ->
            unresolvedNotes.add(newNote)
        }

        // 초기 노트들 표시
        refreshNotes()

        btnAddNewReview.setOnClickListener {
            val intent = Intent(this, AddReviewNoteActivity::class.java)
            intent.putExtra("lectureTitle", lectureTitle)
            startActivity(intent)
        }
    }

    private fun refreshNotes() {
        unresolvedLayout.removeAllViews()
        resolvedLayout.removeAllViews()

        val inflater = LayoutInflater.from(this)

        for (note in unresolvedNotes) {
            val itemView = inflater.inflate(R.layout.review_note_item, null)
            val text = itemView.findViewById<TextView>(R.id.tvContent)
            val checkbox = itemView.findViewById<CheckBox>(R.id.checkBox)
            val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)

            text.text = note
            checkbox.isChecked = false

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    unresolvedNotes.remove(note)
                    resolvedNotes.add(note)
                    refreshNotes()
                }
            }

            btnDelete.setOnClickListener {
                unresolvedNotes.remove(note)
                refreshNotes()
            }

            unresolvedLayout.addView(itemView)
        }

        for (note in resolvedNotes) {
            val itemView = inflater.inflate(R.layout.review_note_item, null)
            val text = itemView.findViewById<TextView>(R.id.tvContent)
            val checkbox = itemView.findViewById<CheckBox>(R.id.checkBox)
            val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)

            text.text = note
            checkbox.isChecked = true

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    resolvedNotes.remove(note)
                    unresolvedNotes.add(note)
                    refreshNotes()
                }
            }

            btnDelete.setOnClickListener {
                resolvedNotes.remove(note)
                refreshNotes()
            }

            resolvedLayout.addView(itemView)
        }
    }
}
