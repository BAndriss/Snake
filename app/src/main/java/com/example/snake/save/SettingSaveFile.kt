package com.example.snake.save

import android.content.Context
import com.example.snake.gamelogic.Control
import java.io.File
import java.nio.charset.StandardCharsets

class SettingSaveFile {
    companion object {
        private const val fileName = "controlSetting.txt"

        fun loadControl(context: Context): Control {
            val file = File(context.filesDir, fileName)
            if (!file.exists())
                return Control.SLIDING
            val ct = file.readText(StandardCharsets.UTF_8)
            ct.ifBlank { return Control.SLIDING }
            return when (ct) {
                Control.SLIDING.toString() -> Control.SLIDING
                Control.CLICK.toString() -> Control.CLICK
                Control.ACCELEROMETER.toString() -> Control.ACCELEROMETER
                else -> Control.SLIDING
            }
        }

        fun saveControl(context: Context, control: Control) {
            File(context.filesDir, fileName).writeText(control.toString())
        }
    }
}