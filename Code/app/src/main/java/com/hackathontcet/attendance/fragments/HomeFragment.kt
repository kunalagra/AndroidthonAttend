package com.hackathontcet.attendance.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathontcet.attendance.*
import java.text.FieldPosition

//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [HomeFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class HomeFragment : Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HomeFragment.
//         */
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}


class HomeFragment : Fragment(), RecyclerAdapter.ClickListener {
    lateinit var Rid : Array<Int>
    lateinit var Sname : Array<String>

    private lateinit var adapter : RecyclerAdapter
    val subjects : ArrayList<Subjects> = ArrayList()
    lateinit var imageId : Array<Int>
    lateinit var name : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view : View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_view)
        recyclerView.clearAnimation()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        getUserData()
        adapter = RecyclerAdapter(subjects,this)
        recyclerView.adapter = adapter

        adapter.setOnClickListener(object : RecyclerAdapter.ClickListener {
            override fun onItemClick(position: Int, subject: String) {
                val intent = Intent(activity, CalendarView::class.java)
                intent.putExtra("key1", subject)
                intent.putExtra("key2", Sname)
                intent.putExtra("key3", Rid)
                startActivity(intent)
            }
        })
    }

    private fun getUserData(){
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




    override fun onItemClick(position: Int, subject: String) {
//        val fragment : Fragment = DetailFragment.newInstance(subjects.subjectName!!)
//        val transaction = activity?.supportFragmentManager?.beginTransaction()
//        transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("main_fragment")!!)
//        transaction?.add(R.id.fl_wrapper,fragment)
//        transaction?.addToBackStack(null)
//        transaction?.commit()
//        if (::Sname.isInitialized) {
//            val intent = Intent(this@HomeFragment.requireContext(), CalendarView::class.java)
//            intent.putExtra("key1", subject)
//            intent.putExtra("key2", Sname)
//            intent.putExtra("key3", Rid)
//            startActivity(intent)
//
//        } else {
//            Toast.makeText(
//                this@HomeFragment.requireContext(),
//                "Fetching Data. Retry Again",
//                Toast.LENGTH_SHORT
//            ).show()
//            Thread.sleep(500)
//        }
        val intent = Intent(activity, CalendarView::class.java)
        intent.putExtra("key1", subject)
        intent.putExtra("key2", Sname)
        intent.putExtra("key3", Rid)
        startActivity(intent)
    }
}