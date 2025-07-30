package com.example.aready

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddReviewNoteActivity : AppCompatActivity() {


    lateinit var btnSubmitReviewNote : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review_note)


        btnSubmitReviewNote = findViewById<Button>(R.id.btnSubmitReviewNote)


        btnSubmitReviewNote.setOnClickListener {
            val intent = Intent(this, ReviewNoteActivity::class.java)
            startActivity(intent)
        }

    }
}