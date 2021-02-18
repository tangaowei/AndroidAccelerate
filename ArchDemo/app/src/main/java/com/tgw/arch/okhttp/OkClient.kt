package com.tgw.arch.okhttp

import android.os.Handler
import android.util.Log
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkClient {
    var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    companion object {
        private var instane : OkClient = OkClient()
        fun getInstance() : OkClient {
            return instane
        }
    }

    fun get(url: String, responseHandler: IResponseHandler) {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(MyCallback(Handler(), responseHandler))
    }
}

class MyCallback(
    private var mHandler: Handler,
    private var mRespnoseHandler : IResponseHandler
) : Callback {
    override fun onFailure(call: Call, e: IOException) {
        mHandler.post {
            mRespnoseHandler.onFailure(0, e.toString())
        }
    }

    override fun onResponse(call: Call, response: Response) {
        val name = Thread.currentThread().name
        Log.d("TGW", "currentThread: {$name}")
        if (response != null && response.isSuccessful) {
            mHandler.post {
                val threadName = Thread.currentThread().name
                Log.d("TGW", "threadName: {$threadName}")
                response.body?.string()?.let { mRespnoseHandler.onSuccess(response.code, it) }
            }
        } else {
            mRespnoseHandler.onFailure(response.code, response.body.toString())
        }
    }
}


interface IResponseHandler {
    fun onFailure(statusCode : Int, errorMsg: String)
    fun onProgress(currentBytes: Long, totalBytes: Long)
    fun onSuccess(statusCode : Int, response: String)
}