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
        val intel  = Intent(this, SaveActivity::class.java)
        intel.putExtra("POINT", point)
        startActivity(intel)
    }
    override fun onBackPressed() {
    }
}