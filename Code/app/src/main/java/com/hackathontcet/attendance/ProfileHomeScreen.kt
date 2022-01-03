package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hackathontcet.attendance.fragments.AboutFragment
import com.hackathontcet.attendance.fragments.HomeFragment
import com.hackathontcet.attendance.fragments.ProfileFragment

class ProfileHomeScreen : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val aboutFragment = AboutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_home_screen)
        mAuth = FirebaseAuth.getInstance()

        loadProfile()

        val button: Button = findViewById(R.id.profile_button)

        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            mAuth.signOut()
            finish()
            Toast.makeText(this, "You were logged out", Toast.LENGTH_SHORT).show()
        }

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, HomeScreenMainActivity::class.java))

                }
                R.id.about -> {
                    startActivity(Intent(this, AboutHomeScreen::class.java))
                }
            }
            finish()
            true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            val uid = user.uid
            val currentName = findViewById<TextView>(R.id.profile_name_text)
            val currentEmail = findViewById<TextView>(R.id.profile_email_text)
            currentName.text = "UserID: \n" + uid
            currentEmail.text = "Email: " + email.toString()
        }
    }
}