package com.example.snake.gamelogic

import com.example.snake.view.GameView
import java.util.*

class GameLogic {
    var snake: Snake
    var food: Food
    var width: Int
    var height: Int
    var direction: Direction = Direction.UP
    private var point: Int = 0
    private var fieldSize: Int
    private var gameView: GameView
    private var timer: Timer

    constructor(width: Int, height: Int, fieldSize: Int, gameView: GameView) {
        this.width = width
        this.height = height
        this.fieldSize = fieldSize
        this.gameView = gameView
        //TODO snake length 4-re átírni
        this.snake = Snake(4, (width / 2), (height / 2), fieldSize * 2, this)
        food = Food(width, height, fieldSize)
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                //TODO ha az alkalmazás hátérbe van rakva idéglenes futt tovább
                move()
                if (food.checkTouchSnakeHead(snake.snakePart[0].posX, snake.snakePart[0].posY)) {
                    point++
                    snake.addPart()
                    food = Food(width, height, fieldSize)
                }
                gameView.invalidate()
            }
        }, 1000, 100)
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

