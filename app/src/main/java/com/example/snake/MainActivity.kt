package com.example.snake

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.view.GameView


class MainActivity : AppCompatActivity() {
    lateinit var gameView : GameView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton: Button = findViewById(R.id.startGameButton)
        val context = this
        startButton.setOnClickListener {
            gameView = GameView(context, null, context)
            setContentView(gameView)
            //TODO törölni
            //Thread.sleep(1000)
            //gameOver(10)

        }
        val leaderboardButton: Button = findViewById(R.id.leaderboardButton)
        leaderboardButton.setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }

    }
    fun gameOver(point: Int){
        val intel  = Intent(this, SaveActivity::class.java)
        intel.putExtra("POINT", point)
        startActivity(intel)
    }
    override fun onBackPressed() {
    }
}