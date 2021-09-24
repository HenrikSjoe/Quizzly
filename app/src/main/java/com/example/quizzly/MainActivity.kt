package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val mixedBtn = findViewById<Button>(R.id.mixedButton)
        val flagsBtn = findViewById<Button>(R.id.flagsButton)

        mixedBtn.setOnClickListener {
            startMixedActivity()
        }

        flagsBtn.setOnClickListener(){
            startFlagsActivity()
        }
    }

    fun startMixedActivity() {
        val intent = Intent(this, MixedActivity::class.java)
        startActivity(intent)
    }

    fun startFlagsActivity(){
        val intent = Intent(this, FlagsActivity::class.java)
        startActivity(intent)
    }
}