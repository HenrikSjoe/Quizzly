package com.example.quizzly

import android.content.Intent
import android.graphics.Color.green
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MixedActivity : AppCompatActivity() {

    lateinit var setQuestion: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var qNr: TextView
    val handler = Handler()
    var numOfQ = 0

    var correctAnswers = 0
    var questionsList = mutableListOf<Question>()

    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed)
        supportActionBar?.hide()


        progressBar = findViewById(R.id.progressBar)

        setQuestion = findViewById(R.id.questionTextView)

        btn1 = findViewById(R.id.option1)
        btn2 = findViewById(R.id.option2)
        btn3 = findViewById(R.id.option3)
        btn4 = findViewById(R.id.option4)
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


    fun shuffleQs() {
        questionsList.shuffle()

    }

    fun setQs() {

        progressBar.incrementProgressBy(20)

        qNr.text = "${numOfQ + 1}/5"

        val question = questionsList[numOfQ]

        question.answers.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[numOfQ].answers[0].answer
        btn2.text = questionsList[numOfQ].answers[1].answer
        btn3.text = questionsList[numOfQ].answers[2].answer
        btn4.text = questionsList[numOfQ].answers[3].answer

        btn1.setOnClickListener {
            if (questionsList[numOfQ].answers[0].isCorrect) {
                btn1.setBackgroundColor(getResources().getColor(R.color.green))
                correctAnswers++
            } else {
                btn1.setBackgroundColor(getResources().getColor(R.color.red))

                if (questionsList[numOfQ].answers[1].isCorrect) {
                    btn2.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[2].isCorrect) {
                    btn3.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[3].isCorrect) {
                    btn4.setBackgroundColor(getResources().getColor(R.color.green))
                }
            }
            numOfQ++
            handler.postDelayed({
                setQsOrStartResultActivity()
            }, 700)
        }

        btn2.setOnClickListener {
            if (questionsList[numOfQ].answers[1].isCorrect) {
                btn2.setBackgroundColor(getResources().getColor(R.color.green))
                correctAnswers++
            } else {
                btn2.setBackgroundColor(getResources().getColor(R.color.red))

                if (questionsList[numOfQ].answers[2].isCorrect) {
                    btn3.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[3].isCorrect) {
                    btn4.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[0].isCorrect) {
                    btn1.setBackgroundColor(getResources().getColor(R.color.green))
                }
            }
            numOfQ++
            handler.postDelayed({
                setQsOrStartResultActivity()
            }, 700)
        }


        btn3.setOnClickListener {


            if (questionsList[numOfQ].answers[2].isCorrect) {
                btn3.setBackgroundColor(getResources().getColor(R.color.green))
                correctAnswers++
            } else {
                btn3.setBackgroundColor(getResources().getColor(R.color.red))

                if (questionsList[numOfQ].answers[3].isCorrect) {
                    btn4.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[0].isCorrect) {
                    btn1.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[1].isCorrect) {
                    btn2.setBackgroundColor(getResources().getColor(R.color.green))
                }
            }
            numOfQ++
            handler.postDelayed({
                setQsOrStartResultActivity()
            }, 700)
        }

        btn4.setOnClickListener {
            if (questionsList[numOfQ].answers[3].isCorrect) {
                btn4.setBackgroundColor(getResources().getColor(R.color.green))
                correctAnswers++
            } else {
                btn4.setBackgroundColor(getResources().getColor(R.color.red))

                if (questionsList[numOfQ].answers[0].isCorrect) {
                    btn1.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[1].isCorrect) {
                    btn2.setBackgroundColor(getResources().getColor(R.color.green))
                } else if (questionsList[numOfQ].answers[2].isCorrect) {
                    btn3.setBackgroundColor(getResources().getColor(R.color.green))
                }
            }
            numOfQ++
            handler.postDelayed({
                setQsOrStartResultActivity()
            }, 700)
        }
    }

    fun setQsOrStartResultActivity() {
        resetBtnColorWhite()
        if (numOfQ == 5) {
            startResultActivity()
        } else {
            setQs()
        }
    }

    fun addQs() {
        val q1Answers = mutableListOf(
            Answer("Barbara Millicent Roberts", true),
            Answer("Barbie McKen", false),
            Answer("Barbie Doll", false),
            Answer("Barbara", false)

        )

        val q2Answers = mutableListOf(
            Answer("Sålde begagnade möbler", true),
            Answer("Banktjänsteman", false),
            Answer("Mångsysslare", false),
            Answer("Mattläggare", false)
        )

        val q3Answers = mutableListOf(
            Answer("Ca. 25000", true),
            Answer("Ca. 10000", false),
            Answer("Ca. 40000", false),
            Answer("Ca. 90000", false)
        )

        val q4Answers = mutableListOf(
            Answer("Frankrike", true),
            Answer("Belgien", false),
            Answer("Schweiz", false),
            Answer("Ungern", false)
        )
        val q5Answers = mutableListOf(
            Answer("År 1206", true),
            Answer("201 f.Kr", false),
            Answer("År 301", false),
            Answer("År 1590", false)
        )

        val q6Answers = mutableListOf(
            Answer("Ag", true),
            Answer("Au", false),
            Answer("Si", false),
            Answer("Sr", false)
        )

        val q7Answers = mutableListOf(
            Answer("3", true),
            Answer("1", false),
            Answer("2", false),
            Answer("4", false)
        )

        val q8Answers = mutableListOf(
            Answer("460° C", true),
            Answer("55° C", false),
            Answer("-125° C", false),
            Answer("-165° C", false)
        )

        val q9Answers = mutableListOf(
            Answer("Ian Fleming", true),
            Answer("George Lazenby", false),
            Answer("John le Carré", false),
            Answer("John Curry", false)
        )

        val q10Answers = mutableListOf(
            Answer("50", true),
            Answer("45", false),
            Answer("47", false),
            Answer("55", false)
        )

        questionsList.add(Question("Vad är dockan, Barbies, fulla namn?", q1Answers))

        questionsList.add(Question("Vad jobbade Al Capone \"officiellt\" med?", q2Answers))

        questionsList.add(
            Question(
                "Hur många andetag tar en människa per dag, i snitt?",
                q3Answers
            )
        )

        questionsList.add(Question("Var kom konstnären Henri Matisse från?", q4Answers))

        questionsList.add(
            Question(
                "Vilket år påbörjade Djinghis Khan sin erövring av Asien?",
                q5Answers
            )
        )

        questionsList.add(Question("Vad är det kemiska tecknet för silver?", q6Answers))

        questionsList.add(Question("Hur många hjärtan har en bläckfisk?", q7Answers))

        questionsList.add(Question("Vad är den genomsnittliga yttemperaturen på Venus?", q8Answers))

        questionsList.add(Question("Vem skrev böckerna om James Bond?", q9Answers))

        questionsList.add(Question("Hur gammal blev Michael Jackson?", q10Answers))
    }

    fun resetBtnColorWhite() {
        btn1.setBackgroundColor(getResources().getColor(R.color.white));
        btn2.setBackgroundColor(getResources().getColor(R.color.white));
        btn3.setBackgroundColor(getResources().getColor(R.color.white));
        btn4.setBackgroundColor(getResources().getColor(R.color.white));
    }
}



