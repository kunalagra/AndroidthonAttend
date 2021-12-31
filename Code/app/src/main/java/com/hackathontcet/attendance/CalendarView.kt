package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.*

class CalendarView : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var savedday = 0
    var savedmonth = 0
    var savedyear = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)

        pickDate()

        val click : TextView = findViewById(R.id.result_date_time)
        click.setOnClickListener{
            click.text = "Selected date = ${savedday}-${savedmonth}-${savedyear}"
        }
    }

    private fun getDateCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
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
        savedday = day
        savedmonth = month
        savedyear = year
        val sel_date : Button = findViewById(R.id.selected_date)
        sel_date.text = "${savedday}-${savedmonth}-${savedyear}"
    }
}