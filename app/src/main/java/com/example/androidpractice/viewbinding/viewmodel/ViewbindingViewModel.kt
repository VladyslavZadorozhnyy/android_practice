package com.example.androidpractice.viewbinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewbindingViewModel: ViewModel() {
    private val TITLE_VALUE = "This is viewbinding fragment example"
    private var title = TITLE_VALUE

    fun getTitle() = title

    fun resetTitle() {
        title = TITLE_VALUE
    }

    fun shuffleTitle() {
        val shuffledTitle = title.toList().shuffled()
        title = ""
        shuffledTitle.forEach { title += it }
    }
}