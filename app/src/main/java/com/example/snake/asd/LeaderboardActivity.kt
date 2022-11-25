package com.example.snake.asd

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.R
import com.example.snake.scoredatamodel.SaveScoreData
import com.example.snake.scoredatamodel.ScoreModelData


class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        val allScore: ArrayList<ScoreModelData> = SaveScoreData.loadAllScore(this)
        val list = ArrayList<HashMap<String, String>>()
        for (score in allScore) {
            val map = HashMap<String, String>()
            println(score.name + " " + score.point)
            map["Name"] = score.name.toString()
            map["Score"] = score.point.toString()
            list.add(map)
        }
        list.sortWith(  compareBy({it["Score"]?.toInt()},{it["Name"]}) )
        //list.reversed()
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = SimpleAdapter(
            this,
            list.reversed(),
            R.layout.row_layout,
            arrayOf("Name", "Score"),
            intArrayOf(R.id.nameRowLayout, R.id.scoreRowLayout)
        )
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}