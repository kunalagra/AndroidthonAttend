package com.hackathontcet.attendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hackathontcet.attendance.ui.login.fragments.HomeFragment
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenMainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Subjects>

    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_home_screen)

        val homeFragment = HomeFragment()

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout -> Toast.makeText(this,"You were logged out",Toast.LENGTH_SHORT).show()// makeCurrentFragment(homeFragment)
            }
            true
        }



        imageId = arrayOf(
            R.drawable.phy_icon,
            R.drawable.chem_icon,
            R.drawable.math_icon
        )

        name = arrayOf(
            "Physics",
            "Chemistry",
            "Mathematics"
        )

        newRecyclerView = findViewById(R.id.rv_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Subjects>()
        getUserdata()
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


    private fun getUserdata(){
        for(i in imageId.indices){
            val subject = Subjects(imageId[i],name[i])
            newArrayList.add(subject)
        }

        val adapter = RecyclerAdapter(newArrayList)
        newRecyclerView.adapter = adapter

        adapter.setOnClickListener(object : RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@HomeScreenMainActivity, "You Clicked on subject no. $position", Toast.LENGTH_SHORT).show()
            }
        })
    }
}