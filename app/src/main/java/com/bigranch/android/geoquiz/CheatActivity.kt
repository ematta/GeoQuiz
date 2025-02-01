package com.bigranch.android.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bigranch.android.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.bigranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bigranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private val cheatViewModel: CheatViewModel by viewModels()

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean) : Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cheatViewModel.isAnswerShown = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                cheatViewModel.isAnswerShown -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(cheatViewModel.isAnswerShown)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(RESULT_OK, data)
    }
}