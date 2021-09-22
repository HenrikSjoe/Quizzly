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

        val mixedbtn = findViewById<Button>(R.id.mixedButton)

        mixedbtn.setOnClickListener {
            Log.d("hhh", "Tryck")
            startMixedActivity()
        }
    }

    fun startMixedActivity() {
        val intent = Intent(this, MixedActivity::class.java)
        startActivity(intent)
    }
}