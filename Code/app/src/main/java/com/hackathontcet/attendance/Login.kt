package com.hackathontcet.attendance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var forgot: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        forgot = findViewById(R.id.forgot)

        forgot.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()
            if(username.isEmpty() or password.isEmpty()) {
                if (username.isEmpty()) {
                    Toast.makeText(this@Login, "Please enter email address.", Toast.LENGTH_SHORT)
                        .show()
                }
                if (password.isEmpty()) {
                    Toast.makeText(this@Login, "Please enter password.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else{
                login(username,password)
            }
        }

    }
    private fun login(username: String, password: String){
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, HomeScreenMainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@Login, "User dose not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}