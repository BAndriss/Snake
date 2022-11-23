package com.example.snake

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.view.GameView


class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val button: Button = findViewById(R.id.startGameButton)

        val context = this

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val gameView = GameView(context, null, context)
                gameView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                setContentView(gameView)
            }
        })
    }
    fun gameOver(point: Int){
        println("ASSSSD:" + point)
        startActivity(Intent(this, MainActivity::class.java))
    }

}