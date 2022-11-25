package com.example.snake.gamelogic

import android.content.Context
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
            if (ct == Control.SLIDING.toString())
                return Control.SLIDING
            else if (ct == Control.CLICK.toString())
                return Control.CLICK
            return Control.SLIDING
        }

        fun saveControl(context: Context, control: Control) {
            File(context.filesDir, fileName).writeText(control.toString())
        }
    }
}