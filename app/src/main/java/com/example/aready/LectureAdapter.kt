package com.example.aready

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LectureAdapter (
    private val lectureList: MutableList<LectureData>,
    private val onDeleteClick: (LectureData) -> Unit
) : RecyclerView.Adapter<LectureAdapter.LectureViewHolder>() {

        class LectureViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            val tvLectureName: TextView = itemView.findViewById<TextView>(R.id.tvLectureName)
            val tvLectureDayTime: TextView = itemView.findViewById<TextView>(R.id.tvLectureDayTime)
            val btnReviewNote : Button = itemView.findViewById<Button>(R.id.btnReviewNote)
            val btnDeleteReviewNote: Button = itemView.findViewById(R.id.btnDeleteReviewNote)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lecture, parent, false)
        return LectureViewHolder(view)
    }

    override fun getItemCount(): Int = lectureList.size

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture = lectureList[position]
        holder.tvLectureName.text = lecture.lectureTitle
        holder.tvLectureDayTime.text = "${lecture.dayOfWeek} %02d:%02d".format(lecture.startHour, lecture.startMin)

        holder.btnReviewNote.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ReviewNoteActivity::class.java)
            context.startActivity(intent)
        }

        holder.btnDeleteReviewNote.setOnClickListener {
            onDeleteClick(lecture)
        }
    }

    fun removeLecture(lecture: LectureData) {
        val position = lectureList.indexOf(lecture)
        if (position != -1) {
            lectureList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
