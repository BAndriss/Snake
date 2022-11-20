package com.example.snake.gamelogic

class SnakePart(var posX: Int, var posY: Int) {
    fun checkPosConflict(x: Int, y: Int, accuracy: Int): Boolean {
        if (posX + accuracy >= x && x >= posX - accuracy && y >= posY - accuracy && y <= posY + accuracy)
            return true
        return false;
    }
}