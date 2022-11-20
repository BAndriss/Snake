package com.example.snake.gamelogic

class Food(width: Int, height: Int, private var fieldSize: Int) {
    var  posX: Int =  (1 until width/fieldSize).random()*fieldSize
    var posY: Int = (1 until height/fieldSize).random()*fieldSize

    fun checkTouchSnakeHead(snakeHeadXPos: Int, snakeHeadYPos: Int): Boolean{
        if(posX+fieldSize>=snakeHeadXPos &&snakeHeadXPos >= posX-fieldSize && snakeHeadYPos >= posY-fieldSize && snakeHeadYPos<= posY+fieldSize )
            return true
        return false
    }
}