package com.example.androidpractice.services.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {
    private val objectRetreivedMessage = ""
    private val objectSentMessage = ""

    fun showToast(context: Context, text: String, toastLength: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, text, toastLength).show()
    }
}