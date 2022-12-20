package com.example.androidpractice.services.on_bind.businesslogic

import android.app.Service
import android.content.Intent
import android.os.Binder
import com.example.androidpractice.services.utils.ObjectToBePassed
import com.example.androidpractice.services.utils.ObjectToBeRetrieved
import com.example.androidpractice.services.utils.ToastUtils

class OnBindService : Service() {
    private val binder = ServiceBinder()

    override fun onBind(p0: Intent?): Binder {
        return binder
    }

    fun retrieveObject(obj: ObjectToBeRetrieved) {
        ToastUtils.showToast(baseContext,"Object retreived on service side: ${obj.message}, ${obj.timeCreated}")
    }

    fun passObjectFromService(): ObjectToBePassed {
        return ObjectToBePassed("Message", "ObjectToBePassed from service")
    }

    inner class ServiceBinder : Binder() {
        fun getService(): OnBindService {
            return this@OnBindService
        }
    }
}