package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.util.*

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
            if (savedyear==0){
                Toast.makeText(this, "Please Select a Date", Toast.LENGTH_SHORT).show()
            }
            // Check if Data is first loaded first
            else if (::studentName.isInitialized) {
                click.text = "Refresh Data"
                var temp = ""
                // Get list of absent roll no.s and transform them into Array
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
                    // Generate Array of Present/Absent/No Data for absent array
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
            }else{
                Thread.sleep(200)
            }
        }

    }
    private fun getStudent(){
        // Thread to parallel load the data of students from Firebase's Realtime Database
        val thread = Thread {
            val ref = database.reference
            ref.child("StuTab/").get().addOnSuccessListener {
                if (it.exists()) {
                    var namez = "${it.value}"
                    // Cleaning u the data
                    namez = namez.drop(1)
                    namez = namez.replace("]", "")
                    namez = namez.replace("null, ", "")
                    // Transforming comma String to Array
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
                    // COnvert string array to int array
                    studentRoll = tRid.map { it.toInt() }.toTypedArray()

                }
            }
        }
        thread.start()



    }
    private fun getUserdata(){
        // Send data to Adapter (RecylerView) to and display it accordingly
        ArrayList.clear()
        for(i in studentName.indices){
            val namel = Name(studentRoll[i],studentName[i],absentS[i])
            ArrayList.add(namel)
        }
        val adapter = MyAdapter(ArrayList)

        userlayout.adapter = adapter

    }



    private fun pickDate() {
        // datePicker Code with maxDate as current Date
        val seldate : Button = findViewById(R.id.selected_date)
        seldate.setOnClickListener{
            val cal : Calendar = Calendar.getInstance()
            day = cal.get(Calendar.DAY_OF_MONTH)
            month = cal.get(Calendar.MONTH)
            year = cal.get(Calendar.YEAR)
            val dia = DatePickerDialog(this, this, year, month, day)
            dia.datePicker.maxDate = cal.timeInMillis
            dia.show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedday = p3
        savedmonth = p2 + 1
        savedyear = p1
        // to make to format of dd/MM/yyyy
        nmonth = savedmonth.toString()
        nday = savedday.toString()
        nmonth = "$savedmonth".padStart(2, '0')
        nday = "$savedday".padStart(2, '0')
        // Get the subject var from the Home intent and display the date
        val intent = intent
        val subject = intent.getStringExtra("key1")
        var ab: String
        val seldate: Button = findViewById(R.id.selected_date)
        seldate.text = "${nday}-${nmonth}-${savedyear}"
        // Get the list of Absent roll no from the Firebase Realtime DB and store in array format

        val ref = database.getReference("$subject/")
        ref.child("$nday$nmonth$savedyear").get().addOnSuccessListener {
            ab = "${it.value}"
            absent = if (ab != "null") {
                val tabsent = ab.split(",").toTypedArray()
                tabsent.map { it.toInt() }.toTypedArray()
            } else{
                // If no data (ie null is sent back
                arrayOf(-1)
            }
        }
    }
}

