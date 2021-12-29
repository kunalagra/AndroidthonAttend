package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import java.time.Instant

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        val login: Button = findViewById(R.id.loginb)
        val signup: Button = findViewById(R.id.signupb)
        val homes: Button = findViewById(R.id.homesact)
        signup.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        homes.setOnClickListener {
            val intent = Intent(this, HomeScreenMainActivity::class.java)
            startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            reload();
        }
    }
    private fun reload(){
        startActivity(Intent(this@MainActivity, HomeScreenMainActivity::class.java))
    }
}
