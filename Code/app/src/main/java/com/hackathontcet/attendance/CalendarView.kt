package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
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

    private lateinit var userlayout: RecyclerView
    private lateinit var ArrayList: ArrayList<Name>
    private lateinit var adapter: MyAdapter
    //private lateinit var mAuth: FirebaseAuth
    //private lateinit var data: DatabaseReference
    var Sname = arrayOf("")
    var Rid = arrayOf("")
    var absentf = ""
    var absent = arrayOf("")
    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)
        val intent = intent
        val subject = intent.getStringExtra("key1")

        userlayout = findViewById(R.id.userlayout)
        userlayout.layoutManager = LinearLayoutManager(this)
        userlayout.setHasFixedSize(true)

        ArrayList = arrayListOf<Name>()
        getStudent()
        /*
        ArrayList = ArrayList()
        adapter = MyAdapter(ArrayList)

       // userlayout = findViewById(R.id.userlayout)

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

        */


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
        var ab=""
        click.setOnClickListener{
            click.text = "GetData"
            var ref= database.getReference("$subject/")
            ref.child("$nday$nmonth$savedyear").get().addOnSuccessListener {
                ab = "${it.value}"
                absent = ab.split(",").toTypedArray()
            }

        }



    }
    private fun getStudent(){
        var ref = database.getReference()
        ref.child("StuTab/").get().addOnSuccessListener{
            if(it.exists()){
                var namez = "${it.value}"
                namez = namez.drop(1)
                namez = namez.replace("]","")
                Sname = namez.split(", ").toTypedArray()
                var tempnum = "1"
                var aa=1
                for (i in Sname){
                    aa+=1
                    val b = aa.toString()
                    tempnum = tempnum.plus(",").plus(b)
                }
                Rid = tempnum.split(",").toTypedArray()
            }
    }
        for(i in Rid.indices){
            val name = Name(Rid[i],Sname[i],absent[i])
            ArrayList.add(name)
        }

        val adapter = MyAdapter(ArrayList)
        userlayout.adapter = adapter
    }

    private fun getDateCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH,)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        val seldate : Button = findViewById(R.id.selected_date)
        seldate.setOnClickListener{
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
        nmonth = "$savedmonth".padStart(2,'0')
        nday = "$savedday".padStart(2,'0')

        val seldate : Button = findViewById(R.id.selected_date)
        seldate.text = "${nday}-${nmonth}-${savedyear}"
    }
}

