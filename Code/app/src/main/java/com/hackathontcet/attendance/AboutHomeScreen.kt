package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_home_screen)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    startActivity(Intent(this,HomeScreenMainActivity::class.java))

                }
                R.id.profile -> {
                    startActivity(Intent(this,ProfileHomeScreen::class.java))
                }
            }
            finish()
            true
        }
    }
}