package com.hackathontcet.attendance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: DevDatabase

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        db = DevDatabase.getDatabase(this)
        var test = arrayOf("")
        var i =0
        GlobalScope.launch {
            db.devDao().insert(Devdetails(0, "Aman Tiwari"))
            db.devDao().insert(Devdetails(1, "Deexith Madas"))
            db.devDao().insert(Devdetails(2, "Ganesh Utla"))
            db.devDao().insert(Devdetails(3, "Kunal Agrawal"))
            db.devDao().insert(Devdetails(4, "Vaibhav Ashta"))

        }

        val login: Button = findViewById(R.id.loginb)
        val signup: Button = findViewById(R.id.signupb)
        signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            reload()
        }
    }
    private fun reload(){
        startActivity(Intent(this@MainActivity, HomeScreenMainActivity::class.java))
        finish()
    }
}
