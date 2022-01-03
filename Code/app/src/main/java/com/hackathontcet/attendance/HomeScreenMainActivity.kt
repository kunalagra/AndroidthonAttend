package com.hackathontcet.attendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent as Intent

class HomeScreenMainActivity : AppCompatActivity() {
    lateinit var Rid : Array<Int>
    lateinit var Sname : Array<String>

    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Subjects>
    private lateinit var mAuth: FirebaseAuth

    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_home_screen)
        getStudent()
        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    startActivity(Intent(this, ProfileHomeScreen::class.java))
                }
                R.id.about -> {
                    startActivity(Intent(this, AboutHomeScreen::class.java))
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
    private fun getStudent(){
        var ref = database.reference
        ref.child("StuTab/").get().addOnSuccessListener{
            if(it.exists()){
                var namez = "${it.value}"
                namez = namez.drop(1)
                namez = namez.replace("]","")
                namez = namez.replace("null, ","")
                Sname = namez.split(", ").toTypedArray()
                Log.i("firebase","$namez")
                var tempnum = ""
                var aa=1
                for (i in Sname){
                    val b = aa.toString()
                    tempnum = tempnum.plus(",").plus(b)
                    aa+=1
                }
                tempnum = tempnum.drop(1)
                Log.i("firebase", "$tempnum")
                val tRid = tempnum.split(",").toTypedArray()
                Rid = tRid.map { it.toInt() }.toTypedArray()

            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_container, fragment)
        transaction.commit()
    }

    private fun getUserdata() {
        for (i in imageId.indices) {
            val subject = Subjects(imageId[i], name[i])
            Log.i("getUserdata", "$subject")
            newArrayList.add(subject)
        }

        val adapter = RecyclerAdapter(newArrayList)

        val swipegesture = object : SwipeGesture(this) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from_pos = viewHolder.adapterPosition
                val to_pos = target.adapterPosition

                Collections.swap(newArrayList, from_pos, to_pos)
                adapter.notifyItemMoved(from_pos, to_pos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val archiveItem = newArrayList[viewHolder.adapterPosition]
                        adapter.deleteItem(viewHolder.adapterPosition)
                        adapter.addItem(newArrayList.size, archiveItem)

                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(newRecyclerView)

        newRecyclerView.adapter = adapter

        adapter.setOnClickListener(object : RecyclerAdapter.onItemClickListener {
            override fun onItemClick(position: Int, subject: String) {
                val intent = Intent(this@HomeScreenMainActivity, CalendarView::class.java)
                intent.putExtra("key1", subject)
                intent.putExtra("key2", Sname)
                intent.putExtra("key3", Rid)
                startActivity(intent)


            }
        })
    }
}