package com.hackathontcet.attendance.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathontcet.attendance.AboutAdapter
import com.hackathontcet.attendance.R
import com.hackathontcet.attendance.Subjects

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


        //CardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

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
            R.drawable.ic_profile_user,
            R.drawable.ic_profile_user,
            R.drawable.ic_profile_user,
            R.drawable.ic_profile_user,
            R.drawable.ic_profile_user
        )

        name = arrayOf(
            "Aman Tiwari","Deexith Madas","Ganesh Utla","Kunal Agrawal","Vaibhav Ashta"
        )

        for (i in imageId.indices){
            names.add(Subjects(imageId[i],name[i]))
        }

    }
    companion object;

    override fun onItemClick(subjects: String) {
        when (subjects) {
            "Kunal Agrawal" -> {
                startActivity( Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/kunalagra")))
            }
            "Aman Tiwari" -> {
                startActivity( Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/SultanIndian007")))
            }
            "Deexith Madas" -> {
                startActivity( Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/ythoncode")))
            }
            "Ganesh Utla" -> {
                startActivity( Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/ganesh-utla")))
            }
            "Vaibhav Ashta" -> {
                startActivity( Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/VaibhavAshta")))
            }
        }
    }
}