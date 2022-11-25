package com.example.snake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.snake.scoredatamodel.SaveScoreData
import com.example.snake.scoredatamodel.ScoreModelData

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        val backButton: Button = findViewById(R.id.Backbutton)
        backButton.setOnClickListener {   setContentView(R.layout.activity_main) }
        val allScore : ArrayList<ScoreModelData>  = SaveScoreData.loadAllScore(this)
        for (score in allScore){
            println(score.name+" "+score.point)
        }
        //allScore.stream().sorted(ScoreModelData..reverseOrder()))
    }
}