package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

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
  //  private lateinit var adapter: MyAdapter
    //private lateinit var mAuth: FirebaseAuth
    //private lateinit var data: DatabaseReference
    lateinit var studentRoll : Array<Int>
    lateinit var studentName : Array<String>
    var absentS = arrayOf("")
    var absent = arrayOf(0)
    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)
        getStudent()

        userlayout = findViewById(R.id.userlayout)
        userlayout.layoutManager = LinearLayoutManager(this)
        userlayout.setHasFixedSize(true)
        ArrayList = arrayListOf<Name>()
        pickDate()
        val click : TextView = findViewById(R.id.result_date_time)
        click.setOnClickListener {
            if (::studentName.isInitialized) {
                click.text = "Refresh Data"
                var temp = ""
                if (savedyear==0){
                    Toast.makeText(this, "Please Select a Date", Toast.LENGTH_SHORT).show()
                }else{
                when {
                    absent[0] == -1 -> {
                        for (i in studentRoll) {
                            temp = temp.plus(",No Data")
                        }
                        temp = temp.drop(1)
                        absentS = temp.split(",").toTypedArray()

                    }
                    absent[0] == 0 -> {
                        for (i in studentRoll) {
                            temp = temp.plus(",Present")
                        }
                        temp = temp.drop(1)
                        absentS = temp.split(",").toTypedArray()
                    }
                    else -> {
                        for (i in studentRoll) {
                            temp = temp.plus(",Present")
                        }
                        temp = temp.drop(1)
                        val temab = temp.split(",").toTypedArray()
                        for (i in absent.indices) {
                            val ab = absent[i]
                            if (ab == studentRoll[ab - 1]) {
                                temab[ab - 1] = "Absent"
                            }
                        }
                        absentS = temab

                    }
                }
                getUserdata()
            }}else{
                Thread.sleep(200)
            }
        }

    }
    private fun getStudent(){
        val thread = Thread {
            val ref = database.reference
            ref.child("StuTab/").get().addOnSuccessListener {
                if (it.exists()) {
                    var namez = "${it.value}"
                    namez = namez.drop(1)
                    namez = namez.replace("]", "")
                    namez = namez.replace("null, ", "")
                    studentName = namez.split(", ").toTypedArray()
                    var tempnum = ""
                    var aa = 1
                    for (i in studentName) {
                        val b = aa.toString()
                        tempnum = tempnum.plus(",").plus(b)
                        aa += 1
                    }
                    tempnum = tempnum.drop(1)
                    val tRid = tempnum.split(",").toTypedArray()
                    studentRoll = tRid.map { it.toInt() }.toTypedArray()

                }
            }
        }
        thread.start()



    }
    private fun getUserdata(){
        ArrayList.clear()
        for(i in studentName.indices){
            val namel = Name(studentRoll[i],studentName[i],absentS[i])
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
        var ab: String
        val seldate: Button = findViewById(R.id.selected_date)
        seldate.text = "${nday}-${nmonth}-${savedyear}"
        val ref = database.getReference("$subject/")
        ref.child("$nday$nmonth$savedyear").get().addOnSuccessListener {
            ab = "${it.value}"
            absent = if (ab != "null") {
                val tabsent = ab.split(",").toTypedArray()
                tabsent.map { it.toInt() }.toTypedArray()
            } else{
                arrayOf(-1)
            }
        }
    }
}

