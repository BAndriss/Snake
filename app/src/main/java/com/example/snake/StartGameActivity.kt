package com.example.snake

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.gamelogic.Direction
import com.example.snake.view.GameView


class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val button: Button = findViewById(R.id.startGameButton)
        val context = this
        button.setOnClickListener {
            val gameView = GameView(context, null, context)
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

}