package com.hackathontcet.attendance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email)
        submit = findViewById(R.id.submit)

        submit.setOnClickListener {
            val email: String = email.text.toString().trim {it <= ' '}
            if (email.isEmpty()){
                Toast.makeText(this@ForgotPassword, "Please enter email address.", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this@ForgotPassword, "Email sent successfully to reset your password!", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this@ForgotPassword, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}