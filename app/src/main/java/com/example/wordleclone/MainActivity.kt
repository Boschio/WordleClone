package com.example.wordleclone

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    var wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessButton = findViewById<Button>(R.id.guessButton)
        val guessLayout = findViewById<LinearLayout>(R.id.guessLayout)
        val guessInput = findViewById<TextInputEditText>(R.id.guessInput)
        val answer = findViewById<TextView>(R.id.answer)
        var count = 0

        answer.setText(wordToGuess)

        guessButton.setOnClickListener {
            var guess = guessInput.text.toString().uppercase()
            var guessCheck = checkGuess(guess)

            if (count < 3) {
                count++
                val tv_guess = TextView(this)
                tv_guess.textSize = 20f
                tv_guess.text = "Guess #" + count + "                             " + guess
                guessLayout.addView(tv_guess)

                val tv_check = TextView(this)
                tv_check.textSize = 20f
                tv_check.text = "Guess #" + count + " Check                  " + guessCheck
                guessLayout.addView(tv_check)

            }

            if (guessCheck == "OOOO" && count < 3) {
                answer.visibility = View.VISIBLE
                guessButton.isClickable = false
                guessButton.setBackgroundColor(Color.LTGRAY)
                guessInput.isEnabled = false
                Toast.makeText(it.context, "You Won!", Toast.LENGTH_LONG).show()
            }

            if (count == 3 && guessCheck != "OOOO") {
                answer.visibility = View.VISIBLE
                guessButton.isClickable = false
                guessButton.setBackgroundColor(Color.LTGRAY)
                guessInput.isEnabled = false
                Toast.makeText(it.context, "You Lose...", Toast.LENGTH_LONG).show()
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