package com.example.aready

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var dateText : TextView
    lateinit var class1Title: TextView
    lateinit var class1Time: TextView
    lateinit var class2Title: TextView
    lateinit var class2Time: TextView

    lateinit var dbManager: DBManager
    lateinit var sqliteDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        class1Title = findViewById(R.id.class1Title)
        class1Time = findViewById(R.id.class1Time)
        class2Title = findViewById(R.id.class2Title)
        class2Time = findViewById(R.id.class2Time)

        // 오늘 날짜를 포맷해서 dateText에 표시
        dateText = findViewById<TextView>(R.id.dateText)
        val today = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy.MM.dd EEE", Locale.KOREA)
        val formattedDate = formatter.format(today)
        dateText.text = formattedDate

        // 요일 계산
        val dayFormatter = SimpleDateFormat("E", Locale.KOREA)
        val todayDayOfWeek = dayFormatter.format(today)

        dbManager = DBManager(this, "lectureDB", null, 1)
        sqliteDB = dbManager.readableDatabase
        val cursor = sqliteDB.rawQuery(
            """
        SELECT * FROM lecture
        WHERE dayOfWeek = ?
        ORDER BY startHour ASC, startMinute ASC
        LIMIT 2
        """.trimIndent(),
            arrayOf(todayDayOfWeek)
        )

        var index = 0
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow("lectureTitle"))
            val hour = cursor.getInt(cursor.getColumnIndexOrThrow("startHour"))
            val min = cursor.getInt(cursor.getColumnIndexOrThrow("startMinute"))

            val formattedTime = "%s %02d:%02d".format(todayDayOfWeek, hour, min)

            if (index == 0) {
                class1Title.text = title
                class1Time.text = formattedTime
            } else if (index == 1) {
                class2Title.text = title
                class2Time.text = formattedTime
            }
            index++
        }

        cursor.close()
        sqliteDB.close()

        val fabLectureList = findViewById<FloatingActionButton>(R.id.fabLectureList)
        fabLectureList.setOnClickListener {
            val intent = Intent(this, LectureListActivity::class.java)
            startActivity(intent)
        }
    }
}
