package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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
    private lateinit var listView: ListView
    var name =""
    var absent =""
    private var database = FirebaseDatabase.getInstance("https://attendance-c5215-default-rtdb.asia-southeast1.firebasedatabase.app")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)
        val intent = intent
        val subject = intent.getStringExtra("key1")

        var ref= database.getReference("$subject")
        ref.child("01012022").get().addOnSuccessListener {
            absent = it.value as String
        }
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
            click.text = "Selected date = ${savedday}-${savedmonth}-${savedyear}"
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
        if (savedmonth<10){
            nmonth= savedmonth.toString()
                nmonth = "0$nmonth"
         }
        if (savedday<10){
            nday= savedday.toString()
            nday = "0$nday"
        }

        val sel_date : Button = findViewById(R.id.selected_date)
        sel_date.text = "${nday}-${nmonth}-${savedyear}"
    }
}

