package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ProfileHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_home_screen)

        val button: Button = findViewById(R.id.profile_button)

        button.setOnClickListener {
            startActivity(Intent(this@ProfileHomeScreen, HomeScreenMainActivity::class.java))
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }
    }
}