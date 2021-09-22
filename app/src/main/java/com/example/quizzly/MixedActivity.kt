package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MixedActivity : AppCompatActivity() {

    lateinit var setQuestion: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var qNr: TextView
    var nr = 1
    var correctAnswers = 0
    var questionsList = mutableListOf<Questions>()
    var q1Answers = mutableListOf<Answer>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed)
        supportActionBar?.hide()

        setQuestion = findViewById(R.id.questionTextView)

        btn1 = findViewById(R.id.option1)
        btn2 = findViewById(R.id.option2)
        btn3 = findViewById(R.id.option3)
        qNr = findViewById(R.id.qNrTextView)

        addQs()
        shuffleQs()
        setQs()
    }

    fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)
    }

    fun addQs() {

        questionsList = mutableListOf<Questions>()

        q1Answers = mutableListOf(
            Answer("Barbara Millicent Roberts", true),
            Answer("Barbie McKen", false),
            Answer("Barbie Doll", false)
        )

        val q1 = Questions(1, "Vad heter Barbie?", q1Answers)
        questionsList.add(q1)

        val q2Answers = mutableListOf(
            Answer("Sålde begagnade möbler", true),
            Answer("Banktjänsteman", false),
            Answer("Mångsysslare", false)
        )

        val q2 = Questions(2, "Vad angav Al Capones visitkort att han jobbade med?", q2Answers)
        questionsList.add(q2)

        val q3Answers = mutableListOf(
            Answer("Ca. 25000", true),
            Answer("Ca. 10000", false),
            Answer("Ca. 40000", false)
        )

        val q3 = Questions(3, "Hur många andetag tar en människa per dag, i snitt?", q3Answers)
        questionsList.add(q3)

        val q4Answers = mutableListOf(
            Answer("Fransk", true),
            Answer("Belgisk", false),
            Answer("Schweizisk", false)
        )

        val q4 = Questions(4, "Vilken nationalitet hade konstnären Henri Matisse?", q4Answers)
        questionsList.add(q4)

        val q5Answers = mutableListOf(
            Answer("År 1206", true),
            Answer("201 f.Kr", false),
            Answer("År 301", false)
        )

        val q5 = Questions(5, "Vilket år påbörjade Djinghis Khan sin erövring av Asien?", q5Answers)
        questionsList.add(q5)

    }

    fun shuffleQs() {
        questionsList.shuffle()

    }

    fun setQs() {

        qNr.text = "Fråga $nr/5"

        val question = questionsList[0]

        question.answers!!.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[0].answers!![0].answer
        btn2.text = questionsList[0].answers!![1].answer//q1Answers[1].answer
        btn3.text = questionsList[0].answers!![2].answer//q1Answers[2].answer

        btn1.setOnClickListener() {
            Log.d("!!!", "${questionsList.size}")
            if (questionsList[0].answers!![0].isCorrect) {
                correctAnswers++
            }
            setQ2()
        }

        btn2.setOnClickListener() {
            if (questionsList[0].answers!![1].isCorrect) {
                correctAnswers++
            }
            setQ2()
        }

        btn3.setOnClickListener() {
            if (questionsList[0].answers!![2].isCorrect) {
                correctAnswers++
            }
            setQ2()
        }
    }

    fun setQ2() {
        val question = questionsList[1]

        nr++
        qNr.text = "Fråga $nr/5"

        question.answers!!.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[1].answers!![0].answer
        btn2.text = questionsList[1].answers!![1].answer//q1Answers[1].answer
        btn3.text = questionsList[1].answers!![2].answer//q1Answers[2].answer

        btn1.setOnClickListener() {
            Log.d("!!!", "${questionsList.size}")
            if (questionsList[1].answers!![0].isCorrect) {
                correctAnswers++
            }
            setQ3()
        }

        btn2.setOnClickListener() {
            if (questionsList[1].answers!![1].isCorrect) {
                correctAnswers++
            }
            setQ3()
        }

        btn3.setOnClickListener() {
            if (questionsList[1].answers!![2].isCorrect) {
                correctAnswers++
            }
            setQ3()
        }
    }

    fun setQ3() {
        val question = questionsList[2]

        nr++
        qNr.text = "Fråga $nr/5"

        question.answers!!.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[2].answers!![0].answer
        btn2.text = questionsList[2].answers!![1].answer//q1Answers[1].answer
        btn3.text = questionsList[2].answers!![2].answer//q1Answers[2].answer

        btn1.setOnClickListener() {
            Log.d("!!!", "${questionsList.size}")
            if (questionsList[2].answers!![0].isCorrect) {
                correctAnswers++
            }
            setQ4()
        }

        btn2.setOnClickListener() {
            if (questionsList[2].answers!![1].isCorrect) {
                correctAnswers++
            }
            setQ4()
        }

        btn3.setOnClickListener() {
            if (questionsList[2].answers!![2].isCorrect) {
                correctAnswers++
            }
            setQ4()
        }
    }

    fun setQ4() {
        val question = questionsList[3]
        nr++
        qNr.text = "Fråga $nr/5"

        question.answers!!.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[3].answers!![0].answer
        btn2.text = questionsList[3].answers!![1].answer//q1Answers[1].answer
        btn3.text = questionsList[3].answers!![2].answer//q1Answers[2].answer

        btn1.setOnClickListener() {
            Log.d("!!!", "${questionsList.size}")
            if (questionsList[3].answers!![0].isCorrect) {
                correctAnswers++
            }
            setQ5()
        }

        btn2.setOnClickListener() {
            if (questionsList[3].answers!![1].isCorrect) {
                correctAnswers++
            }
            setQ5()
        }

        btn3.setOnClickListener() {
            if (questionsList[3].answers!![2].isCorrect) {
                correctAnswers++
            }
            setQ5()
        }
    }

    fun setQ5() {
        val question = questionsList[4]
        nr++
        qNr.text = "Fråga $nr/5"

        question.answers!!.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[4].answers!![0].answer
        btn2.text = questionsList[4].answers!![1].answer//q1Answers[1].answer
        btn3.text = questionsList[4].answers!![2].answer//q1Answers[2].answer

        btn1.setOnClickListener() {
            Log.d("!!!", "${questionsList.size}")
            if (questionsList[4].answers!![0].isCorrect) {
                correctAnswers++
            }
            startResultActivity()
        }

        btn2.setOnClickListener() {
            if (questionsList[4].answers!![1].isCorrect) {
                correctAnswers++
            }
            startResultActivity()
        }

        btn3.setOnClickListener() {
            if (questionsList[4].answers!![2].isCorrect) {
                correctAnswers++
            }
            startResultActivity()
        }
    }
}