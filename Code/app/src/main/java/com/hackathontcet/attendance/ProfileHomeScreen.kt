package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ProfileHomeScreen : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_home_screen)

        val button: Button = findViewById(R.id.profile_button)

        button.setOnClickListener {
            startActivity(Intent(this@ProfileHomeScreen, HomeScreenMainActivity::class.java))
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }

//        loadProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile(){
        val name = findViewById<TextView>(R.id.username)
        val email = findViewById<TextView>(R.id.email)
        val currentName = findViewById<TextView>(R.id.profile_name_text)
        val currentEmail = findViewById<TextView>(R.id.profile_email_text)

        currentName.text = "Username: "+name.text
        currentEmail.text = "Username: "+email.text

    }
}