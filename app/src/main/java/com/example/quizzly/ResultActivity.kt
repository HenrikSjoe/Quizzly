package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    lateinit var result: TextView
    lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        result = findViewById(R.id.resultTextView)
        image = findViewById(R.id.resultImageView)

        val button = findViewById<Button>(R.id.button)



        button.setOnClickListener {
            startMainActivity()
        }

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)

        if (correctAnswers == 5) {
            image.setImageResource(R.drawable.trophy2)
            result.text = "Alla rätt! Snyggt jobbat!"
         } else if (correctAnswers >= 3) {
             image.setImageResource(R.drawable.trophy2)
            result.text = "Bra jobbat! \nDu svarade rätt på $correctAnswers \nfrågor."
        } else if (correctAnswers == 1) {
            image.setImageResource(R.drawable.oops3)
            result.text = "Aj då! \nDu svarade rätt på $correctAnswers \nfråga."
        } else if (correctAnswers < 3) {
            image.setImageResource(R.drawable.oops3)
            result.text = "Aj då! \nDu svarade rätt på $correctAnswers \nfrågor."
        }

    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}