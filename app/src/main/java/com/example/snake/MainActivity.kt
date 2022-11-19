package com.example.snake


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameView = GameView(this, null, this)
        gameView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(gameView)

    }
    fun gameOver(point: Int){
        println("ASSSSD:" + point)
    }
}