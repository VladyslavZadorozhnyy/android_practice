package com.example.androidpractice.notification.viewmodels

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {
//    private lateinit var context: Context
    private lateinit var notificationManager: NotificationManager

    fun setContextValue(value: Context) {
        notificationManager = value.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun sendImmediateNotification(context: Context) {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(context, "101")
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentTitle("Text title from viewModel")
            .setContentText("Text content from viewModel")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1010, builder.build())
    }

    fun sendDelayedNotification(context: Context, looper: Looper, seconds: Int) {
        val handler = Handler(looper)
        handler.postDelayed(
            {
                sendImmediateNotification(context)
            }, 1000L * seconds)
    }

    fun sendProgressNotification(context: Context) {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(context, "101")
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentTitle("Text title from viewModel")
            .setContentText("Text content from viewModel")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        for (i in 0..10) {
            builder.setProgress(100, i * 10, false)
            notificationManager.notify(1010, builder.build())
            Thread.sleep(1000)
        }

        builder.setProgress(0, 0, false)
        notificationManager.notify(1010, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                "101",
                "Channel name",
                importance
            ).apply {
                description = "Description name"
            }

            notificationManager.createNotificationChannel(channel)
        }
    }
}