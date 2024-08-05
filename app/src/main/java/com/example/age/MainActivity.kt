package com.example.age

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ageText = findViewById<TextView>(R.id.ageText)
        val button = findViewById<Button>(R.id.getAgeBtn)
        button.setOnClickListener {
            val datePicker  = findViewById<DatePicker>(R.id.datePicker)
            val birthYear = datePicker.year
            val birthMonth = datePicker.month + 1
            val birthDay = datePicker.dayOfMonth

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            if  (year < birthYear || (year == birthYear && month < birthMonth) || (year == birthYear && month == birthMonth && day < birthDay)){
                Log.d("msg", "msg")
                ageText.text = ""
                Toast.makeText(this, "Invalid Birthdate, Try again", Toast.LENGTH_LONG).show()
            }else{
                var ageYear = year - birthYear
                var ageMonth = 0
                var ageDay = 0

                if (month < birthMonth){
                    ageYear -= 1
                    ageMonth = month - birthMonth + 12
                }else{
                    ageMonth = month - birthMonth
                }

                if (day < birthDay){
                    if  (ageMonth == 0){
                        ageYear -= 1
                        ageMonth = 11
                    }else{
                        ageMonth -= 1
                    }
                    if (month == 1 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11){
                        ageDay = day - birthDay + 31
                    }else{
                        if (month == 3){
                            ageDay = day - birthDay + 29
                        }else{
                            ageDay = day - birthDay + 30
                        }
                    }
                }else{
                    ageDay = day - birthDay
                }

                ageText.text = "$ageYear years, $ageMonth months, $ageDay days"
            }

        }
    }
}