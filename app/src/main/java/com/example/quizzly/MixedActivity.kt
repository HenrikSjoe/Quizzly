package com.example.quizzly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MixedActivity : AppCompatActivity() {

    lateinit var questionTextView : TextView
    lateinit var option1Button : Button
    lateinit var option2Button : Button
    lateinit var option3Button : Button
    var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mixed)

        questionTextView = findViewById(R.id.questionTextView)
        option1Button = findViewById(R.id.option1)
        option2Button = findViewById(R.id.option2)
        option3Button = findViewById(R.id.option3)

        q1()
    }

    fun q1 (){
        questionTextView.text = "Vad är dockan, Barbie's, fulla namn?"
        option1Button.text = "Barbara Millicent Roberts"
        option2Button.text = "Barbie McKen"
        option3Button.text = "Barbie Doll"

        option1Button.setOnClickListener(){
            q2()
            correctAnswers +=1

        }
        option2Button.setOnClickListener(){
            q2()

        }
        option3Button.setOnClickListener(){
            q2()

        }
    }

    fun q2 (){
        questionTextView.text = "Hur många andetag tar en människa varje dag?"
        option1Button.text = "Ca. 25000"
        option2Button.text = "Ca. 10000"
        option3Button.text = "Ca. 40000"

        option1Button.setOnClickListener(){
            q3()
            correctAnswers +=1

        }
        option2Button.setOnClickListener(){
            q3()

        }
        option3Button.setOnClickListener(){
            q3()

        }
    }
    fun q3 (){
        questionTextView.text = "Vad angav Al Capones visitkort att han jobbade med?"
        option1Button.text = "Sålde begagnade möbler"
        option2Button.text = "Banktjänsteman"
        option3Button.text = "Mångsysslare"

        option1Button.setOnClickListener(){
            q4()
            correctAnswers +=1

        }
        option2Button.setOnClickListener(){
            q4()

        }
        option3Button.setOnClickListener(){
            q4()

        }
    }
    fun q4 (){
        questionTextView.text = "Vilken nationalitet hade konstnären Henri Matisse?"
        option1Button.text = "Fransk"
        option2Button.text = "Belgisk"
        option3Button.text = "Schweizisk"

        option1Button.setOnClickListener(){
            q5()
            correctAnswers +=1

        }
        option2Button.setOnClickListener(){
            q5()

        }
        option3Button.setOnClickListener(){
            q5()

        }
    }

    fun q5 (){
        questionTextView.text = "Vilket år påbörjade Djinghis Khan sin erövring av Asien"
        option1Button.text = "År 1206"
        option2Button.text = "201 f.Kr"
        option3Button.text = "År 301"

        option1Button.setOnClickListener(){
            correctAnswers +=1
            startResultActivity()

        }
        option2Button.setOnClickListener(){
            startResultActivity()


        }
        option3Button.setOnClickListener(){
           startResultActivity()

        }
    }

    fun startResultActivity () {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("answeredCorrect", correctAnswers)
        startActivity(intent)
    }
}