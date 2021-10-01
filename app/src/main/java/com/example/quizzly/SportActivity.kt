package com.example.quizzly

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class SportActivity : AppCompatActivity() {

    lateinit var setQuestion: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var qNr: TextView //
    lateinit var timer : TextView
    lateinit var progressBar: ProgressBar
    var numOfQ = 0
    var correctAnswers = 0
    var questionsList = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport)
        supportActionBar?.hide()

        timer = findViewById(R.id.sportTimerTextView)
        progressBar = findViewById(R.id.sportProgressBar)
        setQuestion = findViewById(R.id.sportQuestionTextView)
        btn1 = findViewById(R.id.sportOption1)
        btn2 = findViewById(R.id.sportOption2)
        btn3 = findViewById(R.id.sportOption3)
        btn4 = findViewById(R.id.sportOption4)
        qNr = findViewById(R.id.sportQNrTextView)

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
        btn1.setBackgroundColor(Color.WHITE)
        btn2.setBackgroundColor(Color.WHITE)
        btn3.setBackgroundColor(Color.WHITE)
        btn4.setBackgroundColor(Color.WHITE)
    }

    var countDownTimer = object : CountDownTimer(1000 * 11, 1000){
        override fun onTick(millisUntilFinished: Long) {
            timer.text = "Återstående tid: " + getString(
                R.string.formatted_time,
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
            ) + " sekunder"
        }

        override fun onFinish() {

            when {
                questionsList[numOfQ].answers[1].isCorrect -> {
                    btn2.setBackgroundColor(Color.parseColor("#66CC00"))
                }
                questionsList[numOfQ].answers[2].isCorrect -> {
                    btn3.setBackgroundColor(Color.parseColor("#66CC00"))
                }
                questionsList[numOfQ].answers[3].isCorrect -> {
                    btn4.setBackgroundColor(Color.parseColor("#66CC00"))
                }
                questionsList[numOfQ].answers[0].isCorrect -> {
                    btn1.setBackgroundColor(Color.parseColor("#66CC00"))
                }
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
                btn1.setBackgroundColor(Color.parseColor("#66CC00"))
                correctAnswers++
            } else {
                btn1.setBackgroundColor(Color.parseColor("#FF3333"))

                when {
                    questionsList[numOfQ].answers[1].isCorrect -> {
                        btn2.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[2].isCorrect -> {
                        btn3.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[3].isCorrect -> {
                        btn4.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                }
            }
            whenClickedOrTimeUp()
        }
    }

    fun btn2() {
        btn2.setOnClickListener {
            stopTimer()
            if (questionsList[numOfQ].answers[1].isCorrect) {
                btn2.setBackgroundColor(Color.parseColor("#66CC00"))
                correctAnswers++
            } else {
                btn2.setBackgroundColor(Color.parseColor("#FF3333"))

                when {
                    questionsList[numOfQ].answers[2].isCorrect -> {
                        btn3.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[3].isCorrect -> {
                        btn4.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[0].isCorrect -> {
                        btn1.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                }
            }
            whenClickedOrTimeUp()
        }
    }

    fun btn3() {
        btn3.setOnClickListener {
            stopTimer()

            if (questionsList[numOfQ].answers[2].isCorrect) {
                btn3.setBackgroundColor(Color.parseColor("#66CC00"))
                correctAnswers++
            } else {
                btn3.setBackgroundColor(Color.parseColor("#FF3333"))

                when {
                    questionsList[numOfQ].answers[3].isCorrect -> {
                        btn4.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[0].isCorrect -> {
                        btn1.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[1].isCorrect -> {
                        btn2.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                }
            }
            whenClickedOrTimeUp()
        }
    }
    fun btn4() {
        btn4.setOnClickListener {
            stopTimer()
            if (questionsList[numOfQ].answers[3].isCorrect) {
                btn4.setBackgroundColor(Color.parseColor("#66CC00"))
                correctAnswers++
            } else {
                btn4.setBackgroundColor(Color.parseColor("#FF3333"))

                when {
                    questionsList[numOfQ].answers[0].isCorrect -> {
                        btn1.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[1].isCorrect -> {
                        btn2.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                    questionsList[numOfQ].answers[2].isCorrect -> {
                        btn3.setBackgroundColor(Color.parseColor("#66CC00"))
                    }
                }
            }
            whenClickedOrTimeUp()
        }
    }

    fun whenClickedOrTimeUp () {
        numOfQ++
        Handler(Looper.getMainLooper()).postDelayed({
            setQsOrStartResultActivity()
        }, 700)
    }

    fun addQs() {
        val q1Answers = mutableListOf(
            Answer("Portugal", true),
            Answer("Frankrike", false),
            Answer("Italien", false),
            Answer("Spanien", false)

        )

        val q2Answers = mutableListOf(
            Answer("Polen & Ukraina", true),
            Answer("Österrike & Schweiz", false),
            Answer("Österrike & Ungern", false),
            Answer("Danmark & Tyskland", false)
        )

        val q3Answers = mutableListOf(
            Answer("5", true),
            Answer("3", false),
            Answer("7", false),
            Answer("2", false)
        )

        val q4Answers = mutableListOf(
            Answer("Newcastle", true),
            Answer("Manchester United", false),
            Answer("Brighton", false),
            Answer("Watford", false)
        )
        val q5Answers = mutableListOf(
            Answer("8", true),
            Answer("2", false),
            Answer("5", false),
            Answer("6", false)
        )

        val q6Answers = mutableListOf(
            Answer("Mercedes", true),
            Answer("Ferarri", false),
            Answer("Renault", false),
            Answer("Red Bull", false)
        )

        val q7Answers = mutableListOf(
            Answer("Roger Ljung", true),
            Answer("Kenneth Andersson", false),
            Answer("Martin Dahlin", false),
            Answer("Tomas Brolin", false)
        )

        val q8Answers = mutableListOf(
            Answer("Storslalom & kombination", true),
            Answer("Slalom & störtlopp", false),
            Answer("störtlopp & kombination", false),
            Answer("Storslalom & super-G", false)
        )

        val q9Answers = mutableListOf(
            Answer("300", true),
            Answer("250", false),
            Answer("500", false),
            Answer("120", false)
        )

        val q10Answers = mutableListOf(
            Answer("Stavhopp", true),
            Answer("Kula", false),
            Answer("Spjut", false),
            Answer("Diskus", false)
        )

        val q11Answers = mutableListOf(
            Answer("Hallstavik", true),
            Answer("Eskilstuna", false),
            Answer("Motala", false),
            Answer("Vetlanda", false)
        )

        val q12Answers = mutableListOf(
            Answer("11", true),
            Answer("9", false),
            Answer("7", false),
            Answer("15", false)
        )

        val q13Answers = mutableListOf(
            Answer("Tyskland", true),
            Answer("Samma upplägg som sist", false),
            Answer("Spanien & Portugal", false),
            Answer("Frankrike", false)
        )


        val q14Answers = mutableListOf(
            Answer("6,15 meter", true),
            Answer("6,00 meter", false),
            Answer("5,90 meter", false),
            Answer("6,21 meter", false)
        )

        val q15Answers = mutableListOf(
            Answer("Deuce", true),
            Answer("Love", false),
            Answer("Draw", false),
            Answer("Tiebreak", false)
        )

        val q16Answers = mutableListOf(
            Answer("Fjärilsim", true),
            Answer("Ryggsim", false),
            Answer("Bröstsim", false),
            Answer("Hundsim", false)
        )

        val q17Answers = mutableListOf(
            Answer("Sydney", true),
            Answer("Aten", false),
            Answer("Atlanta", false),
            Answer("Moskva", false)
        )

        val q18Answers = mutableListOf(
            Answer("64", true),
            Answer("81", false),
            Answer("100", false),
            Answer("49", false)
        )

        val q19Answers = mutableListOf(
            Answer("800 meter", true),
            Answer("Höjdhopp", false),
            Answer("Kulstötning", false),
            Answer("Spjut", false)
        )

        val q20Answers = mutableListOf(
            Answer("Svart", true),
            Answer("Grön", false),
            Answer("Röd", false),
            Answer("Brun", false)
        )

        questionsList.add(Question("Fotbolls-EM 2016 avgjordes i Frankrike. Vilket land vann? ", q1Answers))

        questionsList.add(Question("Vilka två länder var värdar för fotbolls-EM 2012?", q2Answers))

        questionsList.add(Question("Hur många Wimbledontitlar har Björn Borg?", q3Answers))

        questionsList.add(Question("Vilket engelskt fotbollslag kallas för The Magpies?", q4Answers))

        questionsList.add(Question("Hur många mål gjorde den \"riktiga\" Ronaldo i VM 2002?", q5Answers))

        questionsList.add(Question("I vilket stall avslutade Michael Schumacher sin karriär?", q6Answers))

        questionsList.add(Question("Vem gjorde det första målet för Sverige i fotbolls-VM 1994?", q7Answers))

        questionsList.add(Question("I vilka grenar har Pernilla Wiberg tagit BÅDE OS-guld och VM-guld??", q8Answers))

        questionsList.add(Question("Hur många poäng ger en perfekt serie i bowling?", q9Answers))

        questionsList.add(Question("Sergej Bubka tog sex raka VM-guld. I vilken friidrottsgren?", q10Answers))

        questionsList.add(Question("Varifrån kommer speedwayklubben Rospiggarna?", q11Answers))

        questionsList.add(Question("Hur många spelare har varje lag på planen i amerikansk fotboll?", q12Answers))

        questionsList.add(Question("Fotbolls-EM 2021 var utspritt lite överallt. Var hålls det 2024?", q13Answers)) //

        questionsList.add(Question("Hur lyder Armand Duplantis utomhusvärldsrekord i stavhopp?", q14Answers))

        questionsList.add(Question("Vilken term används i tennis vid ställningen 40-40?", q15Answers))

        questionsList.add(Question("Vilket är det snabbaste simsättet efter frisim?", q16Answers))

        questionsList.add(Question("Var hölls OS år 2000?", q17Answers))

        questionsList.add(Question("Hur många rutor består ett schackbräde av?", q18Answers))

        questionsList.add(Question("Vilken gren avslutar alltid friidrottens sjukamp?", q19Answers))

        questionsList.add(Question("Vilken färg har den boll som ger flest poäng i snooker?", q20Answers))
    }
}