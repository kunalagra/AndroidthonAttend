package com.hackathontcet.attendance.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hackathontcet.attendance.MainActivity
import com.hackathontcet.attendance.R

class ProfileFragment : Fragment() {
    // Creating a variable for the Firebase Authorization
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        mAuth = FirebaseAuth.getInstance()

        loadProfile(view)

        val button: CardView = view.findViewById(R.id.profile_button)

        button.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            mAuth.signOut()
            Toast.makeText(activity, "You were logged out", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object;

    @SuppressLint("SetTextI18n")
    private fun loadProfile(view: View) {
        /* It will give the Current user's ID (Firebase) as well as Email registered by him */

        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            var uid = user.uid
            // Drops first 8 character to make the ID shorter
            uid=uid.drop(8)
            val currentName = view.findViewById<TextView>(R.id.profile_name_text)
            val currentEmail = view.findViewById<TextView>(R.id.profile_email_text)
            currentName.text = "UserID : $uid"
            currentEmail.text = "Email  : " + email.toString()
        }
    }

}