package com.example.androidpractice.services.background.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.services.utils.TimeUtils
import com.example.androidpractice.services.utils.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BackgroundServiceModel : ViewModel() {
    private var serviceStarted = false
    private var backgroundThread: Thread? = null

    fun startBackgroundThread(context: Context) {
        serviceStarted = true
        backgroundThread = Thread {
            while (true) {
                try {
                    val message = "BackgroundService working: ${TimeUtils.getDateTime()}"
                    viewModelScope.launch(Dispatchers.Main) {
                        ToastUtils.showToast(context, message, Toast.LENGTH_SHORT)
                    }
                    Thread.sleep(7000)
                } catch (e: InterruptedException) {
                    return@Thread
                }
            }
        }

        backgroundThread?.start()
    }

    fun destroyService() {
        serviceStarted = false
        backgroundThread?.interrupt()
        backgroundThread = null
    }
}