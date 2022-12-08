package com.example.snake.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.snake.activity.MainActivity
import com.example.snake.gamelogic.Control
import com.example.snake.gamelogic.Direction
import com.example.snake.gamelogic.GameLogic
import com.example.snake.save.SettingSaveFile

@SuppressLint("ViewConstructor")
class GameView(context: Context, attrs: AttributeSet?, private val mainActivity: MainActivity) :
    View(context, attrs) {
    private lateinit var canvas1: Canvas
    private lateinit var bitmap1: Bitmap
    private val circleRadius = 30
    private lateinit var gameLogic: GameLogic
    private var control: Control = SettingSaveFile.loadControl(context)
    private val paintSnakePart: Paint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val paintSnakeHead: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val paintFood: Paint = Paint().apply {
        color = Color.CYAN
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
    private val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::bitmap1.isInitialized)
            bitmap1.recycle()
        bitmap1 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas1 = Canvas(bitmap1)
        canvas1.drawColor(Color.GREEN)
        gameLogic = GameLogic(width, height, circleRadius, this)
        if (Control.ACCELEROMETER == control) {
            useAccelerometer()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap1, 0f, 0f, paintFood)
        drawFood(canvas)
        drawSnake(canvas)
    }

    private fun drawSnake(canvas: Canvas?) {
        for (snakePart in gameLogic.snake.snakePart) {
            canvas?.drawCircle(
                snakePart.posX.toFloat(),
                snakePart.posY.toFloat(),
                circleRadius.toFloat(),
                paintSnakePart
            )
        }
        canvas?.drawCircle(
            gameLogic.snake.snakePart[0].posX.toFloat(),
            gameLogic.snake.snakePart[0].posY.toFloat(),
            circleRadius.toFloat(),
            paintSnakeHead
        )
    }

    private fun drawFood(canvas: Canvas?) {
        canvas?.drawCircle(
            gameLogic.food.posX.toFloat(),
            gameLogic.food.posY.toFloat(),
            circleRadius.toFloat(),
            paintFood
        )
    }

    private var posX: Float = 0F
    private var posY: Float = 0F

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (control == Control.CLICK) {
                    if (event.y < height / 3 && gameLogic.direction != Direction.DOWN) gameLogic.direction =
                        Direction.UP
                    else if (event.y > 2 * height / 3 && gameLogic.direction != Direction.UP) gameLogic.direction =
                        Direction.DOWN
                    else if (event.x < width / 2 && gameLogic.direction != Direction.RIGHT) gameLogic.direction =
                        Direction.LEFT
                    else if (event.x > width / 2 && gameLogic.direction != Direction.LEFT) gameLogic.direction =
                        Direction.RIGHT
                } else if (control == Control.SLIDING) {
                    posX = event.x
                    posY = event.y
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                val xDif = posX - event.x
                val yDif = posY - event.y
                if (kotlin.math.abs(xDif) > kotlin.math.abs(yDif)) {
                    if (xDif < 0.0F && gameLogic.direction != Direction.LEFT) gameLogic.direction =
                        Direction.RIGHT
                    else if (xDif > 0.0F && gameLogic.direction != Direction.RIGHT) gameLogic.direction =
                        Direction.LEFT
                } else {
                    if (yDif > 0.0F && gameLogic.direction != Direction.DOWN) gameLogic.direction =
                        Direction.UP
                    else if (yDif < 0.0F && gameLogic.direction != Direction.UP) gameLogic.direction =
                        Direction.DOWN
                }
                return false
            }
        }
        return super.onTouchEvent(event)
    }

    fun gameOver(point: Int) {
        mainActivity.gameOver(point)
        sensorManager.unregisterListener(accelerometerSensorListener)
    }

    private var accelerometerSensorListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                if (sensorEvent.values[0] > 2f && gameLogic.direction != Direction.RIGHT) {
                    gameLogic.direction = Direction.LEFT
                } else if (sensorEvent.values[0] < -2f && gameLogic.direction != Direction.LEFT) {
                    gameLogic.direction = Direction.RIGHT
                } else if (sensorEvent.values[2] < 2f && gameLogic.direction != Direction.UP) {
                    gameLogic.direction = Direction.DOWN
                } else if (sensorEvent.values[2] > 2f && gameLogic.direction != Direction.DOWN) {
                    gameLogic.direction = Direction.UP
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }

    private fun useAccelerometer() {
        if (accelerometerSensor == null) {
            sensorManager.unregisterListener(accelerometerSensorListener)
            Toast.makeText(context, "This device has no Accelerometer!", Toast.LENGTH_SHORT).show()
            mainActivity.startActivity(Intent(mainActivity, MainActivity::class.java))
        } else
            sensorManager.registerListener(
                accelerometerSensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_UI
            )
    }


}