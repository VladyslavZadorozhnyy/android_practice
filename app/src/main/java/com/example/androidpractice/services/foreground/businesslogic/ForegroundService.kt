package com.example.androidpractice.services.foreground.businesslogic

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.androidpractice.services.utils.NotificationUtils
import com.example.androidpractice.services.foreground.viewmodels.ForegroundServiceModel

class ForegroundService : Service() {
    private val foregroundServiceModel = ForegroundServiceModel()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        intent?.action?.let {
            if (it == START_SERVICE) {
                val notification = NotificationUtils.createNotification(baseContext)
                startForeground(1, notification)
            } else if (it == RETRIEVE_VALUE) {
                foregroundServiceModel.retrieveObject(intent, baseContext)
            } else if (it == PASS_VALUE) {
                foregroundServiceModel.passObject(applicationContext)
            } else if (it == STOP_VALUE) {
                stopSelf()
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationUtils.deleteNotificationChannel(baseContext)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        const val FOREGROUND_PASS_ACTION = "com.example.androidpractice.services.foreground.businesslogic.PASS"

        const val START_SERVICE = "start-service"
        const val PASS_VALUE = "pass-value"
        const val RETRIEVE_VALUE = "retrieve-value"
        const val STOP_VALUE = "stop-service"
    }
}