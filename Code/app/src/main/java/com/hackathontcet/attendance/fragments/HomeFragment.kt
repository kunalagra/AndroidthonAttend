package com.hackathontcet.attendance.fragments

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathontcet.attendance.*
import java.util.*
import kotlin.collections.ArrayList

class maz: Application(){
    companion object {
        var globalVar = 0
    }
}
class HomeFragment : Fragment(), RecyclerAdapter.ClickListener {


    // Declaring the adapter variable for RecyclerAdapter
    private lateinit var adapter : RecyclerAdapter

    // Declaring the list called subjects for storing the items(subjects)
    val subjects : ArrayList<Subjects> = ArrayList()

    // Declaring this two variables for storing the image and the name for all the subjects
    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        maz.globalVar = 0
        getUserData()
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view : View){
        /* This function will initialize the Recycler View */
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_view)
        recyclerView.clearAnimation()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerAdapter(subjects,this@HomeFragment)
        recyclerView.adapter = adapter

        // This variable is used for doing the Left Swipe Gesture and the Drop Down Gesture
        val swipegesture = object : SwipeGesture(this@HomeFragment.requireContext()) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                /* It is used for doing Drop Down Gesture */

                val from_pos = viewHolder.adapterPosition
                val to_pos = target.adapterPosition

                Collections.swap(subjects, from_pos, to_pos)
                adapter.notifyItemMoved(from_pos, to_pos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /* It is used for doing Left Swipe Gesture */

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val archiveItem = subjects[viewHolder.adapterPosition]
                        adapter.deleteItem(viewHolder.adapterPosition)
                        adapter.addItem(subjects.size, archiveItem)

                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.adapter = adapter

    }

    private fun getUserData(){
        /* It will Give the Data (i.e. Image and Name) for the Subjects*/
        subjects.clear()
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

        for (i in imageId.indices){
            subjects.add(Subjects(imageId[i],name[i]))
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(subjects: String) {
        /* It will move to the Calendar Activity whenever User clicks any item(subject) */

        val intent = Intent(activity, CalendarView::class.java)
        intent.putExtra("key1", subjects)
        startActivity(intent)
    }
}