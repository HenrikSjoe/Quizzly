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

class MixedActivity : AppCompatActivity() {

    lateinit var setQuestion: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var qNr: TextView //
    lateinit var timer: TextView
    lateinit var progressBar: ProgressBar
    var numOfQ = 0
    var correctAnswers = 0
    var questionsList = mutableListOf<Question>()
    var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed)
        supportActionBar?.hide()

        name = intent.getStringExtra("name")


        timer = findViewById(R.id.mixedTimerTextView)
        progressBar = findViewById(R.id.mixedProgressBar)
        setQuestion = findViewById(R.id.mixedQuestionTextView)
        btn1 = findViewById(R.id.mixedOption1)
        btn2 = findViewById(R.id.mixedOption2)
        btn3 = findViewById(R.id.mixedOption3)
        btn4 = findViewById(R.id.mixedOption4)
        qNr = findViewById(R.id.mixedQNrTextView)

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

    var countDownTimer = object : CountDownTimer(1000 * 11, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timer.text = "??terst??ende tid: " + getString(
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

    fun stopTimer() {

        countDownTimer.cancel()
    }

    fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)


        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("name", name)
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

    fun whenClickedOrTimeUp() {
        numOfQ++
        Handler(Looper.getMainLooper()).postDelayed({
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
            Answer("S??lde begagnade m??bler", true),
            Answer("Banktj??nsteman", false),
            Answer("M??ngsysslare", false),
            Answer("Mattl??ggare", false)
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
            Answer("??r 1206", true),
            Answer("201 f.Kr", false),
            Answer("??r 301", false),
            Answer("??r 1590", false)
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
            Answer("460?? C", true),
            Answer("55?? C", false),
            Answer("-125?? C", false),
            Answer("-165?? C", false)
        )

        val q9Answers = mutableListOf(
            Answer("Ian Fleming", true),
            Answer("George Lazenby", false),
            Answer("John le Carr??", false),
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
            Answer("V??sterg??tland", true),
            Answer("??sterg??tland", false),
            Answer("Halland", false),
            Answer("Bohusl??n", false)
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
            Answer("M/S Dahl??na", false)
        )

        val q15Answers = mutableListOf(
            Answer("Sammet", true),
            Answer("Pineapple", false),
            Answer("S??k??", false),
            Answer("Omaha", false)
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
            Answer("Z??rich", false),
            Answer("Berlin", false)
        )

        val q19Answers = mutableListOf(
            Answer("August Strindberg", true),
            Answer("Viktor Rydberg", false),
            Answer("Fredrika Bremer", false),
            Answer("Selma Lagerl??f", false)
        )

        val q20Answers = mutableListOf(
            Answer("En svamp", true),
            Answer("En mossa", false),
            Answer("En buske", false),
            Answer("Ett tr??d", false)
        )

        questionsList.add(Question("Vad ??r dockan, Barbies, fulla namn?", q1Answers))

        questionsList.add(Question("Vad jobbade Al Capone \"officiellt\" med?", q2Answers))

        questionsList.add(
            Question(
                "Hur m??nga andetag tar en m??nniska per dag, i snitt?",
                q3Answers
            )
        )

        questionsList.add(Question("Var kom konstn??ren Henri Matisse fr??n?", q4Answers))

        questionsList.add(
            Question(
                "Vilket ??r p??b??rjade Djinghis Khan sin er??vring av Asien?",
                q5Answers
            )
        )

        questionsList.add(Question("Vad ??r det kemiska tecknet f??r silver?", q6Answers))

        questionsList.add(Question("Hur m??nga hj??rtan har en bl??ckfisk?", q7Answers))

        questionsList.add(Question("Vad ??r den genomsnittliga yttemperaturen p?? Venus?", q8Answers))

        questionsList.add(Question("Vem skrev b??ckerna om James Bond?", q9Answers))

        questionsList.add(Question("Hur gammal blev Michael Jackson?", q10Answers))

        questionsList.add(Question("N??r sj??nk Titanic?", q11Answers))

        questionsList.add(Question("I vilket landskap ligger Sk??vde och Alings??s?", q12Answers))

        questionsList.add(
            Question(
                "Ungef??r hur m??nga liter mj??lk g??r det ??t f??r att tillverka 1 kg ost?",
                q13Answers
            )
        ) //

        questionsList.add(Question("Vad hette fartyget i tv-serien Rederiet?", q14Answers))

        questionsList.add(Question("Vilken av dessa ??r INTE en pokervariant?", q15Answers))

        questionsList.add(
            Question(
                "Vilket land producerar mer apelsiner ??n de ??vriga tillsammans?",
                q16Answers
            )
        )

        questionsList.add(
            Question(
                "Hur l??ngt m??ste man simma f??r att ta simborgarm??rket?",
                q17Answers
            )
        )

        questionsList.add(Question("Wolfgang Amadeus Mozart f??ddes i...?", q18Answers))

        questionsList.add(Question("Vem skrev Hems??borna och R??da rummet?", q19Answers))

        questionsList.add(
            Question(
                "Vad var den st??rsta organism man n??gonsin hittat f??r n??got?",
                q20Answers
            )
        )
    }


}




