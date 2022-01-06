package com.hackathontcet.attendance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_signup)
        mAuth = FirebaseAuth.getInstance()

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signup = findViewById(R.id.signup)

        signup.setOnClickListener {
            val username = username.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            if(email.isEmpty() or username.isEmpty() or password.isEmpty()) {
                if (email.isEmpty()) {
                    Toast.makeText(
                        this@SignUp,
                        "Please enter email address.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (username.isEmpty()) {
                    Toast.makeText(
                        this@SignUp,
                        "Please enter name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (password.isEmpty()) {
                    Toast.makeText(
                        this@SignUp,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else{
                signUp(username,email,password)
            }

        }
    }
    private fun signUp(username: String,email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@SignUp, HomeScreenMainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@SignUp, "SignUp Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignUp, "Error Occurred Try Again", Toast.LENGTH_SHORT).show()
                }
            }

    }
}