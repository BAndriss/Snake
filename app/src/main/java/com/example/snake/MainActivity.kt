package com.example.snake


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val point = intent.getIntExtra("POINT", -1)
        val pointText = findViewById<View>(R.id.pointText) as TextView
        pointText.text = getString(R.string.point) +" "+point.toString()
        val saveButton: Button = findViewById(R.id.saveButton)
        val nameText = findViewById<View>(R.id.nameText) as TextView
        saveButton.setOnClickListener {
            println(nameText.text.length)
            save(nameText, point)
        }
        nameText.setOnClickListener {
            save(nameText, point)
        }
    }
    private fun save(nameText: TextView, point: Int){
        if(nameText.text.isEmpty() ) {
            nameText.setHintTextColor(Color.RED)
        } else{
            //TODO
            println("AAAAAAAAAAAAAAA")
        }
    }
}