package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ResultActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var db : AppDatabase

    lateinit var result: TextView
    lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        job = Job()

        db = AppDatabase.getInstance(this)

        var name : String? = null
        var correctAnswers = 0


        result = findViewById(R.id.resultTextView)
        image = findViewById(R.id.resultImageView)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            startMainActivity()
        }

         correctAnswers = intent.getIntExtra("correctAnswers", 0)
         name = intent.getStringExtra("name")

        addNewHighscore(HighScore(0, name, correctAnswers))

        when {
            correctAnswers == 10 -> {
                image.setImageResource(R.drawable.trophy2)
                result.text = "Alla rätt! Snyggt jobbat $name!"
            }
            correctAnswers >= 5 -> {
                image.setImageResource(R.drawable.trophy2)
                result.text = "Bra jobbat $name! Du svarade rätt på $correctAnswers frågor."
            }
            correctAnswers == 1 -> {
                image.setImageResource(R.drawable.oops3)
                result.text = "Aj då $name! Du svarade rätt på $correctAnswers fråga."
            }
            correctAnswers < 5 -> {
                image.setImageResource(R.drawable.oops3)
                result.text = "Aj då $name! Du svarade rätt på $correctAnswers frågor."
            }
        }

    }

    fun addNewHighscore(highScore: HighScore){

        launch (Dispatchers.IO){
            db.highScoreDao.insert(highScore)
        }

    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}