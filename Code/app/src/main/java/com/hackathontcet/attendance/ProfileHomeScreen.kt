package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_home_screen)

        val button: Button = findViewById(R.id.profile_button)

        button.setOnClickListener {
            startActivity(Intent(this@ProfileHomeScreen, HomeScreenMainActivity::class.java))
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }

        loadProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile(){
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            val uid = user.uid
            val currentName = findViewById<TextView>(R.id.profile_name_text)
            val currentEmail = findViewById<TextView>(R.id.profile_email_text)
            currentName.text = "UserID: \n"+uid.toString()
            currentEmail.text = "Email: "+email.toString()
        }
    }
}