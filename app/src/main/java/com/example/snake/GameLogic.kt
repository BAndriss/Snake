package com.example.snake

import java.util.*
import kotlin.concurrent.schedule

class GameLogic {
    var snake : Snake
    var food: ArrayList<Food> =  ArrayList()
    private var point: Int = 0
    var width: Int
    var height: Int
    private var fieldSize : Int
    var direction: Direction = Direction.RIGHT
    private var gameView : GameView
    private var timer : Timer

    constructor( width: Int, height: Int, fieldSize: Int, gameView: GameView) {
        this.width = width
        this.height = height
        this.fieldSize = fieldSize
        this.gameView = gameView
        this.snake = Snake(4, (width/2),(height/2), fieldSize*2, this)
        this.food.add(Food(width,height,fieldSize))
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                //move()
                food.add(Food(width,height,fieldSize))
                gameView.invalidate()
            }
        }, 1000, 500)
     }

    fun move(){
        when (direction) {
            Direction.UP -> { snake.moveUp()}
            Direction.DOWN -> {snake.moveDown()}
            Direction.LEFT -> {snake.moveLeft()}
            Direction.RIGHT -> {snake.moveRight()}
        }
    }

    fun gameOver(){
        timer.cancel()
    }

}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}