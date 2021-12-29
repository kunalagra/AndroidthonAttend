package com.hackathontcet.attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenMainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Subjects>
    private lateinit var mAuth: FirebaseAuth

    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_home_screen)
        mAuth = FirebaseAuth.getInstance()

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout -> {
                    val intent = Intent(this@HomeScreenMainActivity,MainActivity::class.java)
                    startActivity(intent)
                    mAuth.signOut()
                    finish()
                    Toast.makeText(this,"You were logged out",Toast.LENGTH_SHORT).show()
                }
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

    private fun getUserdata(){
        for(i in imageId.indices){
            val subject = Subjects(imageId[i],name[i])
            newArrayList.add(subject)
        }

        val adapter = RecyclerAdapter(newArrayList)

        val swipegesture = object : SwipeGesture(this){

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val from_pos = viewHolder.adapterPosition
                val to_pos = target.adapterPosition

                Collections.swap(newArrayList,from_pos,to_pos)
                adapter.notifyItemMoved(from_pos,to_pos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int){
                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        val archiveItem = newArrayList[viewHolder.adapterPosition]
                        adapter.deleteItem(viewHolder.adapterPosition)
                        adapter.addItem(newArrayList.size,archiveItem)

                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(newRecyclerView)
        
        newRecyclerView.adapter = adapter

        adapter.setOnClickListener(object : RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@HomeScreenMainActivity, CalendarView::class.java))
            }
        })
    }
}