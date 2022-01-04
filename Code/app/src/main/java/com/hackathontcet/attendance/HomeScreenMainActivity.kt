package com.hackathontcet.attendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hackathontcet.attendance.fragments.AboutFragment
import com.hackathontcet.attendance.fragments.HomeFragment
import com.hackathontcet.attendance.fragments.ProfileFragment

class HomeScreenMainActivity : AppCompatActivity() {

    // Defining the variables for different fragments
    private val homeFragment: HomeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val aboutFragment = AboutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity_home_screen)

        // Defining variable for manipulating the buttons in the bottom navigation bar
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // Setting the Home Fragment as the current fragment
        setCurrentFragment(homeFragment)

        // Now we will display the different fragments according to the User's choice
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.profile -> setCurrentFragment(profileFragment)//startActivity(Intent(this, ProfileHomeScreen::class.java)
                R.id.about -> setCurrentFragment(aboutFragment)//startActivity(Intent(this, AboutHomeScreen::class.java)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        /* This function will set the Given Fragment as the Current Fragment */
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_wrapper, fragment)
        transaction.commit()
    }

}