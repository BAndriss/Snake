package com.example.snake.Activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import com.example.snake.gamelogic.Control
import com.example.snake.R
import com.example.snake.gamelogic.SettingSaveFile


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val context =  this
        val languageSpinner: AppCompatSpinner = findViewById(R.id.spLanguagePicker)
        var control = SettingSaveFile.loadControl(context)
        languageSpinner.setSelection(getPos(control))
        languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                    println("Selected locale =" + p0.getItemAtPosition(p2)+" "+p2)
                    if(p2==0)
                        control = Control.SLIDING
                    else if(p2==1)
                        control = Control.CLICK
                    SettingSaveFile.saveControl(context, control)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }


    }
    private fun getPos(control: Control): Int{
        if(control == Control.SLIDING)
            return 0
        if(control == Control.CLICK)
            return 1
        return 0
    }

}