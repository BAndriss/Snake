package com.example.snake

class Food(var width: Int, var height: Int, var fieldSize: Int) {
    var  posX: Int = (0..width/fieldSize).random()*fieldSize
    var posY: Int = (0..height/fieldSize).random()*fieldSize

}