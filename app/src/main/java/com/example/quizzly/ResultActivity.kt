package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ResultActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job

    lateinit var db: AppDatabase

    lateinit var result: TextView

    var highscoreList = mutableListOf<HighScore>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        job = Job()

        db = AppDatabase.getInstance(this)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val name = intent.getStringExtra("name")


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = HighScoreRecyclerAdapter(this, highscoreList)

        recyclerView.adapter = adapter

        addNewHighscore(HighScore(0, name, correctAnswers))

        loadHighscores()

        adapter.notifyDataSetChanged()








        result = findViewById(R.id.resultTextView)


        val button = findViewById<Button>(R.id.button)


        button.setOnClickListener {
            startMainActivity()
        }


        when {
            correctAnswers == 10 -> {

                result.text = "Alla rätt! Snyggt jobbat $name!"
            }
            correctAnswers >= 5 -> {

                result.text = "Bra jobbat $name! Du svarade rätt på $correctAnswers frågor."
            }
            correctAnswers == 1 -> {

                result.text = "Aj då $name! Du svarade rätt på $correctAnswers fråga."
            }
            correctAnswers < 5 -> {

                result.text = "Aj då $name! Du svarade rätt på $correctAnswers frågor."
            }
        }

    }

    fun addNewHighscore(highScore: HighScore) {

        launch(Dispatchers.IO) {
            db.highScoreDao.insert(highScore)
        }

    }

    fun loadHighscores() {
        val highScores = async(Dispatchers.IO) {
            db.highScoreDao.getAll()
        }
        launch {
            val list = highScores.await().toMutableList()
            highscoreList = list
            Log.d(
                "henrik",
                "loadHighscores:player: ${highscoreList[1].player} score: ${highscoreList[1].score}"
            )
            Log.d("henrik", "loadHighscores: $highscoreList")

        }

    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}