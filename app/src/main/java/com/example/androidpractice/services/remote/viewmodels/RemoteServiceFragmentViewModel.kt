package com.example.androidpractice.services.remote.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.lifecycle.ViewModel
import com.example.androidpractice.IRemoteServiceInterface
import com.example.androidpractice.ObjectToBeRetrievedParcellable
import com.example.androidpractice.services.remote.businesslogic.RemoteService
import com.example.androidpractice.services.utils.ToastUtils

class RemoteServiceFragmentViewModel : ViewModel() {
    fun startService(context: Context, activity: Activity?, connection: ServiceConnection) {
        val intent = Intent(activity, RemoteService().javaClass)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        ToastUtils.showToast(context, "Service started")
    }

    fun stopService(context: Context, activity: Activity?, connection: ServiceConnection) {
        activity?.unbindService(connection)
        ToastUtils.showToast(context, "Service stopped")
    }

    fun getFromService(serviceInterface: IRemoteServiceInterface?, context: Context) {
        val obj = serviceInterface?.passFromService()
        ToastUtils.showToast(context, "Was given form remote service: ${obj?.message} ${obj?.timeCreated}")
    }

    fun passToService(serviceInterface: IRemoteServiceInterface?) {
        val obj = ObjectToBeRetrievedParcellable()
        obj.message = "Message-1"
        obj.timeCreated = "Time Created-1"
        serviceInterface?.passToService(obj)
    }
}