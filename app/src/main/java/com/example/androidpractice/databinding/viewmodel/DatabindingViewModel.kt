package com.example.androidpractice.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatabindingViewModel : ViewModel() {
    private val TITLE_VALUE = "This is databinding fragment example"
    private val title = MutableLiveData(TITLE_VALUE)

    fun getTitle() = title

    fun resetTitle() {
        title.value = TITLE_VALUE
    }

    fun shuffleTitle() {
        val shuffledTitle = title.value?.toList()?.shuffled()
        title.value = ""
        shuffledTitle?.forEach { title.value += it }
    }
}