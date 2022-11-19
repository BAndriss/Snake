package com.example.snake

class Food(width: Int, height: Int, private var fieldSize: Int) {
    var  posX: Int =  (0..width/fieldSize).random()*fieldSize
    var posY: Int = (0..height/fieldSize).random()*fieldSize

    fun checkTouchSnakeHead(snakeHeadXPos: Int, snakeHeadYPos: Int): Boolean{
        if(posX+fieldSize>=snakeHeadXPos &&snakeHeadXPos >= posX-fieldSize && snakeHeadYPos >= posY-fieldSize && snakeHeadYPos<= posY+fieldSize )
            return true
        return false
    }
}