package com.bigranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val CHEATER_KEY = "CHEATER_KEY"

class CheatViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var isAnswerShown: Boolean
        get() = savedStateHandle.get(CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(CHEATER_KEY, value)
}