package com.example.androidpractice.retrofit.businesslogic

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitController : Callback<List<ChangeModel>> {
    private val baseUrl = "https://git.eclipse.org/r/"
    private var result = "No response was given"
    private lateinit var responseCallBack: (String) -> Unit

    fun start(callBack: (String) -> Unit) {
        responseCallBack = callBack

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(GerritAPI::class.java)
        val call = api.loadChanges("status:open")

        call.enqueue(this)
    }

    override fun onResponse(
        call: Call<List<ChangeModel>>?,
        response: Response<List<ChangeModel>>?
    ) {
        result = ""

        if (response?.isSuccessful == true) {
            val changeList = response.body()

            changeList.forEach {
                result += "\n" + it.subject
//                Log.d("AAADIP", "it.subject: ${it.subject}")
            }
        } else {
            result += "\n" + response?.errorBody()
//            Log.d("AAADIP", "onResponse error: ${response?.errorBody()}")
        }

        responseCallBack.invoke(result)
    }

    override fun onFailure(call: Call<List<ChangeModel>>?, t: Throwable?) {
        result = t?.message ?: "Unknown error occured"
        responseCallBack.invoke(result)
//        Log.d("AAADIP", "onFailure error: ${t?.message}")
    }
}