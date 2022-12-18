package com.kodego.activity.one.studentassistanceapp

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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

        var fullName: String? = intent.getStringExtra("fullNameID")
        var id: String? = intent.getStringExtra("id")
        var courseSection: String? = intent.getStringExtra("courseSectionID")
        var avatarPath: Int = intent.getIntExtra("avatarPathID", R.drawable.avatar_place_holder)
        var nickname: String? = intent.getStringExtra("nickNameID")

        binding.tvFullName.text = fullName
        binding.tvStudentID.text = id
        binding.tvCourseSection.text = courseSection
        binding.ivUsersAvatar.setImageResource(avatarPath)
        binding.tvNickName.text = "Hello $nickname!"

        var subjectList: MutableList<Subjects> = mutableListOf<Subjects>(
            Subjects("8:00", "AM", "9:30", "AM", "English", "Room 202", "Mon, Wed, Fri", "Irene Bondoc", R.drawable.img_instructor_female1) ,
            Subjects("9:30", "AM", "11:00", "AM", "Computer Laboratory", "Room ComLab 104", "Mon,Tue", "Buddy Mahinay", R.drawable.img_instructor_male2) ,
            Subjects("1:00", "PM", "2:30", "PM", "Introduction to Arts", "Room 304", "Tue, Thurs", "Evelyn Madrigal", R.drawable.img_instructor_female2),
            Subjects("2:30", "PM", "4:00", "PM", "History of J.P. Rizal", "Room 301", "Mon, Wed, Fri", "Luna Lovegood", R.drawable.img_instructor_female3),
            Subjects("4:00", "PM", "5:30", "PM", "Filipino", "Room 202", "Mon, Wed, Fri", "Julius Lalim", R.drawable.img_instructor_male1) ,
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

        binding.btnCopyIDIcon.setOnClickListener(){
            copyStudentID()
        }

        binding.btnChangeImageCameraIcon.setOnClickListener(){
            android.app.AlertDialog.Builder(this).setMessage("Choose a data source of image.")
                .setPositiveButton("Camera"){ dialog, item ->
                    showCamera()
                }
                .setNegativeButton("Gallery"){ dialog, item ->
                    showGallery()
                }.show()
        }
    }


    private fun copyStudentID() {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("ID", binding.tvStudentID.text)

        clipboard.setPrimaryClip(clip)

        Toast.makeText(applicationContext, "Student ID:${binding.tvStudentID.text} copied to Clipboard.", Toast.LENGTH_SHORT).show()
    }

    private fun showGallery() {
        Dexter.withContext(this).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object :PermissionListener{
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(galleryIntent)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext, "Gallery Permission Denied", Toast.LENGTH_SHORT).show()
                goToSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    private fun showCamera() {
        Dexter.withContext(this).withPermission(
            android.Manifest.permission.CAMERA
        ).withListener(object: PermissionListener{
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraLauncher.launch(cameraIntent)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                goToSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    private fun goToSettings() {
        AlertDialog.Builder(this).setMessage("It seems your permission has been denied. Go to Settings to enable permission.")
            .setPositiveButton("Go to Settings"){ dialog, item ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                var uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel"){dialog, item ->
                dialog.dismiss()
            }.show()
    }

    val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.let {
                val selectedImage = result.data?.data
                binding.ivUsersAvatar.setImageURI(selectedImage)
            }
        }
    }

    val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.let {
                val selectedImage : Bitmap  = result.data?.extras?.get("data") as Bitmap
                binding.ivUsersAvatar.setImageBitmap(selectedImage)
            }
        }
    }

}