package com.three.api_integration

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class APICallManager {

    private val client = OkHttpClient()
    private val baseUrl = "https://api.chucknorris.io/jokes/random"

    fun makeApiCall(callback: (String?) -> Unit){

        val request = baseUrl.let {
            Request.Builder()
                .url(it)
                .build()
        }

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    val gson = Gson()
                    val apiResponse = gson.fromJson(responseBody, APIData::class.java)
                    callback(apiResponse.value)
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }
}

