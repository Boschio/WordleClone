package com.example.wordleclone

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

const val maxGuesses = 3

class MainActivity : AppCompatActivity() {

    var wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessButton = findViewById<Button>(R.id.guessButton)
        val guessLayout = findViewById<LinearLayout>(R.id.guessLayout)
        val answerLayout = findViewById<LinearLayout>(R.id.answerLayout)
        val guessInput = findViewById<TextInputEditText>(R.id.guessInput)
        val answerText = findViewById<TextView>(R.id.answerText)
        val answer = findViewById<TextView>(R.id.answer)
        var count = 0

        answer.setText(wordToGuess)

        fun restart(){
            answerText.visibility = View.VISIBLE
            answer.visibility = View.VISIBLE
            guessButton.isClickable = false
            guessButton.setBackgroundColor(Color.LTGRAY)
            guessInput.isEnabled = false

            val restartButton = Button(this)
            restartButton.setText("Restart")
            restartButton.setBackgroundColor(Color.BLUE)

            restartButton.setOnClickListener {
                val intent = intent
                finish()
                startActivity(intent)
            }

            answerLayout.addView(restartButton)

        }

        guessButton.setOnClickListener {
            var guess = guessInput.text.toString().uppercase()
            var guessCheck = checkGuess(guess)

            if (count < maxGuesses) {

                count++

                val tv_guess = TextView(this)
                tv_guess.textSize = 20f
                tv_guess.text = "Guess #" + count + "                                  " + guess
                guessLayout.addView(tv_guess)

                val tv_check = TextView(this)
                tv_check.textSize = 20f
                tv_check.text = "Guess #" + count + " Check                       " + guessCheck
                guessLayout.addView(tv_check)

            }

            if (guessCheck == "OOOO") {

                Toast.makeText(it.context, "You Won!", Toast.LENGTH_LONG).show()
                restart()
            }

            if (count == maxGuesses && guessCheck != "OOOO") {

                Toast.makeText(it.context, "You Lose...", Toast.LENGTH_LONG).show()
                restart()
            }
        }
    }



    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }



}