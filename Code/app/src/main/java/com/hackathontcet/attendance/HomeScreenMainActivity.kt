package com.hackathontcet.attendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hackathontcet.attendance.fragments.AboutFragment
import com.hackathontcet.attendance.fragments.HomeFragment
import com.hackathontcet.attendance.fragments.ProfileFragment
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent as Intent

class HomeScreenMainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Subjects>
    private lateinit var mAuth: FirebaseAuth

    val homeFragment = HomeFragment()
    val profileFragment = ProfileFragment()
    val aboutFragment = AboutFragment()

    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity_home_screen)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setCurrentFragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.profile -> setCurrentFragment(profileFragment)//startActivity(Intent(this, ProfileHomeScreen::class.java)
                R.id.about -> setCurrentFragment(aboutFragment)//startActivity(Intent(this, AboutHomeScreen::class.java)
            }
            true
        }

//        newRecyclerView = findViewById(R.id.rv_view)
//        newRecyclerView.layoutManager = LinearLayoutManager(this)
//        newRecyclerView.setHasFixedSize(true)
//
//        newArrayList = arrayListOf<Subjects>()
//        getUserdata()
    }



    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_wrapper, fragment)
        transaction.commit()
    }
//
//    private fun getUserdata() {
//        for (i in imageId.indices) {
//            val subject = Subjects(imageId[i], name[i])
//            Log.i("getUserdata", "$subject")
//            newArrayList.add(subject)
//        }
//
//        val adapter = RecyclerAdapter(newArrayList)
//
//        val swipegesture = object : SwipeGesture(this) {
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                val from_pos = viewHolder.adapterPosition
//                val to_pos = target.adapterPosition
//
//                Collections.swap(newArrayList, from_pos, to_pos)
//                adapter.notifyItemMoved(from_pos, to_pos)
//
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                when (direction) {
//                    ItemTouchHelper.LEFT -> {
//                        val archiveItem = newArrayList[viewHolder.adapterPosition]
//                        adapter.deleteItem(viewHolder.adapterPosition)
//                        adapter.addItem(newArrayList.size, archiveItem)
//
//                    }
//                }
//            }
//        }
//        val touchHelper = ItemTouchHelper(swipegesture)
//        touchHelper.attachToRecyclerView(newRecyclerView)
//
//        newRecyclerView.adapter = adapter
//
//        adapter.setOnClickListener(object : RecyclerAdapter.onItemClickListener {
//            override fun onItemClick(position: Int, subject: String) {
//                if (::studentName.isInitialized) {
//                    val intent = Intent(this@HomeScreenMainActivity, CalendarView::class.java)
//                    intent.putExtra("key1", subject)
//                    intent.putExtra("key2", studentName)
//                    intent.putExtra("key3", studentRoll)
//                    startActivity(intent)
//
//                } else {
//                    Toast.makeText(
//                        this@HomeScreenMainActivity,
//                        "Fetching Data. Retry Again",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Thread.sleep(500)
//                }
//
//
//            }
//        })
//    }

}