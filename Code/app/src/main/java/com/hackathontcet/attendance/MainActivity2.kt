package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mAuth = FirebaseAuth.getInstance()

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signup = findViewById(R.id.signup)

        signup.setOnClickListener {
            val username = username.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()

            signUp(username,email,password);
        }
    }
    private fun signUp(username: String,email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@MainActivity2, HomeScreenMainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity2, "SignUp Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity2, "Error Occurred Try Again", Toast.LENGTH_SHORT).show()
                }
            }

    }
}