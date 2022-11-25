package com.example.snake.Activity


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.R
import com.example.snake.scoredatamodel.SaveScoreData
import com.example.snake.scoredatamodel.ScoreModelData
import java.util.*


class SaveActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        val point = intent.getIntExtra("POINT", -1)
        val pointText = findViewById<View>(R.id.pointText) as TextView
        pointText.text = getString(R.string.point,point)
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
            SaveScoreData.saveLocationModel(
                this,
                ScoreModelData(UUID.randomUUID(), nameText.text.toString(), point)
            )
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }
    }
    @Deprecated("Deprecated in Java", ReplaceWith(
        "startActivity(Intent(this, MainActivity::class.java))",
        "android.content.Intent"
    )
    )
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}