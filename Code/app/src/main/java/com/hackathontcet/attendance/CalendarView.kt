package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class CalendarView : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var nday=""
    var nmonth=""
    var day = 0
    var month = 0
    var year = 0
    var savedday = 0
    var savedmonth = 0
    var savedyear = 0
    private lateinit var listView: ListView
    private lateinit var userlayout: RecyclerView
    private lateinit var ArrayList: ArrayList<Name>
    private lateinit var adapter: MyAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var data: DatabaseReference
    var name =""
    var absent =""
    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)
        val intent = intent
        val subject = intent.getStringExtra("key1")
        mAuth = FirebaseAuth.getInstance()
        data = FirebaseDatabase.getInstance().getReference()

        ArrayList = ArrayList()
        adapter = MyAdapter(ArrayList)

        userlayout = findViewById(R.id.userlayout)

        userlayout.layoutManager = LinearLayoutManager(this)
        userlayout.adapter = adapter

        data.child("StuTab").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ArrayList.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(Name::class.java)
                    ArrayList.add(currentUser!!)
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })




        /*
        ref.child("1").get().addOnSuccessListener {
            if(it.exists()){
                name = it.key as String
            }
            else{
                Toast.makeText(this,"Not found",Toast.LENGTH_SHORT).show()
            }
        }*/
        pickDate()
        val click : TextView = findViewById(R.id.result_date_time)


        click.setOnClickListener{
            click.text = "Selected date = ${nday}-${nmonth}-${savedyear}"
            var ref= database.getReference("$subject")
            ref.child("$nday$nmonth$savedyear").get().addOnSuccessListener {
                absent = it.value as String
            }
            Toast.makeText(this, "$absent was found", Toast.LENGTH_SHORT)
                .show()
        }



    }

    private fun getDateCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH,)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        val sel_date : Button = findViewById(R.id.selected_date)
        sel_date.setOnClickListener{
            getDateCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedday = p3
        savedmonth = p2+1
        savedyear = p1
        nmonth= savedmonth.toString()
        nday= savedday.toString()
        if (savedmonth<10){
            nmonth = "0$nmonth"
        }
        if (savedday<10){
            nday = "0$nday"
        }

        val sel_date : Button = findViewById(R.id.selected_date)
        sel_date.text = "${nday}-${nmonth}-${savedyear}"
    }
}

