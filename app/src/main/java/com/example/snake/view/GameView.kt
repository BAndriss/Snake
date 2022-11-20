package com.example.snake.view


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.snake.gamelogic.Direction
import com.example.snake.gamelogic.GameLogic
import com.example.snake.MainActivity

@SuppressLint("ViewConstructor")
class GameView(context: Context, attrs: AttributeSet?, private val mainActivity: MainActivity) : View(context, attrs) {
    private lateinit var canvas1 : Canvas
    private lateinit var bitmap1 : Bitmap
    private val backcolor = ResourcesCompat.getColor(resources, android.R.color.holo_green_dark,null)
    private val circleRadius = 30
    private lateinit var gameLogic : GameLogic
    private val paintSnakePart: Paint = Paint().apply{

        color = ResourcesCompat.getColor(resources, android.R.color.holo_orange_light,null)
        style = Paint.Style.FILL
        isAntiAlias = true

    }
    private val paintSnakeHead: Paint = Paint().apply{

        color = ResourcesCompat.getColor(resources, android.R.color.holo_red_dark,null)
        style = Paint.Style.FILL
        isAntiAlias = true

    }
    private val paintFood: Paint = Paint().apply{

        color = ResourcesCompat.getColor(resources, android.R.color.holo_purple,null)
        style = Paint.Style.FILL
        isAntiAlias = true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if(::bitmap1.isInitialized)
            bitmap1.recycle()
        bitmap1 = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas1 = Canvas(bitmap1)
        canvas1.drawColor(backcolor)
        gameLogic = GameLogic(width, height, circleRadius, this)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap1, 0f, 0f, paintFood)
        drawFood(canvas)
        drawSnake(canvas)
    }

    private fun drawSnake(canvas: Canvas?){
        for(snakePart in gameLogic.snake.snakePart){
            canvas?.drawCircle(snakePart.posX.toFloat() , snakePart.posY.toFloat(), circleRadius.toFloat(), paintSnakePart)
        }
        canvas?.drawCircle(gameLogic.snake.snakePart[0].posX.toFloat() , gameLogic.snake.snakePart[0].posY.toFloat(), circleRadius.toFloat(), paintSnakeHead)
    }
    private fun drawFood(canvas: Canvas?){
        canvas?.drawCircle(gameLogic.food.posX.toFloat() , gameLogic.food.posY.toFloat(), circleRadius.toFloat(), paintFood)
    }

    //KÉPERNYŐRE KATTINTOS DOLOG
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                if(event.y < height/3 && gameLogic.direction != Direction.DOWN)
                    gameLogic.direction = Direction.UP
                else if(event.y > 2*height/3 && gameLogic.direction != Direction.UP)
                    gameLogic.direction = Direction.DOWN
                else if(event.x < width/2 && gameLogic.direction != Direction.RIGHT)
                    gameLogic.direction = Direction.LEFT
                else if(event.x > width/2 && gameLogic.direction != Direction.LEFT)
                    gameLogic.direction = Direction.RIGHT
            }
        }
        return super.onTouchEvent(event)
    }
    fun gameOver(point: Int){
        mainActivity.gameOver(point)
    }


}