package com.example.androidpractice.services.remote.businesslogic

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.androidpractice.IRemoteServiceInterface
import com.example.androidpractice.ObjectToBePassedParcellable
import com.example.androidpractice.ObjectToBeRetrievedParcellable
import com.example.androidpractice.services.remote.viewmodels.RemoteServiceViewModel

class RemoteService : Service() {
    private val viewModel = RemoteServiceViewModel()

    private val binder: IRemoteServiceInterface.Stub = object : IRemoteServiceInterface.Stub() {
        override fun passToService(obj: ObjectToBeRetrievedParcellable?) {
            viewModel.processObject(baseContext, obj)
        }

        override fun passFromService(): ObjectToBePassedParcellable {
            return viewModel.getObject()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}