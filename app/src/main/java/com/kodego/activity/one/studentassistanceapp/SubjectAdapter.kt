package com.kodego.activity.one.studentassistanceapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.activity.one.studentassistanceapp.databinding.ActivityHomeBinding
import com.kodego.activity.one.studentassistanceapp.databinding.RowSubjectsBinding

class SubjectAdapter(val subjectList:MutableList<Subjects>): RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    var onItemClick: ((Subjects) -> Unit)? = null

    inner class SubjectViewHolder(val binding: RowSubjectsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowSubjectsBinding.inflate(layoutInflater, parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.apply {
            txtViewRowSubjStartTime.text = subjectList[position].startTime
            txtViewRowSubjStartMeridiem.text = subjectList[position].startMeridiem
            txtViewRowSubjEndTime.text = subjectList[position].endTime
            txtViewRowSubjEndMeridiem.text = subjectList[position].endMeridiem
            txtViewRowSubjSubject.text = subjectList[position].subjectName
            txtViewRowSubjRoomLink.text = subjectList[position].subjectRoom
            txtViewRowSubjDaySchedule.text = subjectList[position].subjectDaySchedule
            avatarRowSubjImage.setImageResource(subjectList[position].imageInstructor)
            txtViewRowSubjInstructor.text = subjectList[position].subjectInstructor
        }
        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(subjectList[position])
        }
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }
}