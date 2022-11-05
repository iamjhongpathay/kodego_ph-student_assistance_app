package com.kodego.activity.one.studentassistanceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.activity.one.studentassistanceapp.databinding.ActivitySubjectDetailsBinding

class SubjectDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySubjectDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var imageInstructor: Int = intent.getIntExtra("imageInstructor", R.drawable.avatar_place_holder)
        var subjectInstructor: String? = intent.getStringExtra("subjectInstructor")
        var subjectName: String? = intent.getStringExtra("subjectName")
        var subjectRoom: String? = intent.getStringExtra("subjectRoom")
        var subjectDaySchedule: String? = intent.getStringExtra("subjectDaySchedule")
        var subjectTime: String? = intent.getStringExtra("subjectTime")

        binding.avatarSubjDetailsImage.setImageResource(imageInstructor)
        binding.txtViewSubjDetailsInstructorName.text = subjectInstructor
        binding.txtViewDetailsSubject.text = subjectName
        binding.txtViewSubjDetailsRoom.text = subjectRoom
        binding.txtViewSubjDetailsDaySchedule.text = subjectDaySchedule
        binding.txtViewSubjDetailsTimeClass.text = subjectTime
    }
}