package com.bigranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.bigranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var score = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }

        binding.nextButton.setOnClickListener {
            if(currentIndex == questionBank.size - 1){
                currentIndex = 0
            } else {
                currentIndex = (currentIndex + 1) % questionBank.size
            }
            updateQuestion()
        }

        binding.previousButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size - 1
            } else {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            updateQuestion()
        }

        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        binding.trueButton.isEnabled = questionBank[currentIndex].isAnswered == false
        binding.falseButton.isEnabled = questionBank[currentIndex].isAnswered == false
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            score++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Log.d(TAG, "Score: $score")
        questionBank[currentIndex].isAnswered = true

        Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        ).show()
    }
}