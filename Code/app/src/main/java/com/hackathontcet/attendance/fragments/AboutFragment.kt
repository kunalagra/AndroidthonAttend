package com.hackathontcet.attendance.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathontcet.attendance.AboutAdapter
import com.hackathontcet.attendance.R
import com.hackathontcet.attendance.RecyclerAdapter
import com.hackathontcet.attendance.Subjects
import kotlin.collections.ArrayList

class AboutFragment : Fragment(),AboutAdapter.ClickListener {
    private lateinit var adapter : AboutAdapter

    val names : ArrayList<Subjects> = ArrayList()

    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_about, container, false)

        getUserData()
        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View){
        /* This function will initialize the Recycler View */

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv2_view)
        recyclerView.clearAnimation()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = AboutAdapter(names,this@AboutFragment)
        recyclerView.adapter = adapter


    }
    private fun getUserData(){
        /* It will Give the Data (i.e. Image and Name) for the Subjects*/
        names.clear()
        imageId = arrayOf(
            R.drawable.ic_profile,
            R.drawable.ic_profile,
            R.drawable.ic_profile,
            R.drawable.ic_profile,
            R.drawable.ic_profile
        )

        name = arrayOf(
            "Aman Tiwari","Deexith Madas","Ganesh Utla","Kunal Agrawal","Vaibhav Ashta"
        )

        for (i in imageId.indices){
            names.add(Subjects(imageId[i],name[i]))
        }

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(names: String) {
        Log.i("name","$names")
    }
}