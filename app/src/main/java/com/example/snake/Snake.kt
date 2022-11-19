package com.example.snake


class Snake(
    length: Int,
    posHeadX: Int,
    posHeadY: Int,
    private var distanceToSnakePart: Int = 50,
    private var gameLogic: GameLogic
) {
    var snakePart : ArrayList<SnakePart> =  ArrayList()

    init {
        snakePart.add(SnakePart(posHeadX,posHeadY))
        var i =0
        while (i < length-1){
            this.addPart()
            i++
        }
    }

    fun moveUp(){
        movePart()
        snakePart[0].posY -= distanceToSnakePart
        for(part in snakePart)
            if(part.posY <= 0)
                part.posY = gameLogic.height
        checkGameOver()
    }
    fun moveDown(){
        movePart()
        snakePart[0].posY += distanceToSnakePart
        for(part in snakePart)
            if(part.posY >= gameLogic.height)
                part.posY = 0
        checkGameOver()
    }
    fun moveRight(){
        movePart()
        snakePart[0].posX += distanceToSnakePart
        for(part in snakePart)
            if(part.posX >= gameLogic.width)
                part.posX = 0
        checkGameOver()
    }
    fun moveLeft(){
        movePart()
        snakePart[0].posX -= distanceToSnakePart
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
            if(part != snakePart[0] && part.checkPosConflict(snakePart[0].posX, snakePart[0].posY, distanceToSnakePart/2))
                gameLogic.gameOver()
        }
    }
    fun addPart(){
        snakePart.add(SnakePart(snakePart.last().posX, snakePart.last().posY+distanceToSnakePart))
    }
}