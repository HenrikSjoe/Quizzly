package com.example.quizzly

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class FlagsActivity : AppCompatActivity() {


    lateinit var setFlag: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var timer: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var qNr: TextView
    var questionsList = mutableListOf<FlagQuestion>()
    var numOfQ = 0
    var correctAnswers = 0
    var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flags)

        supportActionBar?.hide()

        name = intent.getStringExtra("name")

        setFlag = findViewById(R.id.flagImageView)
        progressBar = findViewById(R.id.flagProgressBar)
        qNr = findViewById(R.id.qNumView)
        timer = findViewById(R.id.flagsTimer)
        btn1 = findViewById(R.id.opt1)
        btn2 = findViewById(R.id.opt2)
        btn3 = findViewById(R.id.opt3)
        btn4 = findViewById(R.id.opt4)

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

        setFlag.setImageResource(questionsList[numOfQ].flag!!)

        btn1.text = questionsList[numOfQ].answers[0].answer
        btn2.text = questionsList[numOfQ].answers[1].answer
        btn3.text = questionsList[numOfQ].answers[2].answer
        btn4.text = questionsList[numOfQ].answers[3].answer

        btn1()
        btn2()
        btn3()
        btn4()
    }

    fun setQsOrStartResultActivity() {
        resetBtnColorWhite()
        if (numOfQ == 10) {
            startResultActivity()
        } else {
            setQs()
        }
    }

    fun shuffleQs() {
        questionsList.shuffle()
    }

    fun resetBtnColorWhite() {
        btn1.setBackgroundColor(Color.WHITE)
        btn2.setBackgroundColor(Color.WHITE)
        btn3.setBackgroundColor(Color.WHITE)
        btn4.setBackgroundColor(Color.WHITE)
    }

    var countDownTimer = object : CountDownTimer(1000 * 11, 1000) {
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

    fun stopTimer() {
        countDownTimer.cancel()
    }

    fun whenClickedOrTimeUp() {
        numOfQ++
        Handler(Looper.getMainLooper()).postDelayed({
            setQsOrStartResultActivity()
        }, 700)
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

    fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("name", name)
        startActivity(intent)
    }


    fun addQs() {
        val algeriaAnswers = mutableListOf(
            Answer("Algeriet", true),
            Answer("Malawi", false),
            Answer("Benin", false),
            Answer("Etiopien", false)
        )
        val ivoryAnswers = mutableListOf(
            Answer("Elfenbenskusten", true),
            Answer("Gambia", false),
            Answer("Grekland", false),
            Answer("Irland", false)
        )
        val belarusAnswers = mutableListOf(
            Answer("Belarus", true),
            Answer("Ryssland", false),
            Answer("Kroatien", false),
            Answer("Bulgarien", false)
        )
        val sanMarinoAnswers = mutableListOf(
            Answer("San Marino", true),
            Answer("Georgien", false),
            Answer("Serbien", false),
            Answer("Vatikanstaten", false)
        )
        val georgiaAnswers = mutableListOf(
            Answer("Georgien", true),
            Answer("San Marino", false),
            Answer("Liechtenstein", false),
            Answer("Slovenien", false)
        )
        val irelandAnswers = mutableListOf(
            Answer("Irland", true),
            Answer("Elfenbenskusten", false),
            Answer("Slovakien", false),
            Answer("Mali", false)
        )
        val monacoAnswers = mutableListOf(
            Answer("Monaco", true),
            Answer("Polen", false),
            Answer("Danmark", false),
            Answer("Indonesien", false)
        )
        val polandAnswers = mutableListOf(
            Answer("Polen", true),
            Answer("Monaco", false),
            Answer("Indonesien", false),
            Answer("Malta", false)
        )
        val brazilAnswers = mutableListOf(
            Answer("Brasilien", true),
            Answer("Panama", false),
            Answer("Chile", false),
            Answer("Argentina", false)
        )
        val colombiaAnswers = mutableListOf(
            Answer("Colombia", true),
            Answer("Venezuela", false),
            Answer("Peru", false),
            Answer("Surinam", false)
        )
        val jamaicaAnswers = mutableListOf(
            Answer("Jamaica", true),
            Answer("Saint Lucia", false),
            Answer("Grenada", false),
            Answer("Guatemala", false)
        )
        val mexicoAnswers = mutableListOf(
            Answer("Mexiko", true),
            Answer("Kanada", false),
            Answer("Nicaragua", false),
            Answer("Belize", false)
        )

        val cambodiaAnswers = mutableListOf(
            Answer("Kambodja", true),
            Answer("Iran", false),
            Answer("Sri Lanka", false),
            Answer("Singapore", false)
        )
        val saudiAnswers = mutableListOf(
            Answer("Saudiarabien", true),
            Answer("Qatar", false),
            Answer("Maldiverna", false),
            Answer("Malaysia", false)
        )

        val taiwanAnswers = mutableListOf(
            Answer("Taiwan", true),
            Answer("Afghanistan", false),
            Answer("Bhutan", false),
            Answer("Libanon", false)
        )

        val vietnamAnswers = mutableListOf(
            Answer("Vietnam", true),
            Answer("Thailand", false),
            Answer("Burma", false),
            Answer("Malaysia", false)
        )

        val uruguayAnswers = mutableListOf(
            Answer("Uruguay", true),
            Answer("Paraguay", false),
            Answer("Surinam", false),
            Answer("Ecuador", false)
        )

        questionsList.add(FlagQuestion(R.drawable.algeria, algeriaAnswers))
        questionsList.add(FlagQuestion(R.drawable.ivory, ivoryAnswers))
        questionsList.add(FlagQuestion(R.drawable.belarus, belarusAnswers))
        questionsList.add(FlagQuestion(R.drawable.sanmarino, sanMarinoAnswers))
        questionsList.add(FlagQuestion(R.drawable.georgia, georgiaAnswers))
        questionsList.add(FlagQuestion(R.drawable.ireland, irelandAnswers))
        questionsList.add(FlagQuestion(R.drawable.monaco, monacoAnswers))
        questionsList.add(FlagQuestion(R.drawable.poland, polandAnswers))
        questionsList.add(FlagQuestion(R.drawable.brazil, brazilAnswers))
        questionsList.add(FlagQuestion(R.drawable.colombia, colombiaAnswers))
        questionsList.add(FlagQuestion(R.drawable.jamaica, jamaicaAnswers))
        questionsList.add(FlagQuestion(R.drawable.mexiko, mexicoAnswers))
        questionsList.add(FlagQuestion(R.drawable.cambodia, cambodiaAnswers))
        questionsList.add(FlagQuestion(R.drawable.saudi_arabia, saudiAnswers))
        questionsList.add(FlagQuestion(R.drawable.taiwan, taiwanAnswers))
        questionsList.add(FlagQuestion(R.drawable.vietnam, vietnamAnswers))
        questionsList.add(FlagQuestion(R.drawable.uruguay, uruguayAnswers))
    }
}

