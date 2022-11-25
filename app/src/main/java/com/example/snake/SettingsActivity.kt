package com.example.snake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        LocaleList()
    }
}