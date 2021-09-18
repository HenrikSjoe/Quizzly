package com.example.quizzly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mixedbtn = findViewById<Button>(R.id.mixedButton)

        mixedbtn.setOnClickListener{
            Log.d("hhh", "Tryck")
            startMixedActivity()
        }
    }

    fun startMixedActivity(){
        val intent = Intent(this, MixedActivity::class.java)
        startActivity(intent)
    }
}