package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    lateinit var result: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        result = findViewById(R.id.resultTextView)

        val button = findViewById<Button>(R.id.button)

        button.text = "Starta om skiten"

        button.setOnClickListener {
            startMainActivity()
        }

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)

        if (correctAnswers == 5) {
            result.text = "Alla rätt! Snyggt jobbat!"
        } else if (correctAnswers >= 3) {
            result.text = "Bra jobbat! \nDu svarade rätt på $correctAnswers \nfrågor."
        } else if (correctAnswers == 1) {
            result.text = "Aj då! \nDu svarade rätt på $correctAnswers \nfråga."
        } else if (correctAnswers < 3) {
            result.text = "Aj då! \nDu svarade rätt på $correctAnswers \nfrågor."
        }

    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}