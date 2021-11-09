package com.example.quizzly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    var name: String? = null
    lateinit var editName : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()



        editName = findViewById<EditText>(R.id.userNameEditText)
        val mixedBtn = findViewById<Button>(R.id.mixedButton)
        val flagsBtn = findViewById<Button>(R.id.flagsButton)
        val sportBtn = findViewById<Button>(R.id.sportButton)

        mixedBtn.setOnClickListener {
            name = editName.text.toString()
            if (name.isNullOrEmpty()) {
                Toast.makeText(this, "Välj ett namn", Toast.LENGTH_SHORT).show()
            } else {
                startMixedActivity()
            }
        }

        flagsBtn.setOnClickListener{
            name = editName.text.toString()
            if (name.isNullOrEmpty()) {
                Toast.makeText(this, "Välj ett namn", Toast.LENGTH_SHORT).show()
            } else {
                startFlagsActivity()
            }
        }

        sportBtn.setOnClickListener {
            name = editName.text.toString()
            if (name.isNullOrEmpty()) {
                Toast.makeText(this, "Välj ett namn", Toast.LENGTH_SHORT).show()
            } else {
                startSportActivity()
            }
        }
    }





    fun startMixedActivity() {
        val intent = Intent(this, MixedActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
        Log.d("1111", "startMixedActivity: name: $name ")
    }

    fun startFlagsActivity(){
        val intent = Intent(this, FlagsActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }

    fun startSportActivity(){
        val intent = Intent(this, SportActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}

