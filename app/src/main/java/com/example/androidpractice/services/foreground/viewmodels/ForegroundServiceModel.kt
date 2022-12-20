package com.example.androidpractice.services.foreground.viewmodels

import android.content.Context
import java.io.Serializable
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androidpractice.MainActivity
import com.example.androidpractice.services.foreground.businesslogic.ForegroundService
import com.example.androidpractice.services.utils.ObjectToBePassed
import com.example.androidpractice.services.utils.ObjectToBeRetrieved
import com.example.androidpractice.services.utils.TimeUtils
import com.example.androidpractice.services.utils.ToastUtils


class ForegroundServiceModel : ViewModel() {
    private var retreivedObject: ObjectToBeRetrieved? = null

    fun retrieveObject(intent: Intent, context: Context) {
        retreivedObject = intent.getSerializableExtra(retrieveObjectKey) as? ObjectToBeRetrieved
        val objectMessage = "Retrived object - message: ${retreivedObject?.message} time created: ${retreivedObject?.timeCreated} to Service"

        ToastUtils.showToast(context, objectMessage)
    }

    fun passObject(context: Context) {
        val objectToBePassed = ObjectToBePassed("Some message", TimeUtils.getDateTime())
        val intent = Intent(ForegroundService.FOREGROUND_PASS_ACTION)
        val bundle = Bundle()

        bundle.putSerializable(passObjectKey, objectToBePassed)
        intent.putExtras(bundle)

        context.sendBroadcast(intent)
    }

    companion object {
        val passObjectKey = "pass_object_key"
        val retrieveObjectKey = "retrieve_object_key"
    }
}