package com.three.api_integration

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class APICallManager {

    fun chuckNorrisApiCall(callback: (String?) -> Unit){

        val client = OkHttpClient()
        val baseUrl = "https://api.chucknorris.io/jokes/random"

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
                    val apiResponse = gson.fromJson(responseBody, APIChuckData::class.java)
                    callback(apiResponse.value)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

    fun foxApiCall(callback: (String?) -> Unit) {
        val client = OkHttpClient()
        val baseUrl = "https://randomfox.ca/floof/"

        val request = baseUrl.let {
            Request.Builder()
                .url(it)
                .build()
        }

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val gson = Gson()
                    val apiResponse = gson.fromJson(responseBody, APIFoxData::class.java)
                    callback(apiResponse.image)
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null)
            }
        })
    }

}

