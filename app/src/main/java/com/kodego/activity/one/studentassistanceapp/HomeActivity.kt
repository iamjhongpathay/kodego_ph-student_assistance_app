package com.kodego.activity.one.studentassistanceapp

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.activity.one.studentassistanceapp.databinding.ActivityHomeBinding
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calendar: Calendar = Calendar.getInstance()
        var currentDate: String = DateFormat.getDateInstance().format(calendar.time)
        binding.txtViewDate.text = currentDate

        var name: String? = intent.getStringExtra("nameID")
        var lname: String? = intent.getStringExtra("lastname")
        var id: String? = intent.getStringExtra("id")
        var course: String? = intent.getStringExtra("courseID")
        var section: String? = intent.getStringExtra("sectionID")
        var avatarPath: Int = intent.getIntExtra("avatarPathID", R.drawable.avatar_place_holder)

        binding.txtViewName.text = name
        binding.txtViewLastname.text = lname
        binding.txtViewID.text = id
        binding.txtViewCourse.text = course
        binding.txtViewSection.text = section
        binding.avatarImage.setImageResource(avatarPath)

        var subjectList: MutableList<Subjects> = mutableListOf<Subjects>(
            Subjects("8:00", "AM", "9:30", "AM", "English", "Room 202", "Mon, Wed, Fri", "Irene Bondoc", R.drawable.img_instructor_female1) ,
            Subjects("9:30", "AM", "11:00", "AM", "Computer Laboratory", "Room ComLab 104", "Mon,Tue", "Buddy Mahinay", R.drawable.img_instructor_male2) ,
            Subjects("1:00", "PM", "2:30", "PM", "Introduction to Arts", "Room 304", "Tue, Thurs", "Evelyn Madrigal", R.drawable.img_instructor_female2),
            Subjects("2:30", "PM", "4:00", "PM", "History of J.P. Rizal", "Room 301", "Mon, Wed, Fri", "Luna Lovegood", R.drawable.img_instructor_female3),
            Subjects("4:00", "PM", "5:30", "PM", "English", "Room 202", "Mon, Wed, Fri", "Julius Lalim", R.drawable.img_instructor_male1) ,
        )

        val adapter = SubjectAdapter(subjectList)
        binding.recyclerSubjects.adapter = adapter
        binding.recyclerSubjects.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = {
            val intent = Intent(this, SubjectDetailsActivity::class.java)
            intent.putExtra("imageInstructor", it.imageInstructor)
            intent.putExtra("subjectInstructor", it.subjectInstructor)
            intent.putExtra("subjectName", it.subjectName)
            intent.putExtra("subjectRoom", it.subjectRoom)
            intent.putExtra("subjectDaySchedule", it.subjectDaySchedule)
            intent.putExtra("subjectTime", "${it.startTime}${it.startMeridiem} - ${it.endTime}${it.endMeridiem}")

            startActivity(intent)
        }
    }
}