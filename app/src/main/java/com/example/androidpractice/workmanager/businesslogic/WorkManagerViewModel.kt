package com.example.androidpractice.workmanager.businesslogic

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.*

class WorkManagerViewModel : ViewModel() {
    fun startWork(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        onFinished: (result: String?) -> Unit,
    ) {
        GlobalScope.launch {
            runBlocking {
                delay(3000)
                val uploadRequest = OneTimeWorkRequestBuilder<CustomWorkManager>()
                    .build()

                WorkManager.getInstance(context).enqueue(uploadRequest)

                withContext(Dispatchers.Main) {
                    WorkManager.getInstance(context).getWorkInfoByIdLiveData(uploadRequest.id).observe(
                        lifecycleOwner
                    ) { info ->
                        if (info.state != WorkInfo.State.ENQUEUED && info.state != WorkInfo.State.RUNNING) {
                            onFinished.invoke(info.outputData.getString(CustomWorkManager.STRING_KEY))
                        }
                    }
                }
            }
        }
    }
}