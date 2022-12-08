package com.example.snake.gamelogic

import com.example.snake.view.GameView
import java.util.*

class GameLogic(
    var width: Int,
    var height: Int,
    private var fieldSize: Int,
    private var gameView: GameView
) {
    var snake: Snake
    var food: Food
    var direction: Direction = Direction.UP
    private var point: Int = 0
    private var timer: Timer
    private val startSnakeLength = 20
    private val gameSpeed = 100.toLong()

    init {
        this.snake = Snake(startSnakeLength, (width / 2), (height / 2), fieldSize * 2, this)
        food = Food(width, height, fieldSize)
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                move()
                if (food.checkTouchSnakeHead(snake.snakePart[0].posX, snake.snakePart[0].posY)) {
                    point++
                    snake.addPart()
                    food = Food(width, height, fieldSize)
                }
                gameView.invalidate()
            }
        }, 1000, gameSpeed)
    }

    fun move() {
        when (direction) {
            Direction.UP ->
                snake.moveUp()
            Direction.DOWN ->
                snake.moveDown()
            Direction.LEFT ->
                snake.moveLeft()
            Direction.RIGHT ->
                snake.moveRight()
        }
    }

    fun gameOver() {
        timer.cancel()
        gameView.gameOver(point)
    }
}

