package com.example.snake


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.snake.view.GameView

class MainActivity :  AppCompatActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val gameView = GameView(this, null, this)
        gameView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        //setContentView(gameView)

        val context = this

        setContent{
            val navController = rememberNavController()

            Scaffold(
                /*topBar = {
                    TopAppBar(
                        title = {
                            Text("Snake")
                        }, navigationIcon = {

                        })
                }*/
            ){
                NavHost(navController = navController, startDestination = "gameView") {
                    composable("gameView"){
                        val gameView = GameView(context, null, context)
                        gameView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
                        context.setContentView(gameView)
                    }
                    composable("startView"){GameView(context, null, context)}
                }
            }
        }*/

        setContentView(R.layout.activity_main)
        /*val gameView = GameView(this, null)
        gameView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(gameView)*/
    }
    fun gameOver(point: Int){
        println("ASSSSD:" + point)
    }
}