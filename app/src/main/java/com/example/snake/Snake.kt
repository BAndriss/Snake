package com.example.snake


class Snake(
    length: Int,
    posHeadX: Int,
    posHeadY: Int,
    distanceToSnakePart: Int = 50,
    private var gameLogic: GameLogic
) {
    var snakePart : ArrayList<SnakePart> =  ArrayList()
    private var length : Int = 4
    private var distenceToSnakePart : Int = 50

    init {
        var i =0
        while (i < length){
            snakePart.add(SnakePart(posHeadX,posHeadY+i*distanceToSnakePart))
            i++
        }
    }

    fun moveUp(){
        movePart()
        snakePart[0].posY -= distenceToSnakePart
        for(part in snakePart)
            if(part.posY <= 0)
                part.posY = gameLogic.height
        checkGameOver()
    }
    fun moveDown(){
        movePart()
        snakePart[0].posY += distenceToSnakePart
        for(part in snakePart)
            if(part.posY >= gameLogic.height)
                part.posY = 0
        checkGameOver()
    }
    fun moveRight(){
        movePart()
        snakePart[0].posX += distenceToSnakePart
        for(part in snakePart)
            if(part.posX >= gameLogic.width)
                part.posX = 0
        checkGameOver()
    }
    fun moveLeft(){
        movePart()
        snakePart[0].posX -= distenceToSnakePart
        for(part in snakePart)
            if(part.posX <= 0)
                part.posX = gameLogic.width
        checkGameOver()
    }
    private fun movePart(){
        var i : Int =snakePart.size-1
        while (i>0) {
            snakePart[i].posY = snakePart[i - 1].posY
            snakePart[i].posX = snakePart[i - 1].posX
            i--
        }
    }
    private fun checkGameOver(){
        for(part in snakePart){
            if(part != snakePart[0] && part.posX == snakePart[0].posX && part.posY == snakePart[0].posY)
                gameLogic.gameOver()
        }
    }
}