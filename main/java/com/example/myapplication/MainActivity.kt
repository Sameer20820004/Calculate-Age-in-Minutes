package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    private var selecteddate:TextView?=null
    private var dateinmin:TextView?=null
    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // findviewby id helps us to find any text or button or any view or antyhing by its id
//        val btn =findViewById<Button>(R.id.clickmebtn) //Here we are assigning a button to a variable
           val btn :Button= findViewById(R.id.button)
           selecteddate=findViewById(R.id.textView4)
           dateinmin=findViewById(R.id.textView5)
           btn.setOnClickListener {
               datepicker()
        }
    }
    private fun datepicker(){
        val cal= Calendar.getInstance()
        val year=cal.get(Calendar.YEAR)
        val month=cal.get(Calendar.MONTH)
        val day=cal.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            {view,year,month,dayOfMonth->
                Toast.makeText(this, "Selected Year $year selected month ${month+1} selected day $dayOfMonth", Toast.LENGTH_SHORT).show()
            val dateselected="$dayOfMonth/${month+1}/$year"
                selecteddate?.text=dateselected // or we can use .setText
                val sdf= SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val thedate=sdf.parse(dateselected)
                val dinmin=thedate.time/600000; //dividing by thousand because time func returns the time in milliseconds
                val currentdate =(sdf.parse(sdf.format(System.currentTimeMillis()))).time/600000
                var diffinmin= currentdate-dinmin;
                if(diffinmin<0)
                    diffinmin=diffinmin*-1;
                dateinmin?.text= diffinmin.toString();

            }
            , year,month, day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000 // restricting the birth 86400000 is the milliseconds in one day
        dpd.show()

    }
}