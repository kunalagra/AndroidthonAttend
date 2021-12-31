package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class AboutHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_home_screen)

        val button: Button = findViewById(R.id.about_button)

        button.setOnClickListener {
            startActivity(Intent(this@AboutHomeScreen, HomeScreenMainActivity::class.java))
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }
    }
}