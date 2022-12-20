package com.example.androidpractice.workmanager.businesslogic

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class CustomWorkManager(
    appContext: Context,
    params: WorkerParameters,
) : Worker(appContext, params) {
    override fun doWork(): Result {

        Thread.sleep(5000)

        val outputData = Data.Builder()
            .putString(STRING_KEY, "Hello from worker")
            .build()

        return Result.success(outputData)
    }

    companion object {
        val STRING_KEY = "STRING_KEY"
    }
}