package com.example.androidpractice.services.foreground.businesslogic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.androidpractice.services.utils.NotificationUtils
import com.example.androidpractice.services.utils.ObjectToBePassed
import com.example.androidpractice.services.utils.ToastUtils
import com.example.androidpractice.services.foreground.viewmodels.ForegroundServiceModel

class ForegroundServiceBroadCastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let {
            val passedObject = p1?.extras?.getSerializable(ForegroundServiceModel.passObjectKey) as? ObjectToBePassed
            val toastMessage = "Passed object: 1: ${passedObject?.message} 2: ${passedObject?.timeCreated}"
            ToastUtils.showToast(p0, toastMessage)
        }
    }
}