package com.example.quizzly

import android.content.Intent
import android.graphics.Color.green
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MixedActivity : AppCompatActivity() {

    lateinit var setQuestion: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var qNr: TextView //
    lateinit var timer : TextView
    lateinit var progressBar: ProgressBar
    val handler = Handler()
    var numOfQ = 0
    var correctAnswers = 0
    var questionsList = mutableListOf<Question>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed)
        supportActionBar?.hide()

        timer = findViewById(R.id.timerTextView)

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

    fun setQs() {
        startTimer()

        progressBar.incrementProgressBy(10)

        qNr.text = "${numOfQ + 1}/10"

        val question = questionsList[numOfQ]

        question.answers.shuffle()

        setQuestion.text = question.question

        btn1.text = questionsList[numOfQ].answers[0].answer
        btn2.text = questionsList[numOfQ].answers[1].answer
        btn3.text = questionsList[numOfQ].answers[2].answer
        btn4.text = questionsList[numOfQ].answers[3].answer

        btn1()
        btn2()
        btn3()
        btn4()
    }

    fun shuffleQs() {
        questionsList.shuffle()
    }

    fun setQsOrStartResultActivity() {
        resetBtnColorWhite()
        if (numOfQ == 10) {
            startResultActivity()
        } else {
            setQs()
        }
    }

    fun resetBtnColorWhite() {
        btn1.setBackgroundColor(getResources().getColor(R.color.white));
        btn2.setBackgroundColor(getResources().getColor(R.color.white));
        btn3.setBackgroundColor(getResources().getColor(R.color.white));
        btn4.setBackgroundColor(getResources().getColor(R.color.white));
    }

    var countDownTimer = object : CountDownTimer(1000 * 11, 1000){
        override fun onTick(millisUntilFinished: Long) {
            timer.text = "Återstående tid: " + getString(
                R.string.formatted_time,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
            ) + " sekunder"
        }

        override fun onFinish() {

            if (questionsList[numOfQ].answers[1].isCorrect) {
                btn2.setBackgroundColor(getResources().getColor(R.color.green))
            } else if (questionsList[numOfQ].answers[2].isCorrect) {
                btn3.setBackgroundColor(getResources().getColor(R.color.green))
            } else if (questionsList[numOfQ].answers[3].isCorrect) {
                btn4.setBackgroundColor(getResources().getColor(R.color.green))
            } else if (questionsList[numOfQ].answers[0].isCorrect) {
                btn1.setBackgroundColor(getResources().getColor(R.color.green))
            }

            whenClickedOrTimeUp()
        }
    }

    fun startTimer() {

        countDownTimer.start()
    }

    fun stopTimer(){

        countDownTimer.cancel()
    }

    fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)


        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)
    }

    fun btn1() {
        btn1.setOnClickListener {
            stopTimer()
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
            whenClickedOrTimeUp()
        }
    }

    fun btn2() {
        btn2.setOnClickListener {
            stopTimer()
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
            whenClickedOrTimeUp()
        }
    }

    fun btn3() {
        btn3.setOnClickListener {
            stopTimer()


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
            whenClickedOrTimeUp()
        }
    }
    fun btn4() {
        btn4.setOnClickListener {
            stopTimer()
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
            whenClickedOrTimeUp()
        }
    }

    fun whenClickedOrTimeUp () {
        numOfQ++
        handler.postDelayed({
            setQsOrStartResultActivity()
        }, 700)
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

        val q11Answers = mutableListOf(
            Answer("1912", true),
            Answer("1905", false),
            Answer("1892", false),
            Answer("1921", false)
        )

        val q12Answers = mutableListOf(
            Answer("Västergötland", true),
            Answer("Östergötland", false),
            Answer("Halland", false),
            Answer("Bohuslän", false)
        )

        val q13Answers = mutableListOf(
            Answer("10", true),
            Answer("1", false),
            Answer("5", false),
            Answer("15", false)
        )


        val q14Answers = mutableListOf(
            Answer("M/S Freja", true),
            Answer("Andrea Doria", false),
            Answer("M/S Reidar", false),
            Answer("M/S Dahléna", false)
        )

        val q15Answers = mutableListOf(
            Answer("Representanthusets talman", true),
            Answer("Utrikesministern", false),
            Answer("Den senator som suttit längst", false),
            Answer("Det blir nyval", false)
        )

        val q16Answers = mutableListOf(
            Answer("Brasilien", true),
            Answer("Marocko", false),
            Answer("Spanien", false),
            Answer("USA", false)
        )

        val q17Answers = mutableListOf(
            Answer("200 meter", true),
            Answer("500 meter", false),
            Answer("100 meter", false),
            Answer("250 meter", false)
        )

        val q18Answers = mutableListOf(
            Answer("Salzburg", true),
            Answer("Wien", false),
            Answer("Zürich", false),
            Answer("Berlin", false)
        )

        val q19Answers = mutableListOf(
            Answer("August Strindberg", true),
            Answer("Viktor Rydberg", false),
            Answer("Fredrika Bremer", false),
            Answer("Selma Lagerlöf", false)
        )

        val q20Answers = mutableListOf(
            Answer("En svamp", true),
            Answer("En mossa", false),
            Answer("En buske", false),
            Answer("Ett träd", false)
        )

        questionsList.add(Question("Vad är dockan, Barbies, fulla namn?", q1Answers))

        questionsList.add(Question("Vad jobbade Al Capone \"officiellt\" med?", q2Answers))

        questionsList.add(Question("Hur många andetag tar en människa per dag, i snitt?", q3Answers))

        questionsList.add(Question("Var kom konstnären Henri Matisse från?", q4Answers))

        questionsList.add(Question("Vilket år påbörjade Djinghis Khan sin erövring av Asien?", q5Answers))

        questionsList.add(Question("Vad är det kemiska tecknet för silver?", q6Answers))

        questionsList.add(Question("Hur många hjärtan har en bläckfisk?", q7Answers))

        questionsList.add(Question("Vad är den genomsnittliga yttemperaturen på Venus?", q8Answers))

        questionsList.add(Question("Vem skrev böckerna om James Bond?", q9Answers))

        questionsList.add(Question("Hur gammal blev Michael Jackson?", q10Answers))

        questionsList.add(Question("När sjönk Titanic?", q11Answers))

        questionsList.add(Question("I vilket landskap ligger Skövde och Alingsås?", q12Answers))

        questionsList.add(Question("Ungefär hur många liter mjölk går det åt för att tillverka 1 kg ost?", q13Answers)) //

        questionsList.add(Question("Vad hette fartyget i tv-serien Rederiet?", q14Answers))

        questionsList.add(Question("Om USA:s president och vicepresident dör, vem tillträder då?", q15Answers))

        questionsList.add(Question("Vilket land producerar mer apelsiner än de övriga tillsammans?", q16Answers))

        questionsList.add(Question("Hur långt måste man simma för att ta simborgarmärket?", q17Answers))

        questionsList.add(Question("Wolfgang Amadeus Mozart föddes i...?", q18Answers))

        questionsList.add(Question("Vem skrev Hemsöborna och Röda rummet?", q19Answers))

        questionsList.add(Question("Vad var den största organism man hittat för något?", q20Answers))
    }
}




