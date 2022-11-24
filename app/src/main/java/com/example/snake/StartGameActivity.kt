package com.example.snake

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.gamelogic.Direction
import com.example.snake.view.GameView


class StartGameActivity : AppCompatActivity() {
    lateinit var gameView : GameView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val button: Button = findViewById(R.id.startGameButton)
        val context = this
        button.setOnClickListener {
            gameView = GameView(context, null, context)
            setContentView(gameView)
            //TODO törölni
            //Thread.sleep(1000)
            //gameOver(10)

        }
    }
    fun gameOver(point: Int){
        val intel  = Intent(this, MainActivity::class.java)
        intel.putExtra("POINT", point)
        startActivity(intel)
    }
    override fun onBackPressed() {
    }
}