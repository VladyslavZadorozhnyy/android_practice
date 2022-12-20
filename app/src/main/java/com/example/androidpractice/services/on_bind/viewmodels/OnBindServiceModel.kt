package com.example.androidpractice.services.on_bind.viewmodels

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androidpractice.services.on_bind.businesslogic.OnBindService
import com.example.androidpractice.services.utils.ObjectToBePassed
import com.example.androidpractice.services.utils.ObjectToBeRetrieved
import com.example.androidpractice.services.utils.ToastUtils

class OnBindServiceModel : ViewModel() {
    private var service: OnBindService? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            (p1 as? OnBindService.ServiceBinder)?.let {
                service = it.getService()
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            service = null
        }
    }

    fun connectToService(activity: Activity) {
        val intent = Intent(activity.baseContext, OnBindService::class.java)
        activity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        ToastUtils.showToast(activity.baseContext, "Service connected")
    }

    fun disconnectFromService(activity: Activity) {
        activity.unbindService(serviceConnection)
        ToastUtils.showToast(activity.baseContext, "Service disconnected")
    }

    fun passToService() {
        val obj = ObjectToBeRetrieved("Message", "Time created")
        service?.retrieveObject(obj)
    }

    fun passFromService(): ObjectToBePassed {
        return service?.passObjectFromService()
            ?: ObjectToBePassed("Error", "Service not available")
    }
}