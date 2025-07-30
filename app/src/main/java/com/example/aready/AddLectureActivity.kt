package com.example.aready

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddLectureActivity : AppCompatActivity() {

    lateinit var dbManger : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var btnBack : ImageButton
    lateinit var etLectureName : EditText

    lateinit var toggleMon : ToggleButton
    lateinit var toggleTue : ToggleButton
    lateinit var toggleWed : ToggleButton
    lateinit var toggleThu : ToggleButton
    lateinit var toggleFri : ToggleButton

    lateinit var etStartHour : EditText
    lateinit var etStartMin : EditText
    lateinit var etReviewHour : EditText
    lateinit var etReviewMin : EditText
    lateinit var etUnresolvedHour : EditText
    lateinit var etUnresolvedMin : EditText

    lateinit var  btnRegisterLecture : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lecture)


        btnBack = findViewById(R.id.btnBack)
        etLectureName = findViewById(R.id.etLectureName)

        toggleMon = findViewById(R.id.toggleMon)
        toggleTue = findViewById(R.id.toggleTue)
        toggleWed = findViewById(R.id.toggleWed)
        toggleThu = findViewById(R.id.toggleThu)
        toggleFri = findViewById(R.id.toggleFri)

        etStartHour = findViewById(R.id.etStartHour)
        etStartMin = findViewById(R.id.etStartMin)
        etReviewHour = findViewById(R.id.etReviewHour)
        etReviewMin = findViewById(R.id.etReviewMin)
        etUnresolvedHour = findViewById(R.id.etUnresolvedHour)
        etUnresolvedMin = findViewById(R.id.etUnresolvedMin)

        btnRegisterLecture = findViewById(R.id.btnRegisterLecture)

        //DBManger 객체 받아오기
        dbManger = DBManager(this, "lectureDB", null, 1) // cursor값 null

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegisterLecture.setOnClickListener {
            val str_name : String = etLectureName.text.toString()
            val str_start_hour : String = etStartHour.text.toString()
            val str_start_min : String = etStartMin.text.toString()
            val str_review_hour : String = etReviewHour.text.toString()
            val str_review_min : String = etReviewMin.text.toString()
            val str_unresolved_hour : String = etUnresolvedHour.text.toString()
            val str_unresolved_min : String = etUnresolvedMin.text.toString()

            var str_day_of_week : String = ""
            str_day_of_week = when {
                toggleMon.isChecked -> "월"
                toggleTue.isChecked -> "화"
                toggleWed.isChecked -> "수"
                toggleThu.isChecked -> "목"
                toggleFri.isChecked -> "금"
                else -> ""
            }

            sqlitedb = dbManger.writableDatabase
            sqlitedb.execSQL("INSERT INTO lecture VALUES (" +
                    "'$str_name', " +
                    "'$str_day_of_week', " +
                    "$str_start_hour, " +
                    "$str_start_min, " +
                    "$str_review_hour, " +
                    "$str_review_min, " +
                    "$str_unresolved_hour, " +
                    "$str_unresolved_min, " +
                    "'')" // reviewContent는 현재 입력값이 없다고 가정
                 )
            sqlitedb.close()

            val intent = Intent(this, LectureListActivity::class.java)
            startActivity(intent)
            // 입력한 내용들 DB에 추가하는 부분 추가해야함
        }
    }
}