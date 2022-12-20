package com.example.androidpractice.services.background.businesslogic

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.androidpractice.services.background.viewmodels.BackgroundServiceModel

class BackgroundService : Service() {
    private val backgroundServiceModel = BackgroundServiceModel()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        intent?.action?.let {
            if (it == START_SERVICE) {
                backgroundServiceModel.startBackgroundThread(baseContext)
            } else if (it == STOP_VALUE) {
                backgroundServiceModel.destroyService()
                stopSelf()
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        const val START_SERVICE = "start-service"
        const val STOP_VALUE = "stop-service"
    }
}