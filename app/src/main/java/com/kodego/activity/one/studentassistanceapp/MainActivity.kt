package com.kodego.activity.one.studentassistanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kodego.activity.one.studentassistanceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //login button
//        binding.btnSignIn.setOnClickListener(){
//            var userName: String = binding.etUsername.text.toString()
//            var password: String = binding.etPassword.text.toString()
//            checkCredentials(userName, password)
//        }
    }

//    private fun checkCredentials(userName: String, password: String): Boolean{
//        val adminUserName: String = "admin"
//        val adminPassword: String = "admin123"
//
//        if((adminUserName == userName) && (adminPassword == password)){
//            var intent = Intent(this, HomeActivity::class.java) // transition from login(MainActivity) to HomeActivity
//            startActivity(intent)
//            finish() //the app (login-MainActivity) will terminate after the HomeActivity showed
//
//            Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_SHORT).show()
//            return true
//        }else{
//            Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_LONG).show()
//            return false
//        }
//    }
}