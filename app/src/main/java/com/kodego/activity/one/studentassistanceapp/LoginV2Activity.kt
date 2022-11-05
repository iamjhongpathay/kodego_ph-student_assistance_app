package com.kodego.activity.one.studentassistanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.kodego.activity.one.studentassistanceapp.databinding.ActivityHomeBinding
import com.kodego.activity.one.studentassistanceapp.databinding.ActivityLoginV2Binding

class LoginV2Activity : AppCompatActivity() {
    lateinit var binding: ActivityLoginV2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginV2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener(){
            var userName: String = binding.etUsername.text.toString()
            var password: String = binding.etPassword.text.toString()

            checkCredentials(userName, password)
        }
    }

    private fun checkCredentials(userName: String, password: String): Boolean{
        val studentUserName1: String = "student_juan"
        val studentPassword1: String = "juan123"


        if((studentUserName1 == userName) && (studentPassword1 == password)){
            Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, HomeActivity::class.java)

            var name: String = "Juan"
            var lname: String = "Masipag"
            var id: String = "1000141116"
            var course: String = "Mobile App Development,"
            var section: String = "MD1P"
            var avatarPath: Int = R.drawable.sample_two



            intent.putExtra("nameID", name)
            intent.putExtra("lastname", lname)
            intent.putExtra("id", "ID: $id")
            intent.putExtra("courseID", course)
            intent.putExtra("sectionID", "$section")
            intent.putExtra("avatarPathID", avatarPath)

            startActivity(intent)
            finish()

            return true

        }else if(userName.isEmpty() || password.isEmpty()){
            Toast.makeText(applicationContext, "Empty Field!", Toast.LENGTH_LONG).show()

            return false
        }else{
            Toast.makeText(applicationContext, "Invalid Credentials!", Toast.LENGTH_LONG).show()

            return false
        }
    }
}