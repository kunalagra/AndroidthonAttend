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
    lateinit var Rid : Array<Int>
    lateinit var Sname : Array<String>
    var absentS = arrayOf("")
    var absent = arrayOf(0)
    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)
        userlayout = findViewById(R.id.userlayout)
        userlayout.layoutManager = LinearLayoutManager(this)
        userlayout.setHasFixedSize(true)
        ArrayList = arrayListOf<Name>()

        val intent = intent
        Sname = intent.getSerializableExtra("key2") as Array<String>
        Rid = intent.getSerializableExtra("key3") as Array<Int>


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
        click.setOnClickListener {
            click.text = "Fetch Data"
            var temp = ""
            if(absent[0]==-1){
                for (i in Rid) {
                temp = temp.plus(",No Data")
                }
                temp = temp.drop(1)
                absentS = temp.split(",").toTypedArray()

            }
            else if(absent[0]==0){
                for (i in Rid) {
                    temp = temp.plus(",Present")
                }
                temp = temp.drop(1)
                absentS = temp.split(",").toTypedArray()
            }
            else {
            for (i in Rid) {
                temp = temp.plus(",Present")
            }
            temp = temp.drop(1)
            var temab = temp.split(",").toTypedArray()
            for (i in absent.indices) {
                var ab = absent[i]
                if (ab == Rid[ab-1]) {
                    temab[ab-1] = "Absent"
                }
            }
            absentS = temab

        }
            getUserdata()
        }


    }
    private fun getUserdata(){
        ArrayList.clear()
        for(i in Sname.indices){
            val namel = Name(Rid[i],Sname[i],absentS[i])
            Log.i("getUserdata","$namel")
            ArrayList.add(namel)
        }
        val adapter = MyAdapter(ArrayList)

        userlayout.adapter = adapter

    }

    private fun getDateCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
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
        savedmonth = p2 + 1
        savedyear = p1
        nmonth = savedmonth.toString()
        nday = savedday.toString()
        nmonth = "$savedmonth".padStart(2, '0')
        nday = "$savedday".padStart(2, '0')
        val intent = intent
        val subject = intent.getStringExtra("key1")
        var ab = ""
        val seldate: Button = findViewById(R.id.selected_date)
        seldate.text = "${nday}-${nmonth}-${savedyear}"
        var ref = database.getReference("$subject/")
        ref.child("$nday$nmonth$savedyear").get().addOnSuccessListener {
            ab = "${it.value}"
            if (ab != "null") {
                val tabsent = ab.split(",").toTypedArray()
                absent = tabsent.map { it.toInt() }.toTypedArray()
            }
            else{
                absent = arrayOf(-1)
            }
        }
    }
}

