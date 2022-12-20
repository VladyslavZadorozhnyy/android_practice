package com.example.androidpractice.services.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.androidpractice.MainActivity

object NotificationUtils {
    private val channelId = "sampleChannelId"
    private val channelName = "sampleChannelName"

    private val notificationTitle = "Some notification title"
    private val notificationText = "Some notification text"

    private fun createNotificationChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    fun deleteNotificationChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.deleteNotificationChannel(channelId)
        }
    }

    fun createNotification(context: Context?): Notification? {
        context?.let {
            createNotificationChannel(it)
            val pendingIntent = createPendingIntent(it)
            return NotificationCompat.Builder(context, channelId)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
                .setContentIntent(pendingIntent)
                .build()
        }
        return null
    }

    private fun createPendingIntent(context: Context?): PendingIntent {
        val notificationIntent = Intent(context, MainActivity::class.java)

        return PendingIntent.getActivity(
            context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
    }
}