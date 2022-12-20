package com.example.androidpractice.services.remote.viewmodels

import android.content.Context
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.androidpractice.services.utils.ToastUtils
import com.example.androidpractice.ObjectToBePassedParcellable
import com.example.androidpractice.ObjectToBeRetrievedParcellable

class RemoteServiceViewModel : ViewModel() {
    fun processObject(context: Context, obj: ObjectToBeRetrievedParcellable?) {
        obj?.let {
            if (Looper.myLooper() == null)
                Looper.prepare()

            ToastUtils.showToast(context, "Gotcha object to remote service: ${it.message}, ${it.timeCreated}")
        }
    }

    fun getObject(): ObjectToBePassedParcellable {
        val obj = ObjectToBePassedParcellable()
        obj.message = "009"
        obj.timeCreated = "010"
        return obj
    }
}