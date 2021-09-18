package com.example.quizzly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.resultTextView)

        val answeredCorrect = intent.getIntExtra("answeredCorrect",0)


        if (answeredCorrect == 5) {
            result.text = "Alla rätt! Snyggt jobbat!"
        } else if (answeredCorrect >= 3) {
            result.text = "Bra jobbat! \nDu svarade rätt på $answeredCorrect \nfrågor."
        } else if (answeredCorrect == 1) {
            result.text = "Aj då! \nDu svarade rätt på $answeredCorrect \nfråga."
        } else {
            result.text = "Aj då! \nDu svarade rätt på $answeredCorrect \nfrågor."
        }

    }
}