package com.kodego.activity.one.studentassistanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val adminUserName: String = "admin"
        val adminPassword: String = "admin123"

        if((adminUserName == userName) && (adminPassword == password)){
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Admin Logging in...", Toast.LENGTH_SHORT).show()
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